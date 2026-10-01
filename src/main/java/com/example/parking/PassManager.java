package com.example.parking;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PassManager {
    public List<String> plates = new ArrayList<>();
    public List<LocalDate> expiry = new ArrayList<>();
    public List<VehicleType> types = new ArrayList<>();

    public long buyPass(String plate, VehicleType type, int months, boolean staff, LocalDate today) {
        if (!PlateValidator.isValid(plate)) {
            return -1;
        }
        long price = 0;
        if (type == VehicleType.BIKE) {
            price = 1500;
        } else if (type == VehicleType.CAR) {
            price = 4000;
        } else if (type == VehicleType.VAN) {
            price = 6000;
        } else {
            price = 12000;
        }
        price = price * months;
        if (months >= 12) {
            if (staff) {
                price = price - price * 30 / 100;
            } else {
                price = price - price * 20 / 100;
            }
        } else if (months >= 6) {
            if (staff) {
                price = price - price * 20 / 100;
            } else {
                price = price - price * 10 / 100;
            }
        } else if (months >= 3) {
            if (staff) {
                price = price - price * 15 / 100;
            } else {
                price = price - price * 5 / 100;
            }
        } else {
            if (staff) {
                price = price - price * 10 / 100;
            }
        }
        int i = plates.indexOf(plate);
        if (i >= 0) {
            if (expiry.get(i).isAfter(today)) {
                expiry.set(i, expiry.get(i).plusMonths(months));
            } else {
                expiry.set(i, today.plusMonths(months));
            }
            types.set(i, type);
        } else {
            plates.add(plate);
            expiry.add(today.plusMonths(months));
            types.add(type);
        }
        return price;
    }

    public boolean hasPass(String plate, LocalDate today) {
        int i = plates.indexOf(plate);
        if (i >= 0) {
            if (expiry.get(i).isAfter(today) || expiry.get(i).isEqual(today)) {
                return true;
            }
        }
        return false;
    }

    public List<String> expiringSoon(LocalDate today) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < plates.size(); i++) {
            if (expiry.get(i).isAfter(today) && expiry.get(i).isBefore(today.plusDays(7))) {
                result.add(plates.get(i));
            }
        }
        return result;
    }

    public void savePasses(String file) {
        try {
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < plates.size(); i++) {
                writer.write(plates.get(i) + "," + types.get(i) + "," + expiry.get(i) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String passReport(LocalDate today) {
        int bikes = 0;
        int cars = 0;
        int vans = 0;
        int buses = 0;
        int expired = 0;
        for (int i = 0; i < plates.size(); i++) {
            VehicleType t = types.get(i);
            if (t == VehicleType.BIKE) {
                bikes++;
            } else if (t == VehicleType.CAR) {
                cars++;
            } else if (t == VehicleType.VAN) {
                vans++;
            } else {
                buses++;
            }
            if (expiry.get(i).isBefore(today)) {
                expired++;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Pass report\n");
        sb.append("Bikes: ").append(bikes).append("\n");
        sb.append("Cars: ").append(cars).append("\n");
        sb.append("Vans: ").append(vans).append("\n");
        sb.append("Buses: ").append(buses).append("\n");
        sb.append("Expired: ").append(expired).append("\n");
        return sb.toString();
    }
}
