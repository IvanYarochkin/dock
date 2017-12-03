package com.yarachkin.dock.util.validator;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DockValueValidatorTest {

    @Test
    public void validateTest() throws Exception {
        assertTrue(DockValueValidator.validate("40"));
    }

    @Test
    public void validateNegativeValueTest() throws Exception {
        assertFalse(DockValueValidator.validate("-40"));
    }

    @Test
    public void validateDoubleValueTest() throws Exception {
        assertFalse(DockValueValidator.validate("40.5"));
    }

    @Test
    public void validateValueHavingPointTest() throws Exception {
        assertFalse(DockValueValidator.validate("40."));
    }
}