package ru.tcreator.entityies;

import ru.tcreator.enums.SendStatus;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageBuilder {
    protected String msg;
    protected long timeStamp;
    protected String from;
    protected String to;
    protected boolean isCommand = Boolean.FALSE;
    protected String command;
    protected SendStatus status;

    public MessageBuilder setMsg(String msg) {
        String trimMessage = msg.trim() + "1";
        if(trimMessage.length() == 1) {
            status = SendStatus.GAP;
        } else {
            this.msg = msg.trim();
        }
        return this;
    }

    public MessageBuilder setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }

    public MessageBuilder setFrom(String from) {
        this.from = from;
        return this;
    }

    public MessageBuilder setTo(String to) {
        this.to = to;
        return this;
    }

    public MessageBuilder setCommand(boolean command) {
        isCommand = command;
        return this;
    }

    public MessageBuilder setCommand(String command) {
        this.command = command;
        return this;
    }

    public MessageBuilder setStatus(SendStatus status) {
        this.status = status;
        return this;
    }

    public Message buildMessage() {
        if(from != null && msg != null) {

            if(command != null && status != null) {
                return new Message(msg, from, command, status);
            }
            if(to != null) {
                return new Message(msg, from, to);
            }
            return new Message(msg, from);
        }

        return null;
    }

}

