package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    // textview variable
    private var screen: TextView? = null
    var LastN = false
    var LastD = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        screen = findViewById(R.id.screen)

    }
// function for input of number
    fun onDigit(view: View) {
        screen?.append((view as Button).text)
        LastN = true
        LastD = false
    }
// function for input of decimal
    fun onDecimal(view: View) {
    var dec = screen?.text.toString()
    if (dec.contains(".")) {
        var split = dec.split("+", "-", "*", "/", "%")
        var one = split[0]
        var two = split[1]
        if (LastN && !LastD) {
            if (two.contains(".")) {
                screen?.append("")
            } else if (one.contains(".")) {
                screen?.append(".")
                LastN = false
                LastD = true
            }
        }
    }
        else{
            if (LastN && !LastD) {
                screen?.append(".")
                LastN = false
                LastD = true
            }
    }
}
    // function for input of operator
    fun onOperator(view: View) {
        screen?.text?.let {
            if (LastN && !operator(it.toString())){
                screen?.append((view as Button).text)
                LastN = false
                LastD = false
            }
        }
    }
// method for input of operator
    private fun operator(value:String):Boolean{
        return if(value.startsWith("-")){
            false
        }else {
            value.contains("/")||value.contains("*")||value.contains("+")||value.contains("-")
        }
    }
// function for removing double in case of integers
    /*fun zero(string : String) : String{
        var string = screen?.text.toString()
        if (string.contains(".0")){
            var l = string.length
            string = string.substring(0,l-2)
        }
        return string
    }*/
// function for calculating values
    fun onEqual(view: View){
        if(LastN){
            var value = screen?.text.toString()
            var prefix = ""
            try {
                if (value.startsWith("-")) {
                    prefix = "-"
                    value = value.substring(1)
                }
                else if (value.contains("-")) {
                    val splitvalue1 = value.split("-")
                    var one1 = splitvalue1[0]
                    var two1 = splitvalue1[1]
                    if (prefix.isNotEmpty()){
                        one1 = prefix + one1
                    }
                    var result = one1.toDouble() - two1.toDouble()
                    var dot  = (result.toString())
                    screen?.text = dot
                }
                else if (value.contains("+")) {
                    val splitvalue2 = value.split("+")
                    var one2 = splitvalue2[0]
                    var two2 = splitvalue2[1]
                    if (prefix.isNotEmpty()){
                        one2 = prefix + one2
                    }
                    var result = one2.toDouble() + two2.toDouble()
                    screen?.text = (result.toString())
                }
                else if (value.contains("*")) {
                    val splitvalue3 = value.split("*")
                    var one3 = splitvalue3[0]
                    var two3 = splitvalue3[1]
                    if (prefix.isNotEmpty()){
                        one3 = prefix + one3
                    }
                    var result = one3.toDouble() * two3.toDouble()
                    screen?.text = (result.toString())
                }
                 else if (value.contains("/")) {
                    val splitvalue4 = value.split("/")
                    var one4 = splitvalue4[0]
                    var two4 = splitvalue4[1]
                    if (prefix.isNotEmpty()){
                        one4 = prefix + one4
                    }
                    var result = one4.toDouble() / two4.toDouble()
                    screen?.text = (result.toString())
                }
                 else if (value.contains("%")) {
                    val splitvalue5 = value.split("%")
                    var one5 = splitvalue5[0]
                    var two5 = splitvalue5[1]
                    if (prefix.isNotEmpty()){
                        one5 = prefix + one5
                    }
                    var result = (one5.toDouble() * two5.toDouble()) / 100
                    screen?.text = (result.toString())
                }

            }catch (e:java.lang.ArithmeticException){
                e.printStackTrace()
            }
        }
    }
    // function for clear screen
    fun clear(view: View){
        screen?.text = "".toString()
    }
}