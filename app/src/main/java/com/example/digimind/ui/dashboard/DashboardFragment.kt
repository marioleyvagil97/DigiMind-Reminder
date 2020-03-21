package com.example.digimind.ui.dashboard

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.digimind.R
import com.example.digimind.recordatorio
import com.example.digimind.ui.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import kotlinx.android.synthetic.main.vista_recordatorios.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        root.btnHora.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timeRicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)

                btnHora.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(
                root.context,
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true
            ).show()
        }

        root.btnAdd.setOnClickListener{
            var nombre = et_task.text.toString()
            var hora = btnHora.text.toString()

            var days = ArrayList<String>()

            if(checkMonday.isChecked)
                days.add("Monday")
            if(checkTuesday.isChecked)
                days.add("Tuesday")
            if(checkWednesday.isChecked)
                days.add("Wednesday")
            if(checkThursday.isChecked)
                days.add("Thrursday")
            if(checkFriday.isChecked)
                days.add("Friday")
            if(checkSaturday.isChecked)
                days.add("Saturday")
            if(checkSunday.isChecked)
                days.add("Sunday")

            var recor = recordatorio(nombre,days,hora)
            HomeFragment.recordatorios.add(recor)


            Toast.makeText(root.context,"Recordatorio agregado",Toast.LENGTH_SHORT).show()
        }

        return root
    }
}
