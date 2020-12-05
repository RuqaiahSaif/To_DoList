package com.example1.tablayout

    import android.app.DatePickerDialog
    import android.app.Dialog
    import android.os.Bundle
    import android.widget.DatePicker
    import androidx.fragment.app.DialogFragment
    import java.util.*


    private const val ARG_DATE = "date"

    class DatePicket: DialogFragment() {


        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

            val dateListener = DatePickerDialog.OnDateSetListener {
                    _: DatePicker, year: Int, month: Int, day: Int ->
                val resultDate : Date = GregorianCalendar(year, month, day).time
                targetFragment?.let { fragment ->
                    (fragment as Callbacks1).onDateSelected(resultDate)
                }
            }

            val calendar = Calendar.getInstance()

            val initialYear = calendar.get(Calendar.YEAR)
            val initialMonth = calendar.get(Calendar.MONTH)
            val initialDay = calendar.get(Calendar.DAY_OF_MONTH)
            return DatePickerDialog(requireContext(), dateListener, initialYear, initialMonth, initialDay)
        }


        interface  Callbacks1 {
            fun onDateSelected(date: Date)
        }
    }

