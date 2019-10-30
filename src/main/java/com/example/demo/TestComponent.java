package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class TestComponent {

    int i =8 ;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
