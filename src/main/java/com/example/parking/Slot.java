package com.example.parking;

import java.time.LocalDateTime;

public class Slot {
    private final int number;
    private final VehicleType size;
    private Vehicle vehicle;
    private LocalDateTime parkedAt;

    public Slot(int number, VehicleType size) {
        this.number = number;
        this.size = size;
    }

    public boolean isFree() {
        return vehicle == null;
    }

    public boolean fits(Vehicle v) {
        return v.getType().ordinal() <= size.ordinal();
    }

    public void park(Vehicle v, LocalDateTime time) {
        vehicle = v;
        parkedAt = time;
    }

    public Vehicle leave() {
        Vehicle v = vehicle;
        vehicle = null;
        parkedAt = null;
        return v;
    }

    public int getNumber() {
        return number;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDateTime getParkedAt() {
        return parkedAt;
    }
}
