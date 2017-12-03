package com.yarachkin.dock.util;

import com.yarachkin.dock.entity.Ship;
import com.yarachkin.dock.util.validator.DockLineValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DockParser {

    public static List<Ship> parse(List<String> lines) {
        List<Ship> ships = new ArrayList<>();

        lines.forEach(line -> {
            if ( DockLineValidator.validate(line) ) {
                List<String> parsedLine = Arrays.asList(line.split("\\s"));
                Ship ship = new Ship();
                ship.setShipId(IdGenerator.generateShipId());
                ship.setCapacity(Integer.parseInt(parsedLine.get(0)));
                ship.setBoardContainersCounts(Integer.parseInt(parsedLine.get(1)));
                ship.setLoadingContainersCounts(Integer.parseInt(parsedLine.get(2)));
                ship.setUploadingContainersCounts(Integer.parseInt(parsedLine.get(3)));
                ships.add(ship);
            }
        });

        return ships;
    }
}
