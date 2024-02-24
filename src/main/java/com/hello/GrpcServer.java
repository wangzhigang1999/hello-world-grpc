package com.hello;

import com.hello.service.HelloServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.logging.Logger;

public class GrpcServer {
    public static Logger logger = Logger.getLogger(GrpcServer.class.getName());

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder
                .forPort(8080)
                .addService(new HelloServiceImpl()).build();

        server.start();
        logger.info("Server started, listening on 8080");
        server.awaitTermination();

    }
}