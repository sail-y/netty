package com.sail.decorator;

/**
 * @author yangfan
 * @date 2017/08/12
 */
public class Decorator implements Component{

    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void doSomething() {
        component.doSomething();
    }
}
