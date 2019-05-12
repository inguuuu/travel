package com.teamandroid.travelmaker.write

import android.app.DatePickerDialog
import android.app.PendingIntent.getActivity
import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.*
import java.util.*
import android.app.Activity
import android.widget.Toast
import android.widget.DatePicker
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import com.teamandroid.travelmaker.Inn
import com.teamandroid.travelmaker.R


class ApplyWriteViewHolder(itemView: View, var activity : ApplyWrite) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
    var in_date : TextView
    var out_date : TextView
    var in_place : TextView
    var out_place : TextView
    var acc_place : TextView
    
    init {
        in_date = itemView.findViewById(R.id.in_date)
        out_date = itemView.findViewById(R.id.out_date)
        in_place = itemView.findViewById(R.id.in_place)
        out_place = itemView.findViewById(R.id.out_place)
        acc_place = itemView.findViewById(R.id.acc_place)

        in_date.setOnClickListener(this)
        out_date.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        val calendar = Calendar.getInstance()

        if(v == in_date){

            val dateListener = OnDateSetListener {
                view1, year, month, dayOfMonth -> in_date.setText(
                    String.format("%d", year) + "-" + String.format("%02d", month + 1) + "-" + String.format("%02d", dayOfMonth))

            }
            val timeListener = TimePickerDialog.OnTimeSetListener {
                view1, hourOfDay, minute -> in_date.setText(
                    in_date.text.toString() + " " + String.format("%d", hourOfDay) + ":" + String.format("%02d", minute))
            }

            val timePickerDialog = TimePickerDialog(activity, timeListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false)
            timePickerDialog.show()

            val datePickerDialog = DatePickerDialog(activity, dateListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE))
            datePickerDialog.show()

        }
        else if(v == out_date){
            val dateListener = OnDateSetListener { view1, year, month, dayOfMonth -> out_date.setText(
                    String.format("%d", year) + "-" + String.format("%02d", month + 1) + "-" + String.format("%02d", dayOfMonth))
            }
            val timeListener = TimePickerDialog.OnTimeSetListener {
                view1, hourOfDay, minute -> out_date.setText(
                    out_date.text.toString() + " " + String.format("%d", hourOfDay) + ":" + String.format("%02d", minute))
            }

            val timePickerDialog = TimePickerDialog(activity, timeListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false)
            timePickerDialog.show()

            val datePickerDialog = DatePickerDialog(activity, dateListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE))
            datePickerDialog.show()

        }
    }
}

