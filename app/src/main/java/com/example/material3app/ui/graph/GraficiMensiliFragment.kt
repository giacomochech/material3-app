package com.example.material3app.ui.graph


import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.NumberPicker
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.material3app.R
import com.example.material3app.data.FoodViewModel
import com.example.material3app.ui.slideshow.CalcolaFragment
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.PercentFormatter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*


class GraficiMensiliFragment : Fragment() {

    private lateinit var mFoodViewModel: FoodViewModel
    private lateinit var pieChart: PieChart
    private lateinit var barChart: BarChart
    private lateinit var dataPicked : LocalDate
    private lateinit var titoloMese :TextView
    private lateinit var titoloAnno :TextView
    lateinit var progressBarArray : Array<ProgressBar>
    lateinit var infoProgressBarArray : Array<TextView>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_grafici_mensili, container, false)
        progressBarArray = arrayOf(rootView.findViewById(R.id.progressBar1),
            rootView.findViewById(R.id.progressBar2),
            rootView.findViewById(R.id.progressBar3))
        infoProgressBarArray = arrayOf(rootView.findViewById(R.id.infoPb1),
            rootView.findViewById(R.id.infoPb2),
            rootView.findViewById(R.id.infoPb3))

        titoloAnno = rootView.findViewById(R.id.annoSelected)
        titoloMese = rootView.findViewById(R.id.titoloMese)
        pieChart = rootView.findViewById(R.id.pieChart)
        barChart = rootView.findViewById(R.id.barChart)
        val calendarButton = rootView.findViewById<ImageButton>(R.id.calendarMonthYear)





        mFoodViewModel = ViewModelProvider(rootView.context as ViewModelStoreOwner)[FoodViewModel::class.java]

        val sharedPref =
            activity?.getSharedPreferences(CalcolaFragment.SHARED_PREF_PAGINA_CALC, Context.MODE_PRIVATE)
        val obiettivo = sharedPref?.getInt(CalcolaFragment.INT_OBIETTIVO, 0)

        stetUp()
        if (obiettivo != null) {
            updateAllData(dataPicked,obiettivo)
        }

        calendarButton.setOnClickListener {
            val viewDialog = View.inflate(context,R.layout.dialog_date_year,null)
            val pickerMonth = viewDialog.findViewById<NumberPicker>(R.id.pickerMonth)
            pickerMonth.run {
                minValue = 1
                maxValue = 12
                value = dataPicked.monthValue
                displayedValues = arrayOf("Gen","Feb","Mar","Apr","Maggio","Giugno","Luglio",
                    "Ago","Sett","Ott","Nov","Dic")
            }
            val pickerYear = viewDialog.findViewById<NumberPicker>(R.id.pickerYear)
            pickerYear.run {
                val year = dataPicked.year
                minValue = 0
                maxValue = Int.MAX_VALUE
                value = year
            }

            val builder = context?.let { it1 ->
                MaterialAlertDialogBuilder(it1)
                    .setTitle(resources.getString(R.string.title))
                    .setView(viewDialog)
                    .setNegativeButton(resources.getString(R.string.canc)) { dialog, which ->
                    }
                    .setPositiveButton(resources.getString(R.string.ok)) { dialog, which ->
                        dataPicked =  dataPicked.withMonth(pickerMonth.value)
                        dataPicked = dataPicked.withYear(pickerYear.value)
                        if (obiettivo != null) {
                            updateAllData(dataPicked,obiettivo)
                        }
                    }
                    .show()
            }
        }







        return rootView
    }




    private fun updateAllData(data : LocalDate, obiettivo : Int){
        var mese = data.month.getDisplayName(TextStyle.FULL, Locale.ITALY);
        mese= mese.substring(0, 1).toUpperCase() + mese.substring(1).toLowerCase()
        titoloMese.text = mese
        titoloAnno.text = data.year.toString()

        var monthDetail = MonthDetail (data.month,data.year)
        if (obiettivo != null) {
            monthDetail = mFoodViewModel.getSpecificMonthDetails(data.month,data.year,obiettivo,this)
        }
        pieChart.centerText=(" ${((monthDetail.getDetailInt()[0]/monthDetail.getDayOfMonth().toFloat())*100).toInt()}% ")

        loadPieChartData(monthDetail)
        loadProgressBar(monthDetail)
        val list = mFoodViewModel.getMonthDayKal(data.month,data.year)
        loadBarChartData(list,obiettivo)


    }

    private fun loadProgressBar(monthDetail: MonthDetail){
       progressBarArray.forEachIndexed() {  i , it ->
           it.max = monthDetail.getDayOfMonth()
           it.progress= monthDetail.getDetailInt()[i]
       }
        infoProgressBarArray.forEachIndexed() {  i , it ->
            it.text = "${monthDetail.getDetailInt()[i]}/${monthDetail.getDayOfMonth()}"
        }

    }

    private fun stetUp() {
        dataPicked = LocalDate.now()
        setupPieChart()
        setupBarChart()
    }

    private fun setupPieChart() {
        pieChart.isDrawHoleEnabled = true
        pieChart.setDrawEntryLabels(false)
        pieChart.setHoleColor(Color.TRANSPARENT)
        pieChart.description.isEnabled = false
        pieChart.setCenterTextSize(34f)
        pieChart.legend.isEnabled= false
        pieChart.holeRadius = 75f
        pieChart.setDrawRoundedSlices(true)


        //pieChart.setSingleLine(true);

    }
    private fun setupBarChart() {

        barChart.description.isEnabled = false
        barChart.legend.isEnabled= false
        barChart.setFitBars(true)
        barChart.setFitBars(true)
        barChart.xAxis.isEnabled= false
        val left: YAxis = barChart.getAxisLeft()
        left.setDrawLabels(false) // no axis labels
        left.setDrawAxisLine(false) // no axis line
        left.setDrawGridLines(false) // no grid lines
        left.setDrawZeroLine(true) // draw a zero line

        barChart.setDrawValueAboveBar(true)
        barChart.setVisibleXRangeMaximum(10f)

        barChart.getAxisRight().setEnabled(false) // no right axis


    }
    private fun loadPieChartData(monthDetail: MonthDetail) {
        val entries: ArrayList<PieEntry> = ArrayList()
        val list = monthDetail.getDetailInt()
        entries.add( PieEntry(list[0].toFloat(), ""))
        entries.add(PieEntry(list[1].toFloat(), ""))
        entries.add(PieEntry(list[2].toFloat(), ""))

        val colors: ArrayList<Int> = ArrayList()
        colors.add(ContextCompat.getColor(requireContext(), com.google.android.material.R.color.m3_ref_palette_dynamic_primary70))
        colors.add(ContextCompat.getColor(requireContext(), com.google.android.material.R.color.m3_ref_palette_error70))
        colors.add( Color.GRAY)

        progressBarArray.forEachIndexed() {  i , it ->
            it.progressDrawable.setColorFilter(
                colors[i], android.graphics.PorterDuff.Mode.SRC_IN);
        }

        val dataSet = PieDataSet(entries,"")
        dataSet.colors = colors
        dataSet.setHighlightEnabled(true)
        val data = PieData(dataSet)
        data.setDrawValues(false)
        data.setValueFormatter(PercentFormatter(pieChart))
        pieChart.data = data
        pieChart.invalidate()
        pieChart.animateY(1400,Easing.EaseOutCirc)
    }

    private fun loadBarChartData(listKcalDay : List<Int>,obiettivo: Int) {
        val entries: ArrayList<BarEntry> = ArrayList()
        listKcalDay.forEachIndexed { index, it ->
            if(it==0)
                entries.add(BarEntry((index+1).toFloat(),it.toFloat()))
            else
                entries.add(BarEntry((index+1).toFloat(),it.toFloat()-obiettivo))
        }
        val colors: ArrayList<Int> = ArrayList()
        listKcalDay.forEach{
            if(it > obiettivo)
                colors.add(ContextCompat.getColor(requireContext(), com.google.android.material.R.color.m3_ref_palette_error70))
            else
                colors.add(ContextCompat.getColor(requireContext(), com.google.android.material.R.color.m3_ref_palette_dynamic_primary70))
        }

        val dataSet = BarDataSet(entries,"")
        dataSet.colors=colors
        dataSet.setDrawValues(false)
        dataSet.setHighlightEnabled(false)
        val data = BarData(dataSet)
        barChart.data = data
        barChart.invalidate()
        barChart.animateY(1400,Easing.EaseOutCirc)


    }


}