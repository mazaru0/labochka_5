package com.manager.commands;

import com.manager.commands.base.Command;
import com.manager.commands.base.Environment;
import com.manager.commands.exception.CommandException;
import com.manager.model.Ticket;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class GroupCountingByCoordinatesCommand extends Command {

    protected GroupCountingByCoordinatesCommand(String name) {
        super(name);
    }

    /**
     * Сгруппировать билеты по координатам
     * @param env
     * @param stdout
     * @param stdin
     * @param commandArgs
     * @throws CommandException
     */
    @Override
    public void execute(Environment env, PrintStream stdout, InputStream stdin, String[] commandArgs) throws CommandException {
        Map<Long, Long> groupedByX = env.getTickets().stream()
                .collect(Collectors.groupingBy(Ticket::getCoorX, Collectors.counting()));
            stdout.println("Группировка по координате х: ");
            groupedByX.forEach((x, count) -> stdout.println("Координата х = " + x + " - количество элементов в группе " + count));
        };

    /**
     * Метод для получения справки в команде help
     * @return
     */

    @Override
    public String getHelp() {
        return "Эта команда группирует элементы коллекции по значению поля coordinate и выводит количество элементов в каждой группе";
    }
}