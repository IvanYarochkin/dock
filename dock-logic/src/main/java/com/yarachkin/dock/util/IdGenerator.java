package com.yarachkin.dock.util;

public class IdGenerator {
    private static long shipId = 0;

    private static boolean isTest = false;

    private IdGenerator() {
    }

    public static void setIsTest(boolean isTest) {
        IdGenerator.isTest = isTest;
    }

    public static long generateShipId() {
        return isTest ? 1L : ++shipId;
    }
}
