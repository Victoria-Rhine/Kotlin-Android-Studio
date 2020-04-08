package com.example.tipcalculator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Button
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
private var totalBill: EditText? = null
private var tipPercent: SeekBar? = null
private var numberOfPeople: SeekBar? = null
private var finalBill: TextView? = null
private var amountPerPerson: TextView? = null
private var calculateTips: Button? = null
private var tipPercentValue = 0
private var numberOfPeopleValue = 0
private var tipPercentLabel: TextView? = null
private var numberOfPeopleLabel: TextView? = null

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    totalBill = findViewById(R.id.bill_value)
    tipPercent = findViewById(R.id.seekBar)
    numberOfPeople = findViewById(R.id.seekBar_One)
    finalBill = findViewById(R.id.total_to_pay)
    amountPerPerson = findViewById(R.id.amount_per_person)
    tipPercentLabel = findViewById(R.id.percent_label)
    numberOfPeopleLabel = findViewById(R.id.number_label)

    tipPercent!!.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            tipPercentValue = progress
        }
        override fun onStartTrackingTouch(seekBar: SeekBar) {}
        override fun onStopTrackingTouch(seekBar: SeekBar) {
            tipPercentLabel!!.text = "Percentage to Tip " + seekBar.progress + "%"
        }
    })

    numberOfPeople!!.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            numberOfPeopleValue = progress
        }
        override fun onStartTrackingTouch(seekBar: SeekBar) {}
        override fun onStopTrackingTouch(seekBar: SeekBar) {
            numberOfPeopleLabel!!.text = "No. of People " + seekBar.progress
        }
    })

    calculateTips = findViewById(R.id.calculate)
    calculateTips!!.setOnClickListener(View.OnClickListener {
        if (totalBill!!.text.toString() == "" || totalBill!!.text.toString().isEmpty()) {
            Toast.makeText(applicationContext, "Please enter amounts", Toast.LENGTH_LONG).show()
            return@OnClickListener
        }
        var totalBillInput = java.lang.Double.parseDouble(totalBill!!.text.toString())
        if (tipPercentValue == 0 || numberOfPeopleValue == 0) {
            Toast.makeText(applicationContext, "Please choose values", Toast.LENGTH_LONG).show()
            return@OnClickListener
        }

        var tipAmount = TipCalculator().calculateTipAmount(totalBillInput, tipPercentValue)
        var totalAmountForTheBill = TipCalculator().calculateFinallBillAmount(totalBillInput, tipAmount)
        var billPerEachPerson = TipCalculator().calculatePerPersonAmount(totalAmountForTheBill, numberOfPeopleValue)

        finalBill!!.text = totalAmountForTheBill.round().toString()
        amountPerPerson!!.text = billPerEachPerson.round().toString()
    })
    return
}

fun Double.round(decimals: Int = 2): Double = "%.${decimals}f".format(this).toDouble()
}


