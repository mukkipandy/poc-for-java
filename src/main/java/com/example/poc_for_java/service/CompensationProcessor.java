package com.example.poc_for_java.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class CompensationProcessor {


    @Retryable(
            retryFor = {DataAccessException.class},
            maxAttempts = 15,
            backoff = @Backoff(delay = 1, multiplier = 2, maxDelay = 30000, random = true)
    )
    @Transactional
    public void performCompensationWithRetry(Long requestId) {
        log.info("Attempting compensation for requestId: {}", requestId);

        // Simulate failure for testing
        if (Math.random() > 0) {
            throw new DataAccessException("Simulated DB error") {
            };
        }

        log.info("Compensation successful for requestId: {}", requestId);
    }


    @Recover
    public void handlePermanentFailure(DataAccessException ex, Long requestId) {
        log.error("🚨 RECOVERY CALLED for requestId: {}", requestId, ex);
        // This will now be called!
    }
}
