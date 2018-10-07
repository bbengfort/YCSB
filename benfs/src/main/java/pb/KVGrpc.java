package pb;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * Defines the service beteween the wrapped in-memory store and network clients.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.1)",
    comments = "Source: client.proto")
public final class KVGrpc {

  private KVGrpc() {}

  public static final String SERVICE_NAME = "pb.KV";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<pb.Client.GetRequest,
      pb.Client.ClientReply> getGetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Get",
      requestType = pb.Client.GetRequest.class,
      responseType = pb.Client.ClientReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pb.Client.GetRequest,
      pb.Client.ClientReply> getGetMethod() {
    io.grpc.MethodDescriptor<pb.Client.GetRequest, pb.Client.ClientReply> getGetMethod;
    if ((getGetMethod = KVGrpc.getGetMethod) == null) {
      synchronized (KVGrpc.class) {
        if ((getGetMethod = KVGrpc.getGetMethod) == null) {
          KVGrpc.getGetMethod = getGetMethod = 
              io.grpc.MethodDescriptor.<pb.Client.GetRequest, pb.Client.ClientReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "pb.KV", "Get"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pb.Client.GetRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pb.Client.ClientReply.getDefaultInstance()))
                  .setSchemaDescriptor(new KVMethodDescriptorSupplier("Get"))
                  .build();
          }
        }
     }
     return getGetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pb.Client.PutRequest,
      pb.Client.ClientReply> getPutMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Put",
      requestType = pb.Client.PutRequest.class,
      responseType = pb.Client.ClientReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pb.Client.PutRequest,
      pb.Client.ClientReply> getPutMethod() {
    io.grpc.MethodDescriptor<pb.Client.PutRequest, pb.Client.ClientReply> getPutMethod;
    if ((getPutMethod = KVGrpc.getPutMethod) == null) {
      synchronized (KVGrpc.class) {
        if ((getPutMethod = KVGrpc.getPutMethod) == null) {
          KVGrpc.getPutMethod = getPutMethod = 
              io.grpc.MethodDescriptor.<pb.Client.PutRequest, pb.Client.ClientReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "pb.KV", "Put"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pb.Client.PutRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pb.Client.ClientReply.getDefaultInstance()))
                  .setSchemaDescriptor(new KVMethodDescriptorSupplier("Put"))
                  .build();
          }
        }
     }
     return getPutMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pb.Client.DelRequest,
      pb.Client.ClientReply> getDelMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Del",
      requestType = pb.Client.DelRequest.class,
      responseType = pb.Client.ClientReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pb.Client.DelRequest,
      pb.Client.ClientReply> getDelMethod() {
    io.grpc.MethodDescriptor<pb.Client.DelRequest, pb.Client.ClientReply> getDelMethod;
    if ((getDelMethod = KVGrpc.getDelMethod) == null) {
      synchronized (KVGrpc.class) {
        if ((getDelMethod = KVGrpc.getDelMethod) == null) {
          KVGrpc.getDelMethod = getDelMethod = 
              io.grpc.MethodDescriptor.<pb.Client.DelRequest, pb.Client.ClientReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "pb.KV", "Del"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pb.Client.DelRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pb.Client.ClientReply.getDefaultInstance()))
                  .setSchemaDescriptor(new KVMethodDescriptorSupplier("Del"))
                  .build();
          }
        }
     }
     return getDelMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static KVStub newStub(io.grpc.Channel channel) {
    return new KVStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static KVBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new KVBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static KVFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new KVFutureStub(channel);
  }

  /**
   * <pre>
   * Defines the service beteween the wrapped in-memory store and network clients.
   * </pre>
   */
  public static abstract class KVImplBase implements io.grpc.BindableService {

    /**
     */
    public void get(pb.Client.GetRequest request,
        io.grpc.stub.StreamObserver<pb.Client.ClientReply> responseObserver) {
      asyncUnimplementedUnaryCall(getGetMethod(), responseObserver);
    }

    /**
     */
    public void put(pb.Client.PutRequest request,
        io.grpc.stub.StreamObserver<pb.Client.ClientReply> responseObserver) {
      asyncUnimplementedUnaryCall(getPutMethod(), responseObserver);
    }

    /**
     */
    public void del(pb.Client.DelRequest request,
        io.grpc.stub.StreamObserver<pb.Client.ClientReply> responseObserver) {
      asyncUnimplementedUnaryCall(getDelMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pb.Client.GetRequest,
                pb.Client.ClientReply>(
                  this, METHODID_GET)))
          .addMethod(
            getPutMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pb.Client.PutRequest,
                pb.Client.ClientReply>(
                  this, METHODID_PUT)))
          .addMethod(
            getDelMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pb.Client.DelRequest,
                pb.Client.ClientReply>(
                  this, METHODID_DEL)))
          .build();
    }
  }

  /**
   * <pre>
   * Defines the service beteween the wrapped in-memory store and network clients.
   * </pre>
   */
  public static final class KVStub extends io.grpc.stub.AbstractStub<KVStub> {
    private KVStub(io.grpc.Channel channel) {
      super(channel);
    }

    private KVStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KVStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new KVStub(channel, callOptions);
    }

    /**
     */
    public void get(pb.Client.GetRequest request,
        io.grpc.stub.StreamObserver<pb.Client.ClientReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void put(pb.Client.PutRequest request,
        io.grpc.stub.StreamObserver<pb.Client.ClientReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPutMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void del(pb.Client.DelRequest request,
        io.grpc.stub.StreamObserver<pb.Client.ClientReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDelMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Defines the service beteween the wrapped in-memory store and network clients.
   * </pre>
   */
  public static final class KVBlockingStub extends io.grpc.stub.AbstractStub<KVBlockingStub> {
    private KVBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private KVBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KVBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new KVBlockingStub(channel, callOptions);
    }

    /**
     */
    public pb.Client.ClientReply get(pb.Client.GetRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetMethod(), getCallOptions(), request);
    }

    /**
     */
    public pb.Client.ClientReply put(pb.Client.PutRequest request) {
      return blockingUnaryCall(
          getChannel(), getPutMethod(), getCallOptions(), request);
    }

    /**
     */
    public pb.Client.ClientReply del(pb.Client.DelRequest request) {
      return blockingUnaryCall(
          getChannel(), getDelMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Defines the service beteween the wrapped in-memory store and network clients.
   * </pre>
   */
  public static final class KVFutureStub extends io.grpc.stub.AbstractStub<KVFutureStub> {
    private KVFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private KVFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KVFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new KVFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pb.Client.ClientReply> get(
        pb.Client.GetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pb.Client.ClientReply> put(
        pb.Client.PutRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPutMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pb.Client.ClientReply> del(
        pb.Client.DelRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDelMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET = 0;
  private static final int METHODID_PUT = 1;
  private static final int METHODID_DEL = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final KVImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(KVImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET:
          serviceImpl.get((pb.Client.GetRequest) request,
              (io.grpc.stub.StreamObserver<pb.Client.ClientReply>) responseObserver);
          break;
        case METHODID_PUT:
          serviceImpl.put((pb.Client.PutRequest) request,
              (io.grpc.stub.StreamObserver<pb.Client.ClientReply>) responseObserver);
          break;
        case METHODID_DEL:
          serviceImpl.del((pb.Client.DelRequest) request,
              (io.grpc.stub.StreamObserver<pb.Client.ClientReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class KVBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    KVBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return pb.Client.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("KV");
    }
  }

  private static final class KVFileDescriptorSupplier
      extends KVBaseDescriptorSupplier {
    KVFileDescriptorSupplier() {}
  }

  private static final class KVMethodDescriptorSupplier
      extends KVBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    KVMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (KVGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new KVFileDescriptorSupplier())
              .addMethod(getGetMethod())
              .addMethod(getPutMethod())
              .addMethod(getDelMethod())
              .build();
        }
      }
    }
    return result;
  }
}
