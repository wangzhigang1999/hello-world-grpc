package com.hello;

import com.hello.grpc.HelloRequest;
import com.hello.grpc.HelloResponse;
import com.hello.grpc.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {
    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build();
        HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            try {
                HelloResponse helloResponse = stub.hello(HelloRequest.newBuilder()
                        .setFirstName("haha")
                        .setLastName("Monster")
                        .build());
                System.out.println(helloResponse.getGreeting());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        channel.shutdown();
    }
}