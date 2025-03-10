package com.manager.commands;

import com.manager.commands.base.Command;
import com.manager.commands.base.Environment;

import java.io.InputStream;
import java.io.PrintStream;

public class ShowCommand extends Command {
    public ShowCommand() {
        super("show");
    }

    @Override
    public void execute(Environment env, PrintStream stdout, InputStream stdin, String[] commandArgs) {
        env.getTickets().forEach(System.out::println);
    }

    @Override
    public String getHelp() {
        return "Эта команда выводит билеты.";
    }
}
