package com.truetree.app.web.system;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SystemCheckController {

    @GetMapping("/system/check")
    @ResponseStatus(HttpStatus.OK)
    public void systemCheck() {
        log.info("System Check");
    }

}
