package com.manager.commands;

import com.manager.commands.base.Command;
import com.manager.commands.base.Environment;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Date;

public class InfoCommand extends Command {
    public InfoCommand() {
        super("info");
    }

    /**
     * Выводит в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
     * @param env
     * @param stdout
     * @param stdin
     * @param commandArgs
     */

    @Override
    public void execute(Environment env, PrintStream stdout, InputStream stdin, String[] commandArgs) {
        stdout.println("Тип коллекции: " + env.getTickets().getClass().getSimpleName());
        stdout.println("Дата инициализации: " + new Date());
        stdout.println("Количество элементов: " + env.getTickets().size());
    }
    /**
     * Справка для команды help
     * @return
     */

    @Override
    public String getHelp() {
        return "Эта команда покажет основную информацию о коллекции.";
    }
}
