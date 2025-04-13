package com.estsoft.demo.controller;

import com.estsoft.demo.service.ExternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class ExternalApiController {
    private final ExternalService externalService;

    @GetMapping("/api/external")
    public ResponseEntity<Void> callExternal() {
        externalService.call();

        return ResponseEntity.ok().build();
    }
}
