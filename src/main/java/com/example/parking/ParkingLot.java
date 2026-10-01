package com.example.parking;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final List<Slot> slots = new ArrayList<>();
    private final FeeCalculator calculator = new FeeCalculator();
    private final PassManager passes = new PassManager();
    private final String name;
    private long earnings;
    private int totalVisits;

    public ParkingLot(String name, int bikes, int cars, int vans, int buses) {
        this.name = name;
        int n = 1;
        for (int i = 0; i < bikes; i++) {
            slots.add(new Slot(n++, VehicleType.BIKE));
        }
        for (int i = 0; i < cars; i++) {
            slots.add(new Slot(n++, VehicleType.CAR));
        }
        for (int i = 0; i < vans; i++) {
            slots.add(new Slot(n++, VehicleType.VAN));
        }
        for (int i = 0; i < buses; i++) {
            slots.add(new Slot(n++, VehicleType.BUS));
        }
    }

    public Slot park(Vehicle v, LocalDateTime time) {
        for (Slot s : slots) {
            if (s.isFree() && s.fits(v)) {
                s.park(v, time);
                return s;
            }
        }
        return null;
    }

    public long leave(String plate, LocalDateTime time) {
        for (Slot s : slots) {
            if (!s.isFree() && s.getVehicle().getPlate().equals(plate)) {
                long fee = 0;
                if (!passes.hasPass(plate, time.toLocalDate())) {
                    fee = calculator.fee(s.getVehicle().getType(), s.getParkedAt(), time);
                }
                s.leave();
                earnings += fee;
                return fee;
            }
        }
        throw new IllegalArgumentException("Vehicle not found: " + plate);
    }

    public int freeSlots(VehicleType type) {
        int count = 0;
        for (Slot s : slots) {
            if (s.isFree() && s.fits(new Vehicle("", type))) {
                count++;
            }
        }
        return count;
    }

    public PassManager getPasses() {
        return passes;
    }

    public long getEarnings() {
        return earnings;
    }

    public void saveReport(String file) {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("Parking lot: " + name + "\n");
            writer.write("Earnings: " + earnings + "\n");
            for (Slot s : slots) {
                if (!s.isFree()) {
                    writer.write(s.getNumber() + " " + s.getVehicle().getPlate() + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
