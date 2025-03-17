package com.manager.commands;

import com.manager.model.Coordinates;
import com.manager.model.TicketType;
import com.manager.model.Venue;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.crypto.Data;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Date;

public class XmlConverter {
    private long ticketId;
    private String ticketName;
    private long ticketPrice;
    private long coordX;
    private long coordY;
    private Date creationDate;
    private TicketType type;
    private long venueId;
    private String venueName;
    private int venueCapacity;

    public XmlConverter(long ticketId, String ticketName, long ticketPrice, long coordX,
                        long coordY, Date creationDate, TicketType type,
                        long venueId, String venueName, int venueCapacity) throws Exception {
        this.ticketId = ticketId;
        this.ticketName = ticketName;
        this.ticketPrice = ticketPrice;
        this.coordX = coordX;
        this.coordY = coordY;
        this.creationDate = creationDate;
        this.type = type;
        this.venueId = venueId;
        this.venueName = venueName;
        this.venueCapacity = venueCapacity;
    }

    public String createXml() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        // Создаем элементы XML
        Element tickets = document.createElement("Tickets");
        Element ticket = document.createElement("Ticket");

        Element id = document.createElement("id");
        id.appendChild(document.createTextNode(String.valueOf(ticketId)));
        Element name = document.createElement("name");
        name.appendChild(document.createTextNode(ticketName));
        Element price = document.createElement("price");
        price.appendChild(document.createTextNode(String.valueOf(ticketPrice)));

        Element coordinates = document.createElement("coordinates");
        Element x = document.createElement("x");
        x.appendChild(document.createTextNode(String.valueOf(coordX)));
        Element y = document.createElement("y");
        y.appendChild(document.createTextNode(String.valueOf(coordY)));

        coordinates.appendChild(x);
        coordinates.appendChild(y);

        Element creationDate = document.createElement("creationDate");
        creationDate.appendChild(document.createTextNode(String.valueOf(this.creationDate)));
        Element type = document.createElement("type");
        type.appendChild(document.createTextNode(String.valueOf(this.type)));

        Element venue = document.createElement("venue");
        Element venueId = document.createElement("id");
        venueId.appendChild(document.createTextNode(String.valueOf(this.venueId)));
        Element venueName = document.createElement("name");
        venueName.appendChild(document.createTextNode(this.venueName));
        Element capacity = document.createElement("capacity");
        capacity.appendChild(document.createTextNode(String.valueOf(this.venueCapacity)));

        // Собираем иерархию
        venue.appendChild(venueId);
        venue.appendChild(venueName);
        venue.appendChild(capacity);

        // Связываем элементы
        ticket.appendChild(id);
        ticket.appendChild(name);
        ticket.appendChild(price);
        ticket.appendChild(coordinates);
        ticket.appendChild(creationDate);
        ticket.appendChild(type);
        ticket.appendChild(venue);

        tickets.appendChild(ticket);
        document.appendChild(tickets);

        // Сохраняем XML-документ в файл
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(new DOMSource(document), new StreamResult(new File("tickets.xml")));

        return "XML-файл успешно создан";
    }}