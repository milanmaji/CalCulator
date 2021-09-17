package com.sitapuramargram.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.sitapuramargram.mycalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var lastNumeric: Boolean = false
    var lastDot: Boolean = false
    lateinit var activityMainBinding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        var view: View = activityMainBinding.root
        setContentView(view)

        activityMainBinding.btnAddition.setOnClickListener(this)
        activityMainBinding.btnSubtraction.setOnClickListener(this)
        activityMainBinding.btnDevided.setOnClickListener(this)
        activityMainBinding.btnMultiplication.setOnClickListener(this)
        activityMainBinding.btnClear.setOnClickListener(this)
        activityMainBinding.btnResult.setOnClickListener(this)
        activityMainBinding.btnDot.setOnClickListener(this)
        activityMainBinding.btnZero.setOnClickListener(this)
        activityMainBinding.btnOne.setOnClickListener(this)
        activityMainBinding.btnTwo.setOnClickListener(this)
        activityMainBinding.btnThree.setOnClickListener(this)
        activityMainBinding.btnFour.setOnClickListener(this)
        activityMainBinding.btnFive.setOnClickListener(this)
        activityMainBinding.btnSix.setOnClickListener(this)
        activityMainBinding.btnSeven.setOnClickListener(this)
        activityMainBinding.btnEight.setOnClickListener(this)
        activityMainBinding.btnNine.setOnClickListener(this)
    }

    override fun onClick(view: View?) {

        Log.d("tag","Button Clicked")
        when (view?.id) {


            R.id.btnClear -> onClear()
            R.id.btnDot -> onDecimalPoint(view)
            R.id.btnAddition,
            R.id.btnDevided,
            R.id.btnMultiplication,
            R.id.btnSubtraction -> onOperator(view)
            R.id.btnResult -> onEqual()
            else -> view?.let { onDigit(it) }

        }
    }

    fun onDigit(view: View) {
        activityMainBinding.tvInput.append((view as Button).text)
        lastNumeric = true
    }

    fun onClear() {

        activityMainBinding.tvInput.text = ""
        lastNumeric = false
        lastDot = false
    }

    fun onDecimalPoint(view: View) {

        if (lastNumeric && !lastDot) {
            activityMainBinding.tvInput.append((view as Button).text)
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view: View) {

        if (lastNumeric && !isOperatorAdded(activityMainBinding.tvInput.text.toString())) {
            activityMainBinding.tvInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }


    }

    private fun isOperatorAdded(value: String): Boolean {

        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("*")
                    || value.contains("+") || value.contains("-")
        }

    }

    fun onEqual(){

        if(lastNumeric){
            var tvValue = activityMainBinding.tvInput.text.toString()
            var prefix = ""
            try {

                if (tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if(tvValue.contains("-")){

                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    activityMainBinding.tvInput.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())

                } else if(tvValue.contains("+")){

                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    activityMainBinding.tvInput.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                } else if(tvValue.contains("*")){

                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    activityMainBinding.tvInput.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                } else if(tvValue.contains("/")){

                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    activityMainBinding.tvInput.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }




            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }
    private fun removeZeroAfterDot(result: String): String{

        var value = result
        if(result.contains(".0")){
            value = result.substring(0,result.length-2)
            return value
        } else{
            return value
        }

    }


}