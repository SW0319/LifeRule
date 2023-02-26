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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    lateinit var rutinTitle: EditText
    lateinit var rutinContents: EditText
    lateinit var toggleLists : Array<ToggleButton>
    var modifierMode = false
    lateinit var rutin: Rutin

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
                    val newRutin :Rutin = Rutin(0,titleText,contentsText,toggleLists[0].isChecked
                        ,toggleLists[1].isChecked,toggleLists[2].isChecked,toggleLists[3].isChecked,toggleLists[4].isChecked
                        ,toggleLists[5].isChecked,toggleLists[6].isChecked)
                    if (!modifierMode)
                        DataModel.rutinViewModel.addItem(newRutin)
                    else
                    {
                        rutin.title = titleText
                        rutin.content = contentsText
                        rutin.sunday = toggleLists[0].isChecked
                        rutin.monday = toggleLists[1].isChecked
                        rutin.tueday = toggleLists[2].isChecked
                        rutin.wedday = toggleLists[3].isChecked
                        rutin.thuday = toggleLists[4].isChecked
                        rutin.friday = toggleLists[5].isChecked
                        rutin.satday = toggleLists[6].isChecked
                        DataModel.rutinViewModel.updateItem(rutin)
                    }
                    finish()
                }
        }
    }

    private fun checkIsModifierMode()
    {
        val datas: Bundle? = intent.extras
        if(datas != null)
        {
            rutin = DataModel.rutinViewModel.searchItem(datas.get("uid") as Int)
            CoroutineScope(Dispatchers.IO).launch {
                rutinTitle.setText(rutin.title)
                rutinContents.setText(rutin.content)
                toggleLists[0].isChecked = rutin.sunday
                toggleLists[1].isChecked = rutin.monday
                toggleLists[2].isChecked = rutin.tueday
                toggleLists[3].isChecked = rutin.wedday
                toggleLists[4].isChecked = rutin.thuday
                toggleLists[5].isChecked = rutin.friday
                toggleLists[6].isChecked = rutin.satday
            }

            modifierMode = true
        }
    }
}