package com.example.poc_for_java.controller;


import com.example.poc_for_java.service.CompensationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/compensate")
@Tag(name = "Compensation Controller", description = "Initiate compensation for a request")
@Slf4j
public class MyController {

    private final CompensationService compensationService;

    @GetMapping("/{requestId}")
    public ResponseEntity<String> compensate(@PathVariable Long requestId) {
        log.info("Compensation request received for requestId: {}", requestId);
        compensationService.compensateAsync(requestId);
        return ResponseEntity.ok("Compensation done for requestId: " + requestId);
    }
}
