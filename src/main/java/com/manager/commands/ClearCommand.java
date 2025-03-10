package com.manager.commands;

import com.manager.commands.base.Command;
import com.manager.commands.base.Environment;

import java.io.InputStream;
import java.io.PrintStream;

public class ClearCommand extends Command {
    public ClearCommand() {
        super("clear");
    }

    @Override
    public void execute(Environment env, PrintStream stdout, InputStream stdin, String[] commandArgs) {
        env.getTickets().clear();
        stdout.println("Коллекция билетов очищена.");
    }

    @Override
    public String getHelp() {
        return "Эта команда очищает коллекцию.";
    }
}
