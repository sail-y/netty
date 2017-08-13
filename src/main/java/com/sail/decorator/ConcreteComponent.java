package com.sail.decorator;

/**
 * @author yangfan
 * @date 2017/08/12
 */
public class ConcreteComponent implements Component{
    @Override
    public void doSomething() {
        System.out.println("功能A");
    }
}
