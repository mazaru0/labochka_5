package com.manager.commands;

import com.manager.commands.base.Command;
import com.manager.commands.base.Environment;
import com.manager.model.Coordinates;
import com.manager.model.Ticket;
import com.manager.model.TicketType;
import com.manager.model.Venue;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.Instant;
import java.util.Comparator;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Scanner;

public class UpdateIdCommand extends Command {
    private final Scanner scanner;

    public UpdateIdCommand() {
        super("update");
        this.scanner = new Scanner(System.in);
    }


    @Override
    public void execute(Environment env, PrintStream stdout, InputStream stdin, String[] commandArgs) {
        try {
            PriorityQueue<Ticket> updatedQueue = new PriorityQueue<>(Comparator.comparingLong(Ticket::getId));
            boolean found = false;
            String id = "";

            if (commandArgs.length == 0){
                stdout.println("Введите id");
                id = scanner.nextLine();
            }
            while (!env.getTickets().isEmpty()) {
                Ticket ticket = env.getTickets().poll();
                if (ticket.getId() == Integer.parseInt(commandArgs.length == 0 ? id : commandArgs[0])) {
                    try {
                        System.out.println("Введите данные для этого билета сначала:");

                        System.out.print("Введите имя билета: ");
                        String name = null;
                        while (name == null || name.trim().isEmpty()) {
                            try {
                                name = scanner.nextLine();
                            } catch (IllegalArgumentException e) {
                                System.err.println("Некорректное имя билета, попробуйте снова");
                            }
                        }

                        System.out.print("Введите цену билета (больше 0): ");
                        long price = 0;
                        while (price <= 0) {
                            try {
                                price = Long.parseLong(scanner.nextLine());
                                if (price <= 0) {
                                    throw new IllegalArgumentException("Цена должна быть больше нуля");
                                }
                            } catch (IllegalArgumentException e) {
                                System.err.println("Некорректоная цена билета, попробуйте снова");
                            }
                        }

                        System.out.println("Введите координаты:");
                        System.out.print("Введите x (больше -9): ");
                        int x = -10;
                        while (x <= -9) {
                            try {
                                x = Integer.parseInt(scanner.nextLine());
                                if (x <= -9) {
                                    throw new IllegalArgumentException("X должнен быть больше -9");
                                }
                            } catch (IllegalArgumentException e) {
                                System.err.println("Некорректоный x, попробуйте снова");
                            }
                        }

                        System.out.print("Введите y (больше -133): ");
                        int y = -150;
                        while (y <= -133) {
                            try {
                                y = Integer.parseInt(scanner.nextLine());
                                if (y <= -133) {
                                    throw new IllegalArgumentException("Y должнен быть больше -133");
                                }
                            } catch (IllegalArgumentException e) {
                                System.err.println("Некорректоный y, попробуйте снова");
                            }
                        }

                        Coordinates coordinates = new Coordinates(x, y);

                        TicketType type = null;
                        boolean validType = false;
                        while (!validType) {
                            try {
                                System.out.println("Выберите тип билета (VIP, USUAL, BUDGETARY, CHEAP):");
                                String typeInput = scanner.nextLine().toUpperCase();
                                type = TicketType.valueOf(typeInput);
                                validType = true;
                            } catch (IllegalArgumentException e) {
                                System.err.println("Ошибка: неверный тип билета. Попробуйте снова.");
                            }
                        }

                        System.out.print("Введите название места: ");
                        String venueName = null;
                        while (venueName == null || venueName.trim().isEmpty()) {
                            try {
                                venueName = scanner.nextLine();
                                if (venueName == null || venueName.trim().isEmpty()){
                                    throw new IllegalArgumentException("Название места не может быть пустым.");
                                }
                            } catch (IllegalArgumentException e) {
                                System.err.println("Некорректное название места. Попробуйте снова.");
                            }
                        }
                        System.out.print("Введите вместимость места (может быть пустым): ");
                        String capacityStr = scanner.nextLine();
                        Integer capacity = (capacityStr.isEmpty()) ? null : Integer.parseInt(capacityStr);


                        Venue venue = ticket.getVenue();
                        venue.setName(venueName);
                        venue.setCapacity(capacity);

                        ticket.setName(name);
                        ticket.setCoordinates(coordinates);
                        ticket.setCreationDate(Date.from(Instant.now()));
                        ticket.setPrice(price);
                        ticket.setType(type);
                        ticket.setVenue(venue);

                        System.out.println("Билет успешно обновлён!");

                    } catch (NumberFormatException e) {
                        System.err.println("Ошибка: введено неправильное число.");
                    } catch (IllegalArgumentException e) {
                        System.err.println("Ошибка: " + e.getMessage());
                    } catch (Exception e) {
                        System.err.println("Ошибка при добавлении билета: " + e.getMessage());
                    }
                    updatedQueue.add(ticket);
                    found = true;
                } else {
                    updatedQueue.add(ticket);
                }
            }

            env.setTickets(updatedQueue);

            if (found) {
                stdout.println("Билет с ID " + Integer.parseInt(commandArgs[0]) + " обновлён.");
            } else {
                stdout.println("Билет с ID " + Integer.parseInt(commandArgs[0]) + " не найден.");
            }
        } catch (NumberFormatException e) {
            System.err.println("Ошибка: ID должен быть числом.");
        }

    }

    @Override
    public String getHelp() {
        return "Эта команда изменяет существующий билет из коллекции. Пример использования:\n  update 5\n" +
                "  Введите цену билета (больше 0): 1500\n" +
                "  Введите координаты:\n" +
                "    Введите x (больше -9): 10\n" +
                "    Введите y (больше -133): 50\n" +
                "  Выберите тип билета (VIP, USUAL, BUDGETARY, CHEAP): VIP\n" +
                "  Введите id места: 1\n" +
                "  Введите название места: Концертный зал\n" +
                "  Введите вместимость места (может быть пустым): 500";
    }
}
