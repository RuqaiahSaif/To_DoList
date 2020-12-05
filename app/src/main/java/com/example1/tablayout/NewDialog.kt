package com.example1.tablayout

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import java.util.*
private const val DIALOG_DATE = "DialogDate"
private const val REQUEST_DATE = 0
public class NewDialog:  DialogFragment(),DatePicket.Callbacks1 {
    private lateinit var titleEditText: EditText
    private lateinit var  detailsEditText: EditText
    private lateinit var dateButton: Button
    private lateinit var t:To_DoList
    private lateinit var d:Date

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val v = activity?.layoutInflater?.inflate(R.layout.fragment_newdialog, null)
         titleEditText = v?.findViewById(R.id.title) as EditText
         detailsEditText = v?.findViewById(R.id.details) as EditText
        dateButton = v?.findViewById(R.id.date) as Button

        dateButton.setOnClickListener{
            DatePicket().apply {
                setTargetFragment(this@NewDialog, REQUEST_DATE)
                show(this@NewDialog.requireFragmentManager(), DIALOG_DATE)
            }
        }



        return AlertDialog.Builder(requireContext(), R.style.ThemeOverlay_AppCompat_Dialog_Alert)
            .setView(v)
            .setPositiveButton("Add") { dialog, _ ->
                 t =To_DoList(
                    UUID.randomUUID()  ,titleEditText.text.toString() ,detailsEditText.text.toString(),d,0)
                targetFragment?.let { fragment ->
                    (fragment as Callbacks).doingAdd(t)
                }
            }.setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }.create()

    }




    override fun onStart() {
        super.onStart()


    }

    interface Callbacks{
        fun doingAdd(doing:To_DoList){
    }
    }

    override fun onDateSelected(date: Date) {
        d = date
        dateButton.text = date.toString()

    }
}
