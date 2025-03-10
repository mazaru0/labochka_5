package com.manager.commands;

import com.manager.commands.base.Command;
import com.manager.commands.base.Environment;
import com.manager.model.Ticket;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class SaveCommand extends Command {
    public SaveCommand() {
        super("save");
    }

    /**
     * Сохраняет коллекцию в файл
     * @param env
     * @param stdout
     * @param stdin
     * @param commandArgs
     */

    @Override
    public void execute(Environment env, PrintStream stdout, InputStream stdin, String[] commandArgs) {
        try (FileWriter writer = new FileWriter(env.getFilePath())) {
            writer.write("<Tickets>\n");
            for (Ticket ticket : env.getTickets()) {
                writer.write(ticket.toXmlString() + "\n");
            }
            writer.write("</Tickets>");
            stdout.println("Файл успешно сохранён.");
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл.");
        }
    }
    /**
     * Справка для команды help
     * @return
     */

    @Override
    public String getHelp() {
        return "Эта команда сохраняет коллекцию в файл (без неё изменения не будут записаны).";
    }
}
