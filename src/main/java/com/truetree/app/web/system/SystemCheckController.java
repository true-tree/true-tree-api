package com.truetree.app.web.system;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemCheckController {

    @GetMapping("/system/check")
    @ResponseStatus(HttpStatus.OK)
    public void systemCheck() {

    }

}
