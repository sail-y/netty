package com.sail.command;

/**
 * @author yangfan
 * @date 2017/08/16
 */
public class ConcreteCommand implements Command {


    private Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.action();
    }
}
