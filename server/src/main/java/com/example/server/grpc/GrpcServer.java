package com.example.server.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GrpcServer {
    private final NoteGrpcController noteGrpcController;
    @Value("${server.grpc-port}")
    private int port;

    @Async
    @EventListener(ApplicationStartedEvent.class)
    void startServer() throws Exception {
        Server server = ServerBuilder.forPort(port)
                                     .addService(noteGrpcController)
                                     .build();

        log.info("GRPC Server started on %s".formatted(port));

        server.start()
              .awaitTermination();

        log.info("SERVER SHUTDOWN");
    }
}
