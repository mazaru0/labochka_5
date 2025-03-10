package com.manager.commands;

import com.manager.commands.base.Command;
import com.manager.commands.base.Environment;
import com.manager.model.Ticket;

import java.io.InputStream;
import java.io.PrintStream;

public class RemoveHeadCommand extends Command {
    public RemoveHeadCommand() {
        super("remove_head");
    }

    /**
     * Выводит первый элемент коллекции и удаляет его
     * @param env
     * @param stdout
     * @param stdin
     * @param commandArgs
     */

    @Override
    public void execute(Environment env, PrintStream stdout, InputStream stdin, String[] commandArgs) {
        if (env.getTickets().isEmpty()) {
            stdout.println("Коллекция пуста, удалить элемент невозможно.");
            return;
        }

        Ticket head = env.getTickets().poll();
        stdout.println("Удален первый элемент: " + head);
    }
    /**
     * Справка для команды help
     * @return
     */

    @Override
    public String getHelp() {
        return "Эта команда выводит и удаляет первый элемент коллекции.";
    }
}
