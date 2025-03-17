package com.manager.commands;

import com.manager.TicketManager;
import com.manager.commands.base.Command;
import com.manager.commands.base.Environment;
import com.manager.commands.exception.CommandException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.*;

public class ExecuteScriptCommand extends Command {
    private final Scanner scanner;

    public ExecuteScriptCommand() {
        super("execute_script");
        this.scanner = new Scanner(System.in);
    }

    /**
     * Считывает и исполняет скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
     * @param env
     * @param stdout
     * @param stdin
     * @param commandArgs
     * @throws CommandException
     */

    @Override
    public void execute(Environment env, PrintStream stdout, InputStream stdin, String[] commandArgs) throws CommandException {
     String name;
        if (commandArgs.length == 0) {
            while (true) {
                System.out.println("Введите id (имя файла) либо введите exit для выхода из команды:");
                String input = scanner.nextLine().trim(); // Считываем ввод и убираем пробелы

                if (input.equalsIgnoreCase("exit")) { // Проверяем, не ввёл ли пользователь "exit"
                    System.out.println("Выход из команды.");
                    return; // Выход из метода или команды
                }

                // Проверяем, что ввод не пустой
                if (input.isEmpty()) {
                    System.err.println("Ошибка: ID не может быть пустым."); // Сообщаем об ошибке
                    continue; // Пропускаем текущую итерацию и продолжаем цикл
                }

                // Создаем объект File и проверяем его существование
                File file = new File(input);
                if (!file.exists()) {
                    System.err.println("Ошибка: Файл \"" + input + "\" не существует в директории. ");
                    continue; // Пропускаем текущую итерацию и продолжаем цикл
                }

                // Если файл существует, присваиваем значение переменной id
                name = input;
                System.out.println("Файл \"" + name + "\" успешно найден.");
                break; // Если успешно, выходим из цикла
            }
        }
        else{
            name = commandArgs[0];

        }
       ArrayList<String> itemsList = new ArrayList<>();
        Scanner scanner = new Scanner(stdin);
        try (BufferedReader file = new BufferedReader(new FileReader(name))) {
            if (file == null) {
                throw new CommandException ("Ошибка: Переменная окружения не установлена!");
            }
                    String line;

                    while ((line = file.readLine()) != null) {
                        // Если в строке есть <item>, то читаем ее
                        if (line.trim().startsWith("<item>") && line.trim().endsWith("</item>")) {
                            // Читаю текст от 7 элемента до элемента длина строки -7
                            String item = line.trim().substring(6, line.trim().length() - 7);
                            itemsList.add(item);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Делаю ArrayList в массив String
                String[] itemsArray = itemsList.toArray(new String[0]);
                //Мапу загрузила
        HashMap<String, Command> stringCommandHashMap = env.getStringCommandHashMap();
        for ( String element : itemsArray){
        if (!stringCommandHashMap.containsKey(element)){
            System.err.println(" Такой команды нет! Исправьте данные в файле");

        }
        else {
            stdout.println("Вывод команды: " + element);
            stringCommandHashMap.get(element).execute(env,stdout,stdin,commandArgs);
        }
        }}


    /**
     * Справка для команды help
     * @return
     */
    @Override
    public String getHelp() {
        return "Эта команда читает скрипт данного файла и выполняет из него команды";
    }}
