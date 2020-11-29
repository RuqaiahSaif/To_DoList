package com.example1.tablayout

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_newdialog.view.*
import java.text.DateFormat
import java.util.*
private const val DIALOG_DATE = "DialogDate"
private const val REQUEST_DATE = 0
class NewDialog:  DialogFragment(),DatePicket.Callbacks {
    private lateinit var t: To_DoList
    private lateinit var titleEditText: EditText
    private lateinit var  detailsEditText: EditText
    private lateinit var dateButton: Button
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val v = activity?.layoutInflater?.inflate(R.layout.fragment_newdialog, null)
         titleEditText = v?.findViewById(R.id.title) as EditText
         detailsEditText = v?.findViewById(R.id.details) as EditText
        dateButton = v?.findViewById(R.id.date) as Button




        return AlertDialog.Builder(requireContext(), R.style.ThemeOverlay_AppCompat_Dialog_Alert)
            .setView(v)
            .setPositiveButton("Add") { dialog, _ ->
                val t =To_DoList(
                    UUID.randomUUID()  ,titleEditText.text.toString() ,detailsEditText.text.toString())
                targetFragment?.let { fragment ->
                    (fragment as Callbacks).doingAdd(t)
                }
            }.setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }.create()

    }




    override fun onStart() {
        super.onStart()
        dateButton.setOnClickListener{
            DatePicket.newInstance(t.date).apply {
                setTargetFragment(this@NewDialog, REQUEST_DATE)
                show(this@NewDialog.requireFragmentManager(), DIALOG_DATE)
            }
        }

    }

    interface Callbacks{
        fun doingAdd(doing:To_DoList){
    }
    }

    override fun onDateSelected(date: Date) {
        t.date = date
    }


}