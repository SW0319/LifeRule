package com.example.rutinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.ToggleButton
import com.example.rutinapp.Repo.Rutin
import com.example.rutinapp.placeholder.DataModel
import com.example.rutinapp.placeholder.RutinViewModel

class DetailActivity : AppCompatActivity() {

    lateinit var rutinTitle: EditText
    lateinit var rutinContents: EditText
    lateinit var toggleLists : Array<ToggleButton>
    var modifierMode = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        rutinTitle  = findViewById(R.id.detail_title)
        rutinContents =  findViewById(R.id.detail_contents)
        toggleLists = arrayOf<ToggleButton>(findViewById(R.id.detail_sunday),findViewById(R.id.detail_monday),findViewById(R.id.detail_tueday),
            findViewById(R.id.detail_wedday),findViewById(R.id.detail_thuday),findViewById(R.id.detail_friday)
            ,findViewById(R.id.detail_satday))

        setBottomButtonClicked()
        checkIsModifierMode()
    }
    private fun setOnDayButtonClicked(view: View) {
        val checked = (view as ToggleButton).isChecked
        if(checked)
            view.setBackgroundColor(resources.getColor(R.color.main_background))
        else
            view.setBackgroundColor(resources.getColor(R.color.white))
    }

    private fun setBottomButtonClicked()
    {
        findViewById<Button>(R.id.detail_cancel).setOnClickListener {finish()}

        findViewById<Button>(R.id.detail_complete).setOnClickListener {
            val titleText : String = rutinTitle.text.toString()
            val contentsText: String = rutinContents.text.toString()
                if(titleText == "")
                    Toast.makeText(this,"제목은 반드시 들어가야 합니다.",Toast.LENGTH_SHORT).show()
                else
                {
                    val rutin :Rutin = Rutin(0,titleText,contentsText,toggleLists[0].isChecked
                        ,toggleLists[1].isChecked,toggleLists[2].isChecked,toggleLists[3].isChecked,toggleLists[4].isChecked
                        ,toggleLists[5].isChecked,toggleLists[6].isChecked)
                    if (!modifierMode)
                        DataModel.rutinViewModel.addItem(rutin)
                    else
                        DataModel.rutinViewModel.addItem(rutin)

                    finish()
                }
        }
    }

    private fun checkIsModifierMode()
    {
        val datas: Bundle? = intent.extras
        if(datas != null)
        {
            rutinTitle.setText(datas.get("title").toString())
            rutinContents.setText(datas.get("contents").toString())
            val r = arrayOf("sunday","monday","tueday","wedday","thuday","friday","satday")

            var cnt:Int = -1
            for (a in r.iterator())
            {
                cnt++
                if(datas.getBoolean(a))
                    toggleLists[cnt].toggle()
            }
            modifierMode = true
        }
    }
}