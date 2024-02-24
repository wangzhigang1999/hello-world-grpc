package com.hello;

import com.hello.service.HelloServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;

import java.io.IOException;

public class GrpcServer {
    public static Logger logger = org.slf4j.LoggerFactory.getLogger(GrpcServer.class);

    public static void main(String[] args) throws IOException, InterruptedException {

        Server server = ServerBuilder.forPort(8080).addService(new HelloServiceImpl()).build();
        server.start();
        logger.info("Server started, listening on 8080");
        server.awaitTermination();
    }
}