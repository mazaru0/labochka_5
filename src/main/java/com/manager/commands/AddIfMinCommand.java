package com.manager.commands;
import com.manager.commands.base.Command;
import com.manager.commands.base.Environment;
import com.manager.commands.exception.CommandException;
import com.manager.model.Coordinates;
import com.manager.model.Ticket;
import com.manager.model.TicketType;
import com.manager.model.Venue;
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
    @Override
    public void execute(Environment env, PrintStream stdout, InputStream stdin, String[] commandArgs) throws CommandException {{
        PriorityQueue<Ticket> updatedQueue = new PriorityQueue<>(Comparator.comparingLong(Ticket::getId));
        boolean found = false;
            Ticket ticket = env.getTickets().poll();
        assert ticket != null;
        long x= ticket.getCoordinates().getX();
            long price = ticket.getPrice();
            long y= ticket.getCoordinates().getY();

            System.out.println(Math.min(Math.min(Math.min(ticket.getId(),y),x),price));
        }}
    @Override
    public String getHelp() {
        return "";
    }

}