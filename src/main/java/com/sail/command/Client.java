package com.sail.command;

/**
 * @author yangfan
 * @date 2017/08/16
 */
public class Client {
    public static void main(String[] args) {
        Command command = new ConcreteCommand(new Receiver());
        Invoker invoker = new Invoker();
        invoker.setCommand(command);
        invoker.action();
    }
}
