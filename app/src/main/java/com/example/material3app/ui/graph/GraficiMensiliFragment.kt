package com.example.material3app.ui.graph


import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.NumberPicker
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.material3app.R
import com.example.material3app.data.FoodViewModel
import com.example.material3app.ui.slideshow.CalcolaFragment
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
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
    private lateinit var dataPicked: LocalDate
    private lateinit var titoloMese: TextView
    private lateinit var titoloAnno: TextView
    private lateinit var progressBarArray: Array<ProgressBar>
    private lateinit var infoProgressBarArray: Array<TextView>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_grafici_mensili, container, false)
        progressBarArray = arrayOf(
            rootView.findViewById(R.id.progressBar1),
            rootView.findViewById(R.id.progressBar2),
            rootView.findViewById(R.id.progressBar3)
        )
        infoProgressBarArray = arrayOf(
            rootView.findViewById(R.id.infoPb1),
            rootView.findViewById(R.id.infoPb2),
            rootView.findViewById(R.id.infoPb3)
        )

        titoloAnno = rootView.findViewById(R.id.annoSelected)
        titoloMese = rootView.findViewById(R.id.titoloMese)
        pieChart = rootView.findViewById(R.id.pieChart)
        barChart = rootView.findViewById(R.id.barChart)
        val calendarButton = rootView.findViewById<ImageButton>(R.id.calendarMonthYear)





        mFoodViewModel =
            ViewModelProvider(rootView.context as ViewModelStoreOwner)[FoodViewModel::class.java]

        val sharedPref =
            activity?.getSharedPreferences(
                CalcolaFragment.SHARED_PREF_PAGINA_CALC,
                Context.MODE_PRIVATE
            )
        val obiettivo = sharedPref?.getInt(CalcolaFragment.INT_OBIETTIVO, 0)

        stetUp()
        if (obiettivo != null) {
            updateAllData(dataPicked, obiettivo)
        }

        calendarButton.setOnClickListener {
            val viewDialog = View.inflate(context, R.layout.dialog_date_year, null)
            val pickerMonth = viewDialog.findViewById<NumberPicker>(R.id.pickerMonth)
            pickerMonth.run {
                minValue = 1
                maxValue = 12
                value = dataPicked.monthValue
                displayedValues = arrayOf(
                    "Gen", "Feb", "Mar", "Apr", "Maggio", "Giugno", "Luglio",
                    "Ago", "Sett", "Ott", "Nov", "Dic"
                )
            }
            val pickerYear = viewDialog.findViewById<NumberPicker>(R.id.pickerYear)
            pickerYear.run {
                val year = dataPicked.year
                minValue = 0
                maxValue = Int.MAX_VALUE
                value = year
            }

            context?.let { it1 ->
                MaterialAlertDialogBuilder(it1)
                    .setTitle(resources.getString(R.string.title))
                    .setView(viewDialog)
                    .setNegativeButton(resources.getString(R.string.canc)) { dialog, which ->
                    }
                    .setPositiveButton(resources.getString(R.string.ok)) { dialog, which ->
                        dataPicked = dataPicked.withMonth(pickerMonth.value)
                        dataPicked = dataPicked.withYear(pickerYear.value)
                        if (obiettivo != null) {
                            updateAllData(dataPicked, obiettivo)
                        }
                    }
                    .show()
            }
        }







        return rootView
    }


    private fun updateAllData(data: LocalDate, obiettivo: Int) {
        var mese = data.month.getDisplayName(TextStyle.FULL, Locale.ITALY)
        mese = mese.substring(0, 1).uppercase() + mese.substring(1).lowercase()
        titoloMese.text = mese
        titoloAnno.text = data.year.toString()

        val monthDetail: MonthDetail
        monthDetail = mFoodViewModel.getSpecificMonthDetails(data.month, data.year, obiettivo)
        pieChart.centerText = (" ${
            ((monthDetail.getDetailInt()[0] / monthDetail.getDayOfMonth().toFloat()) * 100).toInt()
        }% ")

        loadPieChartData(monthDetail)
        loadProgressBar(monthDetail)
        val list = mFoodViewModel.getMonthDayKal(data.month, data.year)
        loadBarChartData(list, obiettivo)


    }

    private fun loadProgressBar(monthDetail: MonthDetail) {
        progressBarArray.forEachIndexed { i, it ->
            it.max = monthDetail.getDayOfMonth()
            it.progress = monthDetail.getDetailInt()[i]
        }
        infoProgressBarArray.forEachIndexed { i, it ->

            val s = "${monthDetail.getDetailInt()[i]}/${monthDetail.getDayOfMonth()}"
            it.text = s
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
        pieChart.legend.isEnabled = false
        pieChart.holeRadius = 75f
        pieChart.setDrawRoundedSlices(true)


        //pieChart.setSingleLine(true);

    }

    private fun setupBarChart() {

        barChart.description.isEnabled = false

        barChart.setDrawValueAboveBar(true)
        barChart.legend.isEnabled = false
        barChart.setFitBars(true)
        barChart.setFitBars(true)
        barChart.xAxis.isEnabled = false
        val left: YAxis = barChart.axisLeft
        left.setDrawLabels(false) // no axis labels
        left.setDrawAxisLine(false) // no axis line
        left.setDrawGridLines(false) // no grid lines
        left.setDrawZeroLine(true) // draw a zero line
        left.zeroLineWidth = 0.5f
        barChart.setDrawValueAboveBar(true)
        barChart.setVisibleXRangeMaximum(10f)

        barChart.axisRight.isEnabled = false // no right axis


    }

    private fun loadPieChartData(monthDetail: MonthDetail) {
        val entries: ArrayList<PieEntry> = ArrayList()
        val list = monthDetail.getDetailInt()
        entries.add(PieEntry(list[0].toFloat(), ""))
        entries.add(PieEntry(list[1].toFloat(), ""))
        entries.add(PieEntry(list[2].toFloat(), ""))

        val colors: ArrayList<Int> = ArrayList()
        val typedValue = TypedValue()
        context?.theme?.resolveAttribute(
            com.google.android.material.R.attr.colorPrimaryContainer,
            typedValue,
            true
        )
        colors.add(typedValue.data)
        context?.theme?.resolveAttribute(
            com.google.android.material.R.attr.colorErrorContainer,
            typedValue,
            true
        )
        colors.add(typedValue.data)
        colors.add(Color.GRAY)

        progressBarArray.forEachIndexed { i, it ->
            it.progressTintList = ColorStateList.valueOf(colors[i])
        }

        val dataSet = PieDataSet(entries, "")
        dataSet.colors = colors
        dataSet.isHighlightEnabled = true
        val data = PieData(dataSet)
        data.setDrawValues(false)
        data.setValueFormatter(PercentFormatter(pieChart))
        pieChart.data = data
        pieChart.invalidate()
        pieChart.animateY(1400, Easing.EaseOutCirc)
    }

    private fun loadBarChartData(listKcalDay: List<Int>, obiettivo: Int) {
        val entries: ArrayList<BarEntry> = ArrayList()
        val indexMax = getIndexMax(listKcalDay, obiettivo)
        val indexMin = getIndexMin(listKcalDay, obiettivo)
        listKcalDay.forEachIndexed { index, it ->
            if (it == 0)
                entries.add(BarEntry((index + 1).toFloat(), it.toFloat()))
            else
                entries.add(BarEntry((index + 1).toFloat(), it.toFloat() - obiettivo))
        }

        val entries2: ArrayList<BarEntry> = ArrayList()
        val colors2: ArrayList<Int> = ArrayList()

        if (indexMax != -1){
            entries2.add(BarEntry((indexMax + 1).toFloat(), listKcalDay[indexMax].toFloat() - obiettivo))
            val typedValue = TypedValue()
            context?.theme?.resolveAttribute(
                com.google.android.material.R.attr.colorError,
                typedValue,
                true
            )
            colors2.add(typedValue.data)
        }

        if (indexMin != -1) {
            entries2.add(BarEntry((indexMin + 1).toFloat(), listKcalDay[indexMin].toFloat() - obiettivo))
            val typedValue = TypedValue()
            context?.theme?.resolveAttribute(
                com.google.android.material.R.attr.colorSecondary,
                typedValue,
                true
            )
            colors2.add(typedValue.data)
        }

        val colors: ArrayList<Int> = ArrayList()
        listKcalDay.forEach {
            if (it > obiettivo) {
                //colors.add(getContext().getTheme().resolveAttribute(android.R.attr.colorPrimary, value, true))
                val typedValue = TypedValue()
                context?.theme?.resolveAttribute(
                    com.google.android.material.R.attr.colorError,
                    typedValue,
                    true
                )
                colors.add(typedValue.data)

            } else {
                val typedValue = TypedValue()
                context?.theme?.resolveAttribute(
                    com.google.android.material.R.attr.colorSecondary,
                    typedValue,
                    true
                )
                colors.add(typedValue.data)
            }
        }




        val dataSet = BarDataSet(entries, "")
        dataSet.colors = colors
        dataSet.setDrawValues(false)
        dataSet.isHighlightEnabled = false

        val dataSet2 = BarDataSet(entries2, "")
        dataSet2.colors = colors2
        dataSet2.isHighlightEnabled = false
        dataSet2.valueTextSize = 16f

        val data = BarData(dataSet)
        data.addDataSet(dataSet2)
        barChart.data = data
        barChart.invalidate()
        barChart.animateY(1400, Easing.EaseOutCirc)


    }

    private fun getIndexMin(listKcal: List<Int>, obiettivo: Int): Int {
        var indexRet = 0
        listKcal.forEachIndexed { index, it ->
            if (it!=0 && (listKcal[indexRet] > it || listKcal[indexRet]==0))
                indexRet = index
        }
        return if (listKcal[indexRet] > obiettivo || listKcal[indexRet]==0)
            -1
        else
            indexRet
    }

    private fun getIndexMax(listKcal: List<Int>, obiettivo: Int): Int {
        var indexRet = 0
        listKcal.forEachIndexed { index, it ->
            if (listKcal[indexRet] < it)
                indexRet = index
        }
        return if (listKcal[indexRet] < obiettivo || listKcal[indexRet]==0)
            -1
        else
            indexRet
    }


}