package com.manager.commands;
import com.manager.commands.base.Command;
import com.manager.commands.base.Environment;
import com.manager.commands.exception.CommandException;
import com.manager.model.Coordinates;
import com.manager.model.Ticket;
import com.manager.model.TicketType;
import com.manager.model.Venue;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class AddIfMinCommand extends Command {
    private final Scanner scanner;

    public AddIfMinCommand() {
        super("add_if_min");
        this.scanner = new Scanner(System.in);
    }
    @Override
    public void execute(Environment env, PrintStream stdout, InputStream stdin, String[] commandArgs) throws CommandException { try {
        PriorityQueue<Ticket> updatedQueue = new PriorityQueue<>(Comparator.comparingLong(Ticket::getId));
        boolean found = false;

        while (!env.getTickets().isEmpty()) {
            Ticket ticket = env.getTickets().poll();
            if (ticket.getId() == Integer.parseInt(commandArgs[0]) && Integer.parseInt(commandArgs[0]) > 0) {
                int id = Integer.parseInt(commandArgs[0]);
                try {
                    System.out.println("Для нового билета введен id " + Integer.parseInt(commandArgs[0]));

                    System.out.print("Введите имя билета: ");
                    String name = scanner.nextLine();
                    if (name == null || name.trim().isEmpty()) {
                        throw new IllegalArgumentException("Имя билета не может быть пустым.");
                    }

                    System.out.print("Введите цену билета (больше 0): ");
                    long price = Long.parseLong(scanner.nextLine());
                    if (price <= 0) {
                        throw new IllegalArgumentException("Цена должна быть больше 0.");
                    }

                    System.out.println("Введите координаты:");
                    System.out.print("Введите x (больше -9): ");
                    int x = Integer.parseInt(scanner.nextLine());
                    if (x <= -9) {
                        throw new IllegalArgumentException("Значение x должно быть больше -9.");
                    }

                    System.out.print("Введите y (больше -133): ");
                    int y = Integer.parseInt(scanner.nextLine());
                    if (y <= -133) {
                        throw new IllegalArgumentException("Значение y должно быть больше -133.");
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
                    String venueName = scanner.nextLine();
                    if (venueName == null || venueName.trim().isEmpty()) {
                        throw new IllegalArgumentException("Название места не может быть пустым.");
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

            }
            if (ticket.getCoordinates().getX() > Integer.parseInt(commandArgs[0]) && Integer.parseInt(commandArgs[0]) >= -9) {
                int x = Integer.parseInt(commandArgs[0]);
                System.out.println(x);
                try {
                    System.out.println("Для нового билета введена координата по х " + Integer.parseInt(commandArgs[0]));

                    System.out.print("Введите id билета: ");
                    String id = scanner.nextLine();
                    if (id == null || id.trim().isEmpty()) {
                        throw new IllegalArgumentException("Id билета не может быть пустым.");
                    }

                    System.out.print("Введите имя билета: ");
                    String name = scanner.nextLine();
                    if (name == null || name.trim().isEmpty()) {
                        throw new IllegalArgumentException("Имя билета не может быть пустым.");
                    }

                    System.out.print("Введите цену билета (больше 0): ");
                    long price = Long.parseLong(scanner.nextLine());
                    if (price <= 0) {
                        throw new IllegalArgumentException("Цена должна быть больше 0.");
                    }

                    System.out.print("Введите y (больше -133): ");
                    int y = Integer.parseInt(scanner.nextLine());
                    if (y <= -133) {
                        throw new IllegalArgumentException("Значение y должно быть больше -133.");
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
                    String venueName = scanner.nextLine();
                    if (venueName == null || venueName.trim().isEmpty()) {
                        throw new IllegalArgumentException("Название места не может быть пустым.");
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
            }
            if (ticket.getCoordinates().getY() > Integer.parseInt(commandArgs[0]) && Integer.parseInt(commandArgs[0]) >= -133) {
                int y = Integer.parseInt(commandArgs[0]);
                try {
                    System.out.println("Для нового билета введена координата по у " + Integer.parseInt(commandArgs[0]));

                    System.out.print("Введите id билета: ");
                    String id = scanner.nextLine();
                    if (id == null || id.trim().isEmpty()) {
                        throw new IllegalArgumentException("Id билета не может быть пустым.");
                    }

                    System.out.print("Введите имя билета: ");
                    String name = scanner.nextLine();
                    if (name == null || name.trim().isEmpty()) {
                        throw new IllegalArgumentException("Имя билета не может быть пустым.");
                    }

                    System.out.print("Введите цену билета (больше 0): ");
                    long price = Long.parseLong(scanner.nextLine());
                    if (price <= 0) {
                        throw new IllegalArgumentException("Цена должна быть больше 0.");
                    }

                    System.out.println("Введите координаты:");
                    System.out.print("Введите x (больше -9): ");
                    int x = Integer.parseInt(scanner.nextLine());
                    if (x <= -9) {
                        throw new IllegalArgumentException("Значение x должно быть больше -9.");
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
                    String venueName = scanner.nextLine();
                    if (venueName == null || venueName.trim().isEmpty()) {
                        throw new IllegalArgumentException("Название места не может быть пустым.");
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

            }else {
                System.err.println("Ошибка: параметр сравнения должен быть числом и подходить по параметрам.");

                updatedQueue.add(ticket);
            }}
        env.setTickets(updatedQueue);

        if (found) {
            stdout.println("Билет был обновлён.");
        }
    } catch (NumberFormatException e) {
        System.err.println("Ошибка: параметр сравнения должен быть числом.");
    }

}




    @Override
    public String getHelp() {
        return "";
    }

}