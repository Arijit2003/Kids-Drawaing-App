package com.example.drawingapp

import android.app.Dialog
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get

class MainActivity : AppCompatActivity() {
    var drawingView:DrawingView?=null
    var brushIcon:ImageView?=null
    var colorImageButton:ImageButton?=null
    private var colorPalate:LinearLayout?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawingView=findViewById(R.id.drawingView)
        brushIcon=findViewById(R.id.brushIcon)
        brushIcon?.setOnClickListener(){
            showBrushSizeDialog()
        }
        colorPalate=findViewById(R.id.colorPalate)
        colorImageButton=colorPalate!![4] as ImageButton
        colorImageButton!!.setImageDrawable(
            ContextCompat.getDrawable(this,R.drawable.palate_pressed)
        )
        

    }
    fun selectColor(view:View){
        if(view!=colorImageButton){
            val imageButtonColor=view as ImageButton
            val colorTag=imageButtonColor.tag.toString()
            drawingView?.changeColor(colorTag)
            imageButtonColor.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.palate_pressed))
            colorImageButton?.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.palate_normal))
            colorImageButton=imageButtonColor

        }
    }
    private fun showBrushSizeDialog(){
        val brushDialog=Dialog(this)
        val layoutInflater:LayoutInflater=LayoutInflater.from(this)
        val view:View=layoutInflater.inflate(R.layout.brush_size_dialog,null)
        brushDialog.setContentView(view)
        val small:ImageView=view.findViewById(R.id.small)
        val medium:ImageView=view.findViewById(R.id.medium)
        val large:ImageView=view.findViewById(R.id.large)
        small.setOnClickListener(){
            drawingView?.setUpBrushSize(5.toFloat())
            brushDialog.dismiss()
        }
        medium.setOnClickListener(){
            drawingView?.setUpBrushSize(10.toFloat())
            brushDialog.dismiss()

        }
        large.setOnClickListener(){
            drawingView?.setUpBrushSize(15.toFloat())
            brushDialog.dismiss()

        }
        brushDialog.setCancelable(false)
        brushDialog.show()
    }
}