package com.lot.serviceImpl;

import com.lot.service.TestInterface;
import org.springframework.stereotype.Service;

@Service
public class TestInterfaceImpl implements TestInterface {
    @Override
    public String demoTest() {
        return "hello demoTest!";
    }
}
