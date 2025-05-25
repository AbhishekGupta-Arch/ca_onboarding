package com.cloudcounselage.ca_onboarding;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<String> handleError(HttpServletRequest request) {
        Object status = request.getAttribute("jakarta.servlet.error.status_code");
        if (status != null && status.equals(HttpStatus.METHOD_NOT_ALLOWED.value())) {
            return new ResponseEntity<>("Error: Method Not Allowed. Please use POST for /api/ca/onboard", HttpStatus.METHOD_NOT_ALLOWED);
        }
        return new ResponseEntity<>("Error: 404", HttpStatus.NOT_FOUND);
    }
}