package com.yahoo.ycsb.benfs;

import com.google.protobuf.ByteString;
import com.yahoo.ycsb.ByteArrayByteIterator;
import com.yahoo.ycsb.ByteIterator;
import com.yahoo.ycsb.DB;
import com.yahoo.ycsb.DBException;
import com.yahoo.ycsb.Status;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import pb.Client;
import pb.KVGrpc;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BenFSClient extends DB {
  public static final Logger logger = Logger.getLogger(BenFSClient.class.getName());

  public static ManagedChannel channel;
  public KVGrpc.KVBlockingStub blockingStub;

  public static final String HOST_DEFAULT = "localhost";
  public static final String PORT_PROPERTY_DEFAULT = "3264";

  public static final String HOST_PROPERTY = "host";
  public static final String PORT_PROPERTY = "port";

  public BenFSClient() {}

  private BenFSClient(ManagedChannel channel) {
    blockingStub = KVGrpc.newBlockingStub(channel);
  }

  private BenFSClient(String host, int port) {
    this(
        ManagedChannelBuilder.forAddress(host, port)
            // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
            // needing certificates.
            .usePlaintext()
            .build());
  }

  /**
   * Initialize any state for this DB. Called once per DB instance; there is one DB instance per
   * client thread.
   */
  @Override
  public void init() throws DBException {

    String host = getProperties().getProperty(HOST_PROPERTY, HOST_DEFAULT);
    int port = Integer.valueOf(getProperties().getProperty(PORT_PROPERTY, PORT_PROPERTY_DEFAULT));

    channel =
        ManagedChannelBuilder.forAddress(host, port)
            // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
            // needing certificates.
            .usePlaintext()
            .build();

    blockingStub = KVGrpc.newBlockingStub(channel);
  }

  @Override
  public void cleanup() throws DBException {
    try {
      channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Read a record from the database. Each field/value pair from the result will be stored in a
   * HashMap.
   *
   * @param table The name of the table
   * @param key The record key of the record to read.
   * @param fields The list of fields to read, or null for all of them
   * @param result A HashMap of field/value pairs for the result
   * @return
   */
  @Override
  public Status read(
      String table, String key, Set<String> fields, Map<String, ByteIterator> result) {
    if (fields == null) {

      // TODO What should the identity be?
      Client.GetRequest request =
          Client.GetRequest.newBuilder().setIdentity("test").setKey(key).build();
      Client.ClientReply response;
      try {
        response = blockingStub.get(request);

        // Add the returned value to the result
        ByteIterator value = new ByteArrayByteIterator(response.getPair().getValue().toByteArray());
        result.put(key, value);

      } catch (StatusRuntimeException e) {
        logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
        return Status.ERROR;
      }
    } else {

      // NOTE: The current version of the benchmark doesn't seem to ever call this with non-null
      // fields.
      return Status.BAD_REQUEST;
    }

    return Status.OK;
  }

  /**
   * TODO How do we deal with scan? Is it needed?
   *
   * @param table The name of the table
   * @param startkey The record key of the first record to read.
   * @param recordcount The number of records to read
   * @param fields The list of fields to read, or null for all of them
   * @param result A Vector of HashMaps, where each HashMap is a set field/value pairs for one
   *     record
   * @return
   */
  @Override
  public Status scan(
      String table,
      String startkey,
      int recordcount,
      Set<String> fields,
      Vector<HashMap<String, ByteIterator>> result) {
    return Status.NOT_IMPLEMENTED;
  }

  /**
   * TODO Update currently simply does an insert. It probably doesn't make sense to do two requests
   * to check if the value already exists right?
   *
   * @param table The name of the table
   * @param key The record key of the record to write.
   * @param values A HashMap of field/value pairs to update in the record
   * @return
   */
  @Override
  public Status update(String table, String key, Map<String, ByteIterator> values) {
    return insert(table, key, values);
  }

  /**
   * Insert a record in the database. Any field/value pairs in the specified values HashMap will be
   * written into the record with the specified record key.
   *
   * <p>NOTE: The values that are inserted are in the input values map in the form of (field, value)
   * pairs. The way we insert them here is we create a unique key-field string and use that as the
   * key for each value in the map.
   *
   * @param table The name of the table
   * @param key The record key of the record to insert.
   * @param values A HashMap of field/value pairs to insert in the record
   * @return Status of the operation
   */
  @Override
  public Status insert(String table, String key, Map<String, ByteIterator> values) {

    // Add a unique key-value pair for each field in the values map.
    for (Map.Entry<String, ByteIterator> fieldValuePair : values.entrySet()) {
      ByteString value = ByteString.copyFrom(fieldValuePair.getValue().toArray());
      Status s = insertSingleKVPair(key, value);

      if (s == Status.ERROR) {
        return Status.ERROR;
      }
    }

    return Status.OK;
  }

  /**
   * Creates request to the server and handles response.
   *
   * @param key String representation of the unique key to be inserted.
   * @param value Value bytes for the aforementioned key.
   * @return
   */
  private Status insertSingleKVPair(String key, ByteString value) {

    // Build Put Request
    // TODO What should we put as the identity of the put request?
    Client.PutRequest request =
        Client.PutRequest.newBuilder().setIdentity("test").setKey(key).setValue(value).build();
    Client.ClientReply response;

    try {

      // Get the response of the put request
      response = blockingStub.put(request);

      if (!response.getSuccess()) {
        logger.log(Level.WARNING, "RPC failed: {put} response:", response.getSuccess());
        return Status.ERROR;
      }
    } catch (StatusRuntimeException e) {
      logger.log(Level.WARNING, "RPC failed: {put}", e.getStatus());
      return Status.ERROR;
    }

//    logger.log(Level.INFO, "Inserted: " + key + "," + new String(value.toByteArray()));
    return Status.OK;
  }

  @Override
  public Status delete(String table, String key) {
    Client.DelRequest request = Client.DelRequest.newBuilder().setKey(key).build();
    Client.ClientReply response;

    try {
      response = blockingStub.del(request);
      logger.log(Level.WARNING, "Delete Response: " + response.getPair());
      logger.log(Level.WARNING, "Error: " + response.getError());
    } catch (StatusRuntimeException e) {
      logger.log(Level.WARNING, "RPC failed: {del}", e.getStatus());
      return Status.ERROR;
    }
    return Status.OK;
  }
}
