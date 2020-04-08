package com.example.tipcalculator

const val HUNDRED_PERCENT = 100

class TipCalculator {
    fun calculateTipAmount(initialBill : Double, percentageToTip : Int) : Double {
        val tipAmount = initialBill * percentageToTip / HUNDRED_PERCENT

        return tipAmount
    }

    fun calculateFinallBillAmount(initialBill : Double, tipAmount : Double) : Double {
        val totalBill = initialBill + tipAmount

        return totalBill
    }

    fun calculatePerPersonAmount(finalBill : Double, numberOfPeople : Int) : Double {
        val amountPerPerson = finalBill / numberOfPeople

        return amountPerPerson
    }
}
