package com.manager.commands;

import com.manager.commands.base.Command;
import com.manager.commands.base.Environment;

import java.io.InputStream;
import java.io.PrintStream;

public class HistoryCommand extends Command {
    public HistoryCommand() {
        super("history");
    }

    /**
     * Выводит последние 7 команд (без их аргументов)
     * @param env
     * @param stdout
     * @param stdin
     * @param commandArgs
     */

    @Override
    public void execute(Environment env, PrintStream stdout, InputStream stdin, String[] commandArgs) {
        System.out.println("Последние команды:");
        if (env.getCommandHistory().isEmpty()) {
            stdout.println("Ещё не было использовано команд.");
        } else {
            int i = 1;
            for (String cmd : env.getCommandHistory()) {
                if (i == env.getCommandHistory().size()){

                    break;
                }
                System.out.println(cmd);
                i++;
            }
        }
    }
    /**
     * Справка для команды help
     * @return
     */

    @Override
    public String getHelp() {
        return "Эта команда показывает последние 7 команд.";
    }
}
