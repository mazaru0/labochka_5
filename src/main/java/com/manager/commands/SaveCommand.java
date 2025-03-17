package com.manager.commands;

import com.manager.commands.base.Command;
import com.manager.commands.base.Environment;
import com.manager.model.Ticket;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class SaveCommand extends Command {
    private final Scanner scanner;
    public SaveCommand() {
        super("save");
        this.scanner=new Scanner(System.in);
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
        try{ 
            String resultMessage = "";// Создаем XML с введенными данными
            for (Ticket ticket: env.getTickets()){
        XmlConverter xmlConverter = new XmlConverter(ticket.getId(), ticket.getName(), ticket.getPrice(), ticket.getCoorX(), ticket.getCoordinates().getY(), ticket.getCreationDate(),
                ticket.getType(), ticket.getVenue().getId(), ticket.getVenue().getName(), ticket.getVenue().getCapacity());
            resultMessage = xmlConverter.createXml();} // Сохраняем данные и получаем сообщение
            System.out.println(resultMessage);
    } catch (Exception e) {
        e.printStackTrace();
    }}
    /**
     * Справка для команды help
     * @return
     */

    @Override
    public String getHelp() {
        return "Эта команда сохраняет коллекцию в файл (без неё изменения не будут записаны).";
    }
}
