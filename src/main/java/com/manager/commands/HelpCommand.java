package com.manager.commands;

import com.manager.commands.base.Command;
import com.manager.commands.base.Environment;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;

public class HelpCommand extends Command {
    public HelpCommand() {
        super("help");
    }

    /**
     * Выводит справку по доступным командам
     * @param env
     * @param stdout
     * @param stdin
     * @param commandArgs
     */

    @Override
    public void execute(Environment env, PrintStream stdout, InputStream stdin, String[] commandArgs) {
        HashMap<String, Command> stringCommandHashMap = env.getStringCommandHashMap();
        if (commandArgs.length == 1 && stringCommandHashMap.containsKey(commandArgs[0])) {
            Command command = stringCommandHashMap.get(commandArgs[0]);
            stdout.println(command.getHelp());
        } else {
            stringCommandHashMap.forEach((key, value) -> {
                stdout.println(key + ": " + value.getHelp());
            });
        }
    }
    /**
     * Справка для команды help
     * @return
     */

    @Override
    public String getHelp() {
        return "Эта команда объясняет доступные команды";
    }
}
