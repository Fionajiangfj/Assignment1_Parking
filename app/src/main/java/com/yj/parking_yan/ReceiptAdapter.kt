package com.yj.parking_yan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReceiptAdapter(var receiptList:List<String>) : RecyclerView.Adapter<ReceiptAdapter.ReceiptViewHolder>() {

    inner class ReceiptViewHolder(itemView: View) : RecyclerView.ViewHolder (itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiptViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.activity_receipt_adapter, parent, false)
        return ReceiptViewHolder(view)
    }

    override fun getItemCount(): Int {
        return receiptList.size
    }

    override fun onBindViewHolder(holder: ReceiptViewHolder, position: Int) {
        val tvRow = holder.itemView.findViewById<TextView>(R.id.tv_row)

        val receipt = receiptList[position]
        val receiptDivided = receipt.split(";")

        val output = "Receipt:\n" +
                "Parking Lot: ${receiptDivided[1]}\n" +
                "License Plate: ${receiptDivided[0]}\n" +
                "Hours: ${receiptDivided[2]}\n" +
                "Total Paid: ${receiptDivided[3]}"

        tvRow.text = output
    }

}
