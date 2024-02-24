package com.hello.service;

import com.hello.grpc.HelloRequest;
import com.hello.grpc.HelloResponse;
import com.hello.grpc.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;

public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void hello(HelloRequest request, StreamObserver<HelloResponse> observer) {
        AsyncService.asyncHello(request, observer);
    }
}