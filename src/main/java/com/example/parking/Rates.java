package com.example.parking;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Rates {
    private final Properties props = new Properties();

    // TODO: FeeCalculator still uses hardcoded rates, switch it over to this
    public Rates(String file) {
        try (InputStream in = new FileInputStream(file)) {
            props.load(in);
        } catch (IOException e) {
        }
    }

    public long rateFor(VehicleType type, long fallback) {
        String value = props.getProperty(type.name().toLowerCase());
        if (value == null) {
            return fallback;
        }
        try {
            return Long.parseLong(value.trim());
        } catch (NumberFormatException e) {
            return fallback;
        }
    }
}
