package com.example.poc_for_java.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompensationService {

    private final CompensationProcessor compensationProcessor;

    @Async("asyncExecutor")
    public CompletableFuture<Void> compensateAsync(Long requestId) {
        try {
            log.info("Starting compensation for requestId: {}", requestId);
            compensationProcessor.performCompensationWithRetry(requestId);
            return CompletableFuture.completedFuture(null);
        } catch (Exception e) {
            log.error("finally failed compensation for requestId: {}", requestId, e);
            return CompletableFuture.failedFuture(e);
        }
    }

}
