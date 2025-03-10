package com.manager.commands;

import com.manager.commands.base.Command;
import com.manager.commands.base.Environment;
import com.manager.commands.exception.CommandException;
import com.manager.model.Ticket;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.stream.Collectors;

public class PrintUniquePriceCommand extends Command {
    public PrintUniquePriceCommand() {
        super("print_unique_price");
    }

    /**
     * Выводит уникальные значения поля price всех элементов в коллекции
     * @param env
     * @param stdout
     * @param stdin
     * @param commandArgs
     * @throws CommandException
     */
    @Override
    public void execute(Environment env, PrintStream stdout, InputStream stdin, String[] commandArgs) throws CommandException {
        List<Long> prices = env.getTickets().stream()
                .map(Ticket::getPrice) // Получаем имена
                .toList(); // Собираем в список

       // prices.forEach(System.out::println); // Выводим каждое имя
        System.out.println("Все цены билетов: ");
        for (Long element : prices) {
            System.out.print(element + " ");
        }

    }

    /**
     * Справка для команды help
     * @return
     */

    @Override
    public String getHelp() {
        return " Выводит уникальные значения поля price всех элементов в коллекции";
    }
}