package com.example.notes.ui.fragments

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import com.example.notes.R
import com.example.notes.databinding.FragmentPieChartBinding
import com.example.notes.model.Notes
import com.example.notes.ui.adapter.NoteAdapter
import com.example.notes.viewmodel.NotesViewModel
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class PieChartFragment : Fragment() {


    val viewModel : NotesViewModel by viewModels()
    var MyNotes = arrayListOf<Notes>()
    lateinit var binding : FragmentPieChartBinding
    lateinit var pieChart : PieChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPieChartBinding.inflate(layoutInflater,container,false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pieChart = binding.pieChart


        var calendar = Calendar.getInstance()
        var datePickerDialog = DatePickerDialog.OnDateSetListener{view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR,year)
            calendar.set(Calendar.MONTH,month)
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)

            updateTV(calendar)

        }



        binding.btnPickDay.setOnClickListener{
            DatePickerDialog(requireContext(),datePickerDialog,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show()
        }



        viewModel.getNotes().observe(viewLifecycleOwner){ notesList ->
            MyNotes = notesList as ArrayList<Notes>
        }

    }

    private fun updateTV(calendar: Calendar) {
//        val myFomat="d MMMM-yyyy"
//        val sdf = SimpleDateFormat(myFomat, Locale.UK)
//        binding.tvDay.setText(sdf.format(calendar.time))
        val notesDay : CharSequence = DateFormat.format("d MMMM yyyy",calendar.time)
        binding.tvDay.setText(notesDay.toString())
        setupPieChart()
        loadPieChartData(notesDay.toString())

    }

    private fun setupPieChart() {
        pieChart.isDrawHoleEnabled = true
        pieChart.setUsePercentValues(true)
        pieChart.setEntryLabelTextSize(12f)
        pieChart.setEntryLabelColor(Color.BLACK)
        pieChart.centerText = "Work Chart"
        pieChart.setCenterTextSize(13f)
        pieChart.description.isEnabled = false
        val l = pieChart.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)
        l.isEnabled = false
    }

    private fun loadPieChartData(date: String) {

        val entries = ArrayList<PieEntry>()

        for(i in MyNotes){
            if (i.date.contains(date)){
                entries.add(PieEntry(i.hour.toFloat(), i.title))
            }

        }
        val colors = ArrayList<Int>()
        colors.add(Color.GREEN)
        colors.add(Color.RED)
        colors.add(Color.BLUE)
        colors.add(Color.GRAY)
        colors.add(Color.YELLOW)
        val dataSet = PieDataSet(entries, "")
        dataSet.colors = colors
        val data = PieData(dataSet)
        data.setDrawValues(true)
        data.setValueFormatter(PercentFormatter(pieChart))
        data.setValueTextSize(12f)
        data.setValueTextColor(Color.BLACK)
        pieChart.data = data
    }





}