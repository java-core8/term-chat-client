package ru.tcreator.entities;


/**
 * Билдер для {@link Message}.

 */
public class MessageBuilder {
    protected String msg;
    protected String from;
    protected String command;
    protected String parameter;

    public MessageBuilder setMsg(String msg) {
        this.msg = msg.trim();
        return this;
    }

    public MessageBuilder setFrom(String from) {
        this.from = from;
        return this;
    }

    public MessageBuilder setParameter(String parameter) {
        this.parameter = parameter;
        return this;
    }

    public MessageBuilder setCommand(String command) {
        this.command = command;
        return this;
    }

    public Message buildMessage() {
        if(from != null && msg != null) {
            if(command != null) {
                if(parameter != null) {
                    return new Message(msg, from, command, parameter);
                }
                return new Message(msg, from, command);
            }
            return new Message(msg, from);
        }
        if(command != null && from != null) {
            if(parameter != null) {
                return new Message(null, from, command, parameter);
            }
            return new Message(null, from, command);
        }
        return null;
    }

}

