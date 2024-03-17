package top.meethigher.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class TestController {


    @Value("${datasource.config.test:nihao}")
    private String test;



    @GetMapping("/get")
    public String get() {
        return test;
    }
}
