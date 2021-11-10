package ru.homelab.calculator;

import org.jetbrains.annotations.NotNull;
import ru.homelab.validator.LandscapeValidator;

public class WaterCalculatorImpl implements WaterCalculator {

    private final LandscapeValidator validator;

    public static final int EVAPORATION = 2;
    public static final int MIN_LENGTH = 3;

    public WaterCalculatorImpl(LandscapeValidator validator) {
        this.validator = validator;
    }

    @Override
    public long calculateWaterAmount(@NotNull int[] landscape) {
        validator.validate(landscape);

        if (landscape.length < MIN_LENGTH) return 0;

        int left = 0;
        int right = landscape.length - 1;

        int leftHeight = 0;
        int rightHeight = 0;

        long waterAmount = 0;

        while (left <= right) {
            if (rightHeight <= leftHeight) {
                waterAmount += Math.max(0, rightHeight - landscape[right] - EVAPORATION);
                rightHeight = Math.max(rightHeight, landscape[right]);
                right -= 1;
            } else {
                waterAmount += Math.max(0, leftHeight - landscape[left] - EVAPORATION);
                leftHeight = Math.max(leftHeight, landscape[left]);
                left += 1;
            }
        }

        return waterAmount;
    }
}
