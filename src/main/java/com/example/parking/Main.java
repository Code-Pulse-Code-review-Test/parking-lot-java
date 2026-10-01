package com.example.parking;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot("City Mall", 10, 20, 4, 2);
        LocalDateTime morning = LocalDateTime.of(2026, 9, 20, 9, 0);

        System.out.println("Pass: " + lot.getPasses().buyPass("NC LV-4521", VehicleType.VAN, 6, false, morning.toLocalDate()));
        lot.park(new Vehicle("WP CAB-1234", VehicleType.CAR), morning);
        lot.park(new Vehicle("WP BBX-9876", VehicleType.BIKE), morning);
        lot.park(new Vehicle("NC LV-4521", VehicleType.VAN), morning.plusHours(1));

        System.out.println("Free car slots: " + lot.freeSlots(VehicleType.CAR));
        System.out.println("Fee: " + lot.leave("WP CAB-1234", morning.plusHours(3)));
        System.out.println("Van fee with pass: " + lot.leave("NC LV-4521", morning.plusHours(5)));
        System.out.println("Earnings: " + lot.getEarnings());
        System.out.print(lot.getPasses().passReport(morning.toLocalDate()));
    }
}
