package com.example.parking;

public class PlateValidator {

    public static boolean isValid(String plate) {
        if (plate == null || plate.isEmpty()) {
            return false;
        }
        String[] parts = plate.trim().split(" ");
        if (parts.length == 2) {
            String province = parts[0];
            String rest = parts[1];
            if (province.length() == 2) {
                if (province.equals("WP") || province.equals("CP") || province.equals("SP")
                        || province.equals("NP") || province.equals("EP") || province.equals("NW")
                        || province.equals("NC") || province.equals("UP") || province.equals("SG")) {
                    if (rest.contains("-")) {
                        String letters = rest.substring(0, rest.indexOf('-'));
                        String digits = rest.substring(rest.indexOf('-') + 1);
                        if (letters.length() >= 2 && letters.length() <= 3) {
                            if (digits.length() == 4) {
                                for (char c : digits.toCharArray()) {
                                    if (!Character.isDigit(c)) {
                                        return false;
                                    }
                                }
                                return true;
                            }
                        }
                    }
                }
            }
        } else if (parts.length == 1) {
            String old = parts[0];
            if (old.contains("-")) {
                String left = old.substring(0, old.indexOf('-'));
                String right = old.substring(old.indexOf('-') + 1);
                if (left.length() <= 3 && right.length() == 4) {
                    return true;
                }
            }
        }
        return false;
    }
}
