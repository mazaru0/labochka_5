package com.manager.commands;

import com.manager.commands.base.Command;
import com.manager.commands.base.Environment;

import java.io.InputStream;
import java.io.PrintStream;

public class ClearCommand extends Command {
    public ClearCommand() {
        super("clear");
    }

    /**
     * Очищает коллекцию
     * @param env
     * @param stdout
     * @param stdin
     * @param commandArgs
     */

    @Override
    public void execute(Environment env, PrintStream stdout, InputStream stdin, String[] commandArgs) {
        env.getTickets().clear();
        stdout.println("Коллекция билетов очищена.");
    }
    /**
     * Справка для команды help
     * @return
     */
    @Override
    public String getHelp() {
        return "Эта команда очищает коллекцию.";
    }
}
