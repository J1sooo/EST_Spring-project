package com.estsoft.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class TestController {
//    @GetMapping("/test")
//    public ResponseEntity<Map<String, Object>> test(@RequestParam String query) {
//        log.info("TestController testFilter");
//        Map<String, Object> resultMap = new HashMap<>();
//        resultMap.put("query", query);
//
//        return new ResponseEntity<>(resultMap, HttpStatus.OK);
//    }
    @GetMapping("/test")
    public String testFilterInterceptor(@RequestParam String query,
                                        ModelAndView modelAndView) {
        log.info("##TestController testFilter()");

        modelAndView.addObject("query", query);
        return "test";
    }
}
