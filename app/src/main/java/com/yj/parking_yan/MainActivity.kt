package com.yj.parking_yan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.RadioButton
import com.google.android.material.snackbar.Snackbar
import com.yj.parking_yan.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var total = 0.0
    val receiptList = mutableListOf<String>()
    val TAG = "receiptList"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(this.binding.menuToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)

        binding.btnPay.setOnClickListener {
            // Get values
            val parkingLotId = binding.radioGroup.checkedRadioButtonId
            val hours = binding.etHours.text.toString().toIntOrNull()
            val licensePlate = binding.etLp.text.toString()

            // Error handling
            if ( parkingLotId == -1 || hours == null || hours <= 0 || licensePlate.isEmpty()){
                Snackbar.make(binding.root, "All fields must be filled.", Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }

            // Calculate
            val parkingLotA = findViewById<RadioButton>(R.id.rbtn_a)
            val parkingLotB = findViewById<RadioButton>(R.id.rbtn_b)
            val parkingLot = findViewById<RadioButton>(parkingLotId)
            val parkingLotName = parkingLot.text.toString()
            if (parkingLot == parkingLotA) {
                total = 2.5 * hours
            } else if (parkingLot == parkingLotB) {
                total = 3.5 * hours
            }

            // Output results
            binding.tvReceipt.text = "RECEIPT\n" +
                    "$parkingLotName\n" +
                    "License Plate: $licensePlate\n" +
                    "Hours: $hours\n" +
                    "Total Paid: $$total"

            // Clear form
            binding.radioGroup.clearCheck()
            binding.etHours.text.clear()
            binding.etLp.text.clear()

            // Add to receipt list
            receiptList.add("$licensePlate; $parkingLotName; $hours hours; $$total")
            Log.d(TAG, "onCreate: receiptList: ${receiptList.toString()}")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_item_history -> {
                val historyIntent = Intent(this@MainActivity, HistoryActivity::class.java)
                historyIntent.putStringArrayListExtra("extra_receipt", ArrayList(receiptList))
                startActivity(historyIntent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}