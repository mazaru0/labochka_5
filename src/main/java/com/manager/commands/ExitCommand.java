package com.manager.commands;

import com.manager.commands.base.Command;
import com.manager.commands.base.Environment;

import java.io.InputStream;
import java.io.PrintStream;

public class ExitCommand extends Command {
    public ExitCommand() {
        super("exit");
    }

    /**
     * Завершает программу (без сохранения в файл)
     * @param env
     * @param stdout
     * @param stdin
     * @param commandArgs
     */

    @Override
    public void execute(Environment env, PrintStream stdout, InputStream stdin, String[] commandArgs) {
        System.exit(0);
    }
    /**
     * Справка для команды help
     * @return
     */
    @Override
    public String getHelp() {
        return "Эта команда завершает программу.";
    }
}
