package com.yj.parking_yan

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.yj.parking_yan.databinding.ActivityHistoryBinding
import com.yj.parking_yan.databinding.ActivityMainBinding

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding
    private var receiptList = mutableListOf<String>()
    private lateinit var adapter:ReceiptAdapter
    var TAG = "receiptListHistory"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent != null){
            val receivedData = intent.getStringArrayListExtra("extra_receipt")
            if (receivedData != null){
                receiptList = receivedData.toMutableList()
                Log.d(TAG, "onCreate: receiptList: ${receiptList.toString()}")

                // Set up adapter
                this.adapter = ReceiptAdapter(receiptList)
                binding.rv.adapter = adapter
                binding.rv.layoutManager = LinearLayoutManager(this)
                binding.rv.addItemDecoration(
                    DividerItemDecoration(
                        this,
                        LinearLayoutManager.VERTICAL
                    )
                )
            }

        }
    }
}