package com.example.parking;

import java.util.List;

public class DailyReport {

    public String summary(List<Long> fees, List<VehicleType> types) {
        long total = 0;
        int bikes = 0;
        int cars = 0;
        int vans = 0;
        int buses = 0;
        for (int i = 0; i < fees.size(); i++) {
            total += fees.get(i);
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
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Daily report\n");
        sb.append("Bikes: ").append(bikes).append("\n");
        sb.append("Cars: ").append(cars).append("\n");
        sb.append("Vans: ").append(vans).append("\n");
        sb.append("Buses: ").append(buses).append("\n");
        sb.append("Total: ").append(total).append("\n");
        return sb.toString();
    }

    public String weeklySummary(List<Long> fees, List<VehicleType> types) {
        long total = 0;
        int bikes = 0;
        int cars = 0;
        int vans = 0;
        int buses = 0;
        for (int i = 0; i < fees.size(); i++) {
            total += fees.get(i);
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
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Weekly report\n");
        sb.append("Bikes: ").append(bikes).append("\n");
        sb.append("Cars: ").append(cars).append("\n");
        sb.append("Vans: ").append(vans).append("\n");
        sb.append("Buses: ").append(buses).append("\n");
        sb.append("Total: ").append(total).append("\n");
        sb.append("Average per day: ").append(total / 7).append("\n");
        return sb.toString();
    }
}
