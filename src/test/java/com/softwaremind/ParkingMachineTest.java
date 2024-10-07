package com.softwaremind;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@ExtendWith(MockitoExtension.class)
class ParkingMachineTest {

    private final ParkingMachine parkingMachine;

    public ParkingMachineTest() {
        this.parkingMachine = new ParkingMachine();
    }

    @Test
    void testCarPaymentWithCashAtNightForVIP() {
        final String receipt = parkingMachine.processPayment("CAR", 2, "CASH", "NIGHT", "VIP");
        assertThat(receipt).isEqualTo("Paid 3.84 with cash");
    }

    @Test
    void testNikePaymentWithCardDuringDayForRegular() {
        final String receipt = parkingMachine.processPayment("BIKE", 1, "CARD", "DAY", "REGULAR");
        assertThat(receipt).isEqualTo("Paid 1.0 with card");
    }

    @Test
    void testTruckPaymentWithCashAtNightForRegular() {
        final String receipt = parkingMachine.processPayment("TRUCK", 3, "CASH", "NIGHT", "REGULAR");
        assertThat(receipt).isEqualTo("Paid 10.799999999999999 with cash");
    }

    @Test
    void testUnknown() {
        assertThatCode(() -> parkingMachine.processPayment("AIRPLANE",
                                                           3,
                                                           "CASH",
                                                           "NIGHT",
                                                           "REGULAR"))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("Unsupported vehicle type: AIRPLANE");
    }

    @Test
    void testTruckWithUnknownPeriod() {
        assertThatCode(() -> parkingMachine.processPayment("TRUCK",
                                                           3,
                                                           "CASH",
                                                           "EVENING",
                                                           "REGULAR"))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("Unsupported time of day: EVENING");
    }

    @Test
    void testTruckWithUnknownUserType() {
        assertThatCode(() -> parkingMachine.processPayment("TRUCK",
                                                           3,
                                                           "CASH",
                                                           "DAY",
                                                           "GOLD"))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("Unsupported user type: GOLD");
    }

    @Test
    void testTruckWithUnknownPayment() {
        assertThatCode(() -> parkingMachine.processPayment("TRUCK",
                                                           3,
                                                           "CRYPTO",
                                                           "DAY",
                                                           "VIP"))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("Unsupported payment type: CRYPTO");
    }
}