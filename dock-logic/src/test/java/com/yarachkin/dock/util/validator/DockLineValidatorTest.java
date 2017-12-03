package com.yarachkin.dock.util.validator;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class DockLineValidatorTest {

    @Test
    public void validateTest() throws Exception {
        assertTrue(DockLineValidator.validate("40 30 20 11"));
    }

    @Test
    public void validateNegativeValuesTest() throws Exception {
        assertFalse(DockLineValidator.validate("40 -30 20 -11"));
    }

    @Test
    public void validateLessThanFourValuesTest() throws Exception {
        assertFalse(DockLineValidator.validate("40 30 20"));
    }

    @Test
    public void validateMoreThanFourValuesTest() throws Exception {
        assertFalse(DockLineValidator.validate("40 30 20 30 44"));
    }

    @Test
    public void validateValuesHavingCapacityLessThenBoardContainers() throws Exception {
        assertFalse(DockLineValidator.validate("30 31 20 30"));
    }

    @Test
    public void validateValuesHavingCapacityLessThenLoadingContainers() throws Exception {
        assertFalse(DockLineValidator.validate("30 30 31 30"));
    }

    @Test
    public void validateValuesHavingCapacityLessThenUploadingContainers() throws Exception {
        assertFalse(DockLineValidator.validate("30 30 15 35"));
    }
}