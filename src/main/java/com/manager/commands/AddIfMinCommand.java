package com.manager.commands;
import com.manager.commands.base.Command;
import com.manager.commands.base.Environment;
import com.manager.commands.exception.CommandException;
import com.manager.model.Coordinates;
import com.manager.model.Ticket;
import com.manager.model.TicketType;
import com.manager.model.Venue;

import java.lang.reflect.Field;
import java.math.*;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.min;

public class AddIfMinCommand extends Command {
    private final Scanner scanner;

    public AddIfMinCommand() {
        super("add_if_min");
        this.scanner = new Scanner(System.in);
    }

    /**
     *  Добавляю новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
     * @param env
     * @param stdout
     * @param stdin
     * @param commandArgs
     * @throws CommandException
     */
    @Override
    public void execute(Environment env, PrintStream stdout, InputStream stdin, String[] commandArgs) throws CommandException {
        try{
        int number;
        if (commandArgs.length == 0) {
            while (true) {
                stdout.println("Введите число либо введите exit для выхода из команды");
                String input = scanner.nextLine().trim(); // Считываем ввод и убираем пробелы

                if (input.equalsIgnoreCase("exit")) { // Проверяем, не ввёл ли пользователь "exit"
                    stdout.println("Выход из команды.");
                    return; // Выход из метода или команды
                }

                try {
                    number = Integer.parseInt(input); // Пытаемся преобразовать ввод в число
                    break; // Если успешно, выходим из цикла
                } catch (NumberFormatException e) {
                    System.err.println("Ошибка: параметр должен быть числом."); // Выводим ошибку, если ввод не число
                }
            }
        }

        else {
            number = Integer.parseInt(commandArgs[0]);
        }

        if (env.getTickets().isEmpty()) {
            throw new CommandException("Коллекция пуста! Попробуйте другую команду");
        }
        long min = 100000;
        try{
        for (Ticket element: env.getTickets()){
            long id = element.getId();
            long x = element.getCoorX();
            long y = element.getCoordinates().getY();
            long price = element.getPrice();
            long[] numbers = {id, x, y, price};

            long minin= Arrays.stream(numbers).min().getAsLong();
            min=Math.min(min, minin);

            }
        }
        catch (IllegalArgumentException e) {
            System.err.println("Ошибка: Неверный ввод. Попробуйте снова.");
        }
        if (number<min) {
            stdout.println("Добавим новый элемент в коллекцию: ");
            HashMap<String, Command> stringCommandHashMap = env.getStringCommandHashMap();
            stringCommandHashMap.get("add").execute(env,stdout,stdin,commandArgs);
        } else {
            throw new CommandException("Новый билет не может быть создан!");
        }}
         catch (NumberFormatException e) {
            System.err.println("Ошибка: ID должен быть числом.");}}
    /**
     * Справка для команды help
     * @return
     */
    @Override
    public String getHelp() {
        return "";
    }

}