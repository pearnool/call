package com.example.myapplication

//package com.example.vicky.sqliteexample

//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_insert = findViewById<Button>(R.id.btn_insert)
        val etvName = findViewById<EditText>(R.id.etvName)
        val etvAge = findViewById<EditText>(R.id.etvAge)
        val btn_read = findViewById<Button>(R.id.btn_read)
        val tvResult = findViewById<TextView>(R.id.tvResult)
        val btn_update = findViewById<Button>(R.id.btn_update)
        val btn_delete = findViewById<Button>(R.id.btn_delete)

        val context = this
        var db = DataBaseHandler(context)
        btn_insert.setOnClickListener({
            if (etvName.text.toString().length > 0 &&
                etvAge.text.toString().length > 0) {
                var user = User(etvName.text.toString(),etvAge.text.toString().toInt())
                db.insertData(user)
            } else {
                Toast.makeText(context,"Please Fill All Data's",Toast.LENGTH_SHORT).show()
            }
        })

        btn_read.setOnClickListener({
            var data = db.readData()
            tvResult.text = ""
            for (i in 0..(data.size - 1)) {
                tvResult.append(data.get(i).id.toString() + " " + data.get(i).name + " " + data.get(i).age + "\n")
            }
        })

        btn_update.setOnClickListener({
            db.updateData()
            btn_read.performClick()
        })

        btn_delete.setOnClickListener({
            db.deleteData()
            btn_read.performClick()
        })
    }
}
