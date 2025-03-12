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

                ArrayList<String> itemsList = new ArrayList<>();
        Scanner scanner = new Scanner(stdin);
        String name = commandArgs[0];
        System.out.println(name);
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
            System.out.println("Вывод команды: " + element);
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
