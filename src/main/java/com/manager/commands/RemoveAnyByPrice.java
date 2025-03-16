package com.manager.commands;

import com.manager.commands.base.Command;
import com.manager.commands.base.Environment;
import com.manager.model.Ticket;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class RemoveAnyByPrice extends Command {
    private final Scanner scanner;

    public RemoveAnyByPrice() {
        super("remove_any_by_price");
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void execute(Environment env, PrintStream stdout, InputStream stdin, String[] commandArgs) {
        try {
            String price = "";

            if (commandArgs.length == 0){
                stdout.println("Введите цену");
                price = scanner.nextLine();
            }

            for (Ticket ticket : env.getTickets()) {
                if (ticket.getPrice() == Integer.parseInt(commandArgs.length == 0 ? price : commandArgs[0])) {
                    env.getTickets().remove(ticket);
                    stdout.println("Удален билет с ценой: " + Integer.parseInt(commandArgs.length == 0 ? price : commandArgs[0]));
                    return;
                }
            }
            stdout.println("Билет с ценой " + Integer.parseInt(commandArgs.length == 0 ? price : commandArgs[0]) + " не найден.");
        } catch (NumberFormatException e) {
            System.err.println("Ошибка: Цена должна быть числом.");
        }
    }

    @Override
    public String getHelp() {
        return "Эта команда удаляет элемент с указанной ценой. Пример использования: remove_any_by_price 239";
    }
}
