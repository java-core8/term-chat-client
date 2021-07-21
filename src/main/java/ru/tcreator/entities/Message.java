package ru.tcreator.entities;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    protected String msg;
    protected String time;
    protected long timeStamp;
    protected String formatTime = "hh:mm:ss a";
    protected String from;
    protected String to = "all";
    protected boolean isCommand = Boolean.FALSE;
    protected String command;
    protected String parameterCommand;

    public Message(String msg, String from) {
        this.timeStamp = new Date().getTime();
        DateFormat dateFormat = new SimpleDateFormat(formatTime);
        this.msg = msg;
        this.time = dateFormat.format(new Date(timeStamp));
        this.from = from;
    }

    public Message(String msg, String from, String command) {
        this.timeStamp = new Date().getTime();
        DateFormat dateFormat = new SimpleDateFormat(formatTime);
        this.msg = msg;
        this.time = dateFormat.format(new Date(timeStamp));
        this.from = from;
        this.command = command;
        this.isCommand = Boolean.TRUE;
    }

    public Message(String msg, String from, String command, String parameter) {
        this.timeStamp = new Date().getTime();
        DateFormat dateFormat = new SimpleDateFormat(formatTime);
        this.msg = msg;
        this.time = dateFormat.format(new Date(timeStamp));
        this.from = from;
        this.command = command;
        this.isCommand = Boolean.TRUE;
        parameterCommand = parameter;
    }

    @Override
    public String toString() {
        return "[" + to + "]" + " " + from + " " + time + " >>> " + msg;
    }
}

