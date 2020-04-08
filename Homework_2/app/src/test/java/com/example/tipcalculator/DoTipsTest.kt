package com.example.tipcalculator

import org.junit.Assert.*
import org.junit.Test

class TipCalculatorTest {

    @Test
    fun testCalculateTipAmountEqualsFive() {

        val tipAmount : Double = TipCalculator().calculateTipAmount(10.00, 50)

        assertEquals(5.00, tipAmount, 0.01)
    }

    @Test
    fun testCalculateTipAmountEqualsThirtyOneFortyEight() {

        val tipAmount : Double = TipCalculator().calculateTipAmount(125.95, 25)

        assertEquals(31.48, tipAmount, 0.01)
    }

    @Test
    fun testCalculateFinalBillEqualsFifteen() {

        val totalBill : Double = TipCalculator().calculateFinallBillAmount(10.00, 5.0)

        assertEquals(15.00, totalBill, 0.01)
    }

    @Test
    fun testCalculateFinalBillEqualsOneFiftySevenFortyThree() {

        val totalBill : Double = TipCalculator().calculateFinallBillAmount(125.95, 31.48)

        assertEquals(157.43, totalBill, 0.01)
    }

    @Test
    fun testCalculateAmountPerPersonEqualsTen() {

        val amountPerPerson : Double = TipCalculator().calculatePerPersonAmount(60.00, 6)

        assertEquals(10.00, amountPerPerson, 0.01)
    }

    @Test
    fun testCalculateAmountPerPersonEqualsSeventeenEightyFive() {

        val amountPerPerson : Double = TipCalculator().calculatePerPersonAmount(124.95, 7)

        assertEquals(17.85, amountPerPerson, 0.01)
    }

}