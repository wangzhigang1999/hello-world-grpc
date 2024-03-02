package com.hello.aop;

import io.grpc.*;

public class FilterServerInterceptor implements ServerInterceptor {

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {

        final ServerCall.Listener<ReqT> original = next.startCall(call, headers);

        return new ForwardingServerCallListener.SimpleForwardingServerCallListener<>(original) {
            @Override
            public void onMessage(final ReqT message) {
                if (Math.random() > 0.5) {
                    call.close(Status.INTERNAL.withDescription("Random closed,bye~"), new Metadata());
                } else {
                    super.onMessage(message);
                }
            }

            @Override
            public void onHalfClose() {
                if (call.isCancelled() || !call.isReady()) {
                    return;
                }
                super.onHalfClose();
            }
        };
    }
}
