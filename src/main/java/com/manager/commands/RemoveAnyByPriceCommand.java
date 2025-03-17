package com.manager.commands;

import com.manager.commands.base.Command;
import com.manager.commands.base.Environment;
import com.manager.model.Ticket;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class RemoveAnyByPriceCommand extends Command {
    private final Scanner scanner;

    public RemoveAnyByPriceCommand() {
        super("remove_any_by_price");
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void execute(Environment env, PrintStream stdout, InputStream stdin, String[] commandArgs) {
        try {
            int price=0;
            if (commandArgs.length == 0) {
                while (true) {
                    stdout.println("Введите число либо введите exit для выхода из команды");
                    String input = scanner.nextLine().trim(); // Считываем ввод и убираем пробелы

                    if (input.equalsIgnoreCase("exit")) { // Проверяем, не ввёл ли пользователь "exit"
                        stdout.println("Выход из команды.");
                        return; // Выход из метода или команды
                    }

                    try {
                        price = Integer.parseInt(input); // Пытаемся преобразовать ввод в число
                        break; // Если успешно, выходим из цикла
                    } catch (NumberFormatException e) {
                        System.err.println("Ошибка: параметр должен быть числом."); // Выводим ошибку, если ввод не число
                    }
                }}

            for (Ticket ticket : env.getTickets()) {
                if (ticket.getPrice() == Integer.parseInt(commandArgs.length == 0 ? String.valueOf(price) : commandArgs[0])) {
                    env.getTickets().remove(ticket);
                    stdout.println("Удален билет с ценой: " + Integer.parseInt(commandArgs.length == 0 ? String.valueOf(price) : commandArgs[0]));
                    return;
                }
            }
            stdout.println("Билет с ценой " + Integer.parseInt(commandArgs.length == 0 ? String.valueOf(price) : commandArgs[0]) + " не найден.");
        } catch (NumberFormatException e) {
            System.err.println("Ошибка: Цена должна быть числом.");
        }

}

    @Override
    public String getHelp() {
        return "Эта команда удаляет элемент с указанной ценой. Пример использования: remove_any_by_price 239";
    }
}
