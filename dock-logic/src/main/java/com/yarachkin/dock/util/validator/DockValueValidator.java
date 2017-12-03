package com.yarachkin.dock.util.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DockValueValidator {
    private static final Pattern VALUE_PATTERN = Pattern.compile("\\d+(\\.0+)?");

    public static boolean validate(String value) {
        Matcher valueMatcher = VALUE_PATTERN.matcher(value);
        if ( valueMatcher.matches() ) {
            return true;
        }

        return false;
    }
}
