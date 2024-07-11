package com.example.kimhabalibabasenitel;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Component;

@Component
public class TestFallbackClass {
    public static String helloFallback(String name, Throwable throwable) {
        if (throwable instanceof BlockException) {
            return "Oops, blocked by Sentinel";
        } else {
            return "Oops, something went wrong: " + throwable.getMessage();
        }
    }
}
