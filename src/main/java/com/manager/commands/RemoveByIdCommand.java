package com.manager.commands;

import com.manager.commands.base.Command;
import com.manager.commands.base.Environment;
import com.manager.model.Ticket;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class RemoveByIdCommand extends Command {
    private final Scanner scanner;
    public RemoveByIdCommand() {
        super("remove_by_id");
        this.scanner = new Scanner(System.in);
    }

    /**
     * Удаляет элемент из коллекции по его id
     * @param env
     * @param stdout
     * @param stdin
     * @param commandArgs
     */

    @Override
    public void execute(Environment env, PrintStream stdout, InputStream stdin, String[] commandArgs) {
        try {
            int id=0;
            PriorityQueue<Ticket> updatedQueue = new PriorityQueue<>(Comparator.comparingLong(Ticket::getId));
            boolean found = false;
            if (commandArgs.length == 0) {
                while (true) {
                    stdout.println("Введите id либо введите exit для выхода из команды");
                    String input = scanner.nextLine().trim(); // Считываем ввод и убираем пробелы

                    if (input.equalsIgnoreCase("exit")) { // Проверяем, не ввёл ли пользователь "exit"
                        stdout.println("Выход из команды.");
                        return; // Выход из метода или команды
                    }

                    try {
                        // Попытка преобразовать input в число
                        id = Integer.parseInt(input);
                        break; // Если успешно, выходим из цикла
                    } catch (NumberFormatException e) {
                        System.err.println("Ошибка: ID должен быть числом."); // Выводим ошибку, если ввод не число
                    }
                }
            }

            while (!env.getTickets().isEmpty()) {
                Ticket ticket = env.getTickets().poll();
                if (ticket.getId() == Integer.parseInt(String.valueOf(id))) {
                    found = true;
                } else {
                    updatedQueue.add(ticket);
                }
            }

            env.setTickets(updatedQueue);

            if (found) {
                stdout.println("Билет с ID " + Integer.parseInt(commandArgs[0]) + " удален.");
            } else {
                stdout.println("Билет с ID " + Integer.parseInt(commandArgs[0]) + " не найден.");
            }
        } catch (NumberFormatException e) {
            System.err.println("Ошибка: ID должен быть числом.");
        }
    }
    /**
     * Справка для команды help
     * @return
     */

    @Override
    public String getHelp() {
        return "Эта команда удаляет элемент с указанным ID. Пример использования: remove_by_id 15";
    }
}
