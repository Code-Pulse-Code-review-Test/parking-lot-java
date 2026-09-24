package com.example.parking;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class FeeCalculator {

    public long fee(VehicleType type, LocalDateTime in, LocalDateTime out) {
        long hours = Duration.between(in, out).toHours() + 1;
        long rate = 0;
        switch (type) {
            case BIKE:
                rate = 50;
                break;
            case CAR:
                rate = 100;
                break;
            case VAN:
                rate = 150;
                break;
            case BUS:
                rate = 300;
                break;
        }
        long total = hours * rate;
        if (hours > 24) {
            total = total - (total / 10);
        }
        if (out.getDayOfWeek().getValue() >= 6) {
            total = total + 50;
        }
        return total;
    }
}
