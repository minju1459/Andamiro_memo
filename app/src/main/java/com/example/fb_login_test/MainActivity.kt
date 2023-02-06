package com.example.fb_login_test

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.Calendar
import java.util.GregorianCalendar

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val writeButton=findViewById<ImageView>(R.id.writeBtn)
        writeButton.setOnClickListener{

            val mDialogView=LayoutInflater.from(this).inflate(R.layout.custom_dialog,null)
            val mBuilder=AlertDialog.Builder(this)
                .setView(mDialogView)

            val mAlertDialog=mBuilder.show()

            val DateSelectBtn=mAlertDialog.findViewById<Button>(R.id.dateSelectBtn)
            var dateText = ""
            DateSelectBtn?.setOnClickListener{

                val today=GregorianCalendar()
                val year:Int=today.get(Calendar.YEAR)
                val month:Int=today.get(Calendar.MONTH)
                val date:Int=today.get(Calendar.DATE)

                val dlg=DatePickerDialog(this,object:DatePickerDialog.OnDateSetListener{
                    override fun onDateSet(view:DatePicker?,year:Int,month:Int,dayOfMonth:Int) {
                        Log.d("main","${year},${month+1},${dayOfMonth}")
                        DateSelectBtn.setText("${year}.${month+1}.${dayOfMonth}")

                        dateText = "${year}, ${month + 1}, ${dayOfMonth}"
                    }
                }, year,month,date)
                dlg.show()
            }

            val saveBtn=mAlertDialog.findViewById<Button>(R.id.saveBtn)
            saveBtn?.setOnClickListener{
                // Write a message to the database

                val DiaryMemo=mAlertDialog.findViewById<EditText>(R.id.DairyMemo)?.text.toString()
                val model=DataModel(dateText,DiaryMemo)
                val database = Firebase.database
                val myRef = database.getReference("myMemo")

                myRef
                    .push()
                    .setValue(model)
            }

        }

    }
}