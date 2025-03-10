package com.manager.commands;

import com.manager.TicketManager;
import com.manager.commands.base.Command;
import com.manager.commands.base.Environment;
import com.manager.commands.exception.CommandException;

import java.io.*;
import java.util.*;

public class ExecuteScriptCommand extends Command {

    public ExecuteScriptCommand() {
        super("execute_script");
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

                try (BufferedReader file = new BufferedReader(new FileReader("commands.xml"))) {
                    String line;

                    while ((line = file.readLine()) != null) {
                        // Проверяем, содержит ли строка открывающий тег <item>
                        if (line.trim().startsWith("<item>") && line.trim().endsWith("</item>")) {
                            // Извлекаем текст между тегами
                            String item = line.trim().substring(6, line.trim().length() - 7);
                            itemsList.add(item);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Преобразуем ArrayList в массив String
                String[] itemsArray = itemsList.toArray(new String[0]);

        HashMap<String, Command> stringCommandHashMap = env.getStringCommandHashMap();
        for ( String element : itemsArray){
        if (stringCommandHashMap.containsKey(element)){
            System.out.println("Вывод команды: " + element);
            stringCommandHashMap.get(element).execute(env,stdout,stdin,commandArgs);

        }
        else {
            System.err.println(" Такой команды нет! Исправьте данные в файле");
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
