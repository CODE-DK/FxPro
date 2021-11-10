package ru.homelab.validator;

import ru.homelab.exception.WaterCalculatorException;

public class LandscapeValidatorImpl implements LandscapeValidator {

    private static final int MAX_HEIGHT = 32000;
    private static final int MAX_LANDSCAPE = 32000;

    @Override
    public void validate(int[] landscape) {
        if (landscape.length > MAX_LANDSCAPE) {
            throw new WaterCalculatorException("The max landscape can't be great then " + MAX_LANDSCAPE);
        }
        for (int value : landscape) {
            if (value > MAX_HEIGHT) {
                throw new WaterCalculatorException("The max height in landscape " + value + " is great the " + MAX_HEIGHT);
            }
            if (value < 0) {
                throw new WaterCalculatorException("The landscape contains negative values: " + value);
            }
        }
    }
}