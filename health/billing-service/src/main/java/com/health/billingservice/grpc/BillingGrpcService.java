package com.health.billingservice.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class BillingGrpcService extends BillingServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    @Override
    public void createBilling(BillingRequest request,
                              StreamObserver<BillingResponse> responseObserver) {
        log.info("create billing account request received: {}", request.toString());
        BillingResponse response = BillingResponse.newBuilder()
                .setAccountId("123456")
                .setStatus("ACTIVE")
                .build();
//        super.createBilling(request, responseObserver);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
