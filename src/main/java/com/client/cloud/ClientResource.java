package com.client.cloud;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/rest/hello/client")
public class ClientResource {


    @Autowired
    private RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "fallback", groupKey = "Hello",
            commandKey = "hello",
            threadPoolKey = "helloThread"
            )
    @GetMapping
    public String hello() {
        String url = "http:/account-server/v1/account";
        return restTemplate.getForObject(url, String.class);
    }

    public String fallback(Throwable hystrixCommand) {
        return "Fall Back Hello world";
    }

}
