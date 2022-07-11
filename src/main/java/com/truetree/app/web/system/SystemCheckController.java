package com.truetree.app.web.system;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
public class SystemCheckController {

    @GetMapping("/system/check")
    @ResponseStatus(HttpStatus.OK)
    public void systemCheck() {

    }

}
