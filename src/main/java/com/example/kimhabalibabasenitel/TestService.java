package com.example.kimhabalibabasenitel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    // method is identified as a Sentinel
    @SentinelResource(value = "sayHello", blockHandler = "blockHandlerMethod", fallback = "doFallback", fallbackClass = TestFallbackClass.class)
    public String sayHello(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        return "Hello, " + name;

    }

    //fallback
    public String doFallback(String name, Throwable t) {
        if (t instanceof BlockException) {
            return "Oops, blocked by Sentinel";
        } else {
            return "Oops, something went wrong: " + t.getMessage();
        }
    }

    public String blockHandlerMethod(String name, BlockException ex) {
        System.out.println("blockHandlerMethod "+ ex);
        return "Blocked!";
    }
}