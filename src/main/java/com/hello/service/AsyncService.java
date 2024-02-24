package com.hello.service;

import com.hello.grpc.HelloRequest;
import com.hello.grpc.HelloResponse;
import io.grpc.stub.StreamObserver;

public class AsyncService {

    public static void asyncHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        Thread.ofVirtual().start(() -> {
            String greeting = "Hello, " + request.getFirstName() + " " + request.getLastName();
            HelloResponse response = HelloResponse.newBuilder().setGreeting(greeting).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        });

    }
}
