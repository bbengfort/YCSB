package com.yahoo.ycsb.benfs;

import com.yahoo.ycsb.ByteIterator;
import com.yahoo.ycsb.DB;
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

// TODO Implement methods required and properly make this a sub-module before making the pull
// request.
public class BenFSClient extends DB {
  private static final Logger logger = Logger.getLogger(BenFSClient.class.getName());

  private final ManagedChannel channel;
  private final KVGrpc.KVBlockingStub blockingStub;

  /** Construct client for accessing HelloWorld server using the existing channel. */
  private BenFSClient(ManagedChannel channel) {
    this.channel = channel;
    blockingStub = KVGrpc.newBlockingStub(channel);
  }

  public void shutdown() throws InterruptedException {
    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
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

  @Override
  public Status read(
      String table, String key, Set<String> fields, Map<String, ByteIterator> result) {
    // TODO read certain fields.

    logger.info("Will try to read " + table + " ...");
    Client.GetRequest request = Client.GetRequest.newBuilder().setIdentity("test").setKey(key).build();
    Client.ClientReply response;
    try {
      response = blockingStub.get(request);
    } catch (StatusRuntimeException e) {
      logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
      return Status.ERROR;
    }
    logger.info("Response: " + response.getPair());
    return Status.OK;
  }

  @Override
  public Status scan(
      String table,
      String startkey,
      int recordcount,
      Set<String> fields,
      Vector<HashMap<String, ByteIterator>> result) {

    // Start ben's server which is in go and have it run locally

    // Use GRPC to send a request in the format that the server is waiting for it.
    return null;
  }

  @Override
  public Status update(String table, String key, Map<String, ByteIterator> values) {
    return null;
  }

  @Override
  public Status insert(String table, String key, Map<String, ByteIterator> values) {
    return null;
  }

  @Override
  public Status delete(String table, String key) {
    return null;
  }

  public static void main(String args[]){
    BenFSClient client = new BenFSClient("localhost", 50051);
  }
}
