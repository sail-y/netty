package com.sail.decorator;

/**
 * @author yangfan
 * @date 2017/08/12
 */
public class Client {

    public static void main(String[] args) {
        Component component = new ConcreteDecorator2(new ConcreteDecorator1(new ConcreteComponent()));

        component.doSomething();
    }
}
