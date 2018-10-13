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

  /** Construct client for accessing HelloWorld server using the existing channel. */
  private BenFSClient(ManagedChannel channel) {
    blockingStub = KVGrpc.newBlockingStub(channel);
  }

  /** Construct client connecting to HelloWorld server at {@code host:port}. */
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

  @Override
  public Status read(
      String table, String key, Set<String> fields, Map<String, ByteIterator> result) {

    // TODO read certain fields.
    System.out.println("Fields: " + fields);
    System.out.println("key " + key);

    logger.info("Will try to read " + table + " ...");
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
    logger.info("Response: " + response.getPair());
    return Status.OK;
  }

  /**
   * TODO How do we deal with scan?
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
   * TODO Is this what we want to do with update?
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

  @Override
  public Status insert(String table, String key, Map<String, ByteIterator> values) {
    logger.info("Will try to read " + table + " ...");
    logger.info("key: "+key);

    String value = values.toString();
    System.out.println("value: "+value);
    
    Client.PutRequest request =
        Client.PutRequest.newBuilder()
            .setIdentity("test")
            .setKey(key)
            .setValue(ByteString.copyFrom(value.getBytes()))
            .build();
    Client.ClientReply response;

    try {
      response = blockingStub.put(request);

      // Add the returned value to the result
      ByteIterator byteIter =
          new ByteArrayByteIterator(response.getPair().getValue().toByteArray());
      values.put(key, byteIter);
    } catch (StatusRuntimeException e) {
      logger.log(Level.WARNING, "RPC failed: {put}", e.getStatus());
      return Status.ERROR;
    }
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

  public static void main(String args[]) {
    BenFSClient client = new BenFSClient("localhost", 50051);
  }
}
