package com.example.chaquopytrial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.chaquo.python.PyObject
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform

class MainActivity : AppCompatActivity() {

    //get ui elements
    lateinit var num1 :EditText
    lateinit var num2 :EditText
    lateinit var sum :TextView
    lateinit var add_btn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //attach elements to xml
        num1 = findViewById(R.id.num1)
        num2 = findViewById(R.id.num2)
        sum = findViewById(R.id.sum)
        add_btn = findViewById(R.id.add_btn)

        //initialize python
        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(this));
        }

        //get python script
        val py: Python = Python.getInstance()

        // default path is "src/main/python"
        // so we just mention the script name in that folder
        val pyScript:PyObject = py.getModule("script")

        // initialize a python function
        var pyFun:PyObject

        add_btn.setOnClickListener {
            //ui code to handle keyboard
            //keyboard will disappear when user press on Add button
            num1.isEnabled=false
            num2.isEnabled=false
            num1.isEnabled=true
            num2.isEnabled=true

            //attach the function to script's function
            pyFun = pyScript.callAttr("main",num1.text.toString(),num2.text.toString())

            //put the output of python function in sum textView
            sum.setText(pyFun.toString())
        }
    }
}