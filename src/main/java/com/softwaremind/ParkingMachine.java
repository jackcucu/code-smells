package com.softwaremind;

public class ParkingMachine {

    // vehicleType "CAR", "BIKE", "TRUCK"
    // parkingTime in hours
    // paymentType "CASH", "CARD"
    // timeOfDay DAY NIGHT
    // userType REGULAR, VIP
    // receiptType PRINT EMAIL
    public String processPayment(String vehicleType,
                                 double parkingTime,
                                 String paymentType,
                                 String timeOfDay,
                                 String userType) {
        double price = 0.0;

        // Vehicle type check and base rate calculation
        if ("CAR".equalsIgnoreCase(vehicleType)) {
            price = 2.00;
        } else if ("BIKE".equalsIgnoreCase(vehicleType)) {
            price = 1.00;
        } else if ("TRUCK".equalsIgnoreCase(vehicleType)) {
            price = 3.00;
        } else {
            throw new IllegalArgumentException("Unsupported vehicle type: " + vehicleType);
        }

        // Time of day rate adjustment
        if ("NIGHT".equalsIgnoreCase(timeOfDay)) {
            price *= 1.2; // 20% extra at night
        } else if (!"DAY".equalsIgnoreCase(timeOfDay)) {
            throw new IllegalArgumentException("Unsupported time of day: " + timeOfDay);
        }

        // User type discount
        if ("VIP".equalsIgnoreCase(userType)) {
            price *= 0.8; // 20% discount for VIP users
        } else if (!"REGULAR".equalsIgnoreCase(userType)) {
            throw new IllegalArgumentException("Unsupported user type: " + userType);
        }

        // Final price calculation based on parking time
        price *= parkingTime;

        String receipt;
        // Payment processing
        if ("CASH".equalsIgnoreCase(paymentType)) {
            receipt = "Paid " + price + " with cash";
        } else if ("CARD".equalsIgnoreCase(paymentType)) {
            receipt = "Paid " + price + " with card";
        } else {
            throw new IllegalArgumentException("Unsupported payment type: " + paymentType);
        }

        return receipt;
    }
}