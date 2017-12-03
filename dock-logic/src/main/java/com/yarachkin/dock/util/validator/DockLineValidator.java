package com.yarachkin.dock.util.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DockLineValidator {

    public static boolean validate(String line) {
        List<String> values = Arrays.asList(line.split("\\s"));
        if ( values.size() != 4 ) {
            return false;
        }

        for (String value : values) {
            if ( !DockValueValidator.validate(value) ) {
                return false;
            }
        }

        List<Integer> integerValues = new ArrayList<>();
        values.forEach(value -> integerValues.add(Integer.parseInt(value)));

        if ( integerValues.get(0) < integerValues.get(1) || integerValues.get(0) < integerValues.get(2) ||
                integerValues.get(0) < integerValues.get(3) || integerValues.get(0) < integerValues.get(1) -
                integerValues.get(2) + integerValues.get(3) ) {
            return false;
        }
        return true;
    }
}
