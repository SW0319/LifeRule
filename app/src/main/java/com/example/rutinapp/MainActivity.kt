package com.example.rutinapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.rutinapp.Repo.Rutin
import com.example.rutinapp.placeholder.DataModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.main_toolbar))

        DataModel.init(this)

        val fragmentmanager = supportFragmentManager
        val fragmentTransaction = fragmentmanager.beginTransaction()
        fragmentTransaction.add(R.id.activity_fragment,RutinFragment.newInstance(1))    //parameters : 적용할 Activity Layout, 만들 fragment
        fragmentTransaction.commit()
    /*        b.setOnClickListener {
//            Toast.makeText(this, "adada", Toa st.LENGTH_SHORT).show()
//            var builder = Notification.Builder(this, channelID) //createNotificationChannel과 같은 channelID를 사용한다. 다르면 표기되지 않는다.
//                .setSmallIcon(R.drawable.ic_launcher_foreground)
//                .setContentTitle("dada")
//                .setContentText("inner Text0")
//                .setOngoing(true)   //지워지지 않도록 설정한다.
//
//            NotificationManagerCompat.from(this).notify(45,builder.build())
//        }
//        val channelID = "com.example.rutinapp.chaneel1"
        createNotificationChannel(channelID)
*/
    }
    private fun createNotificationChannel(CHANNEL_ID: String) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {   //Build 한 HardWare 기기 버전이 API 26이상인지 확인한다.
            val name = "channel_Name"   //알림 유형의 이름이 나온다.
            val descriptionText = "description" //설명기입일텐데 어디서 확인하는지는 모르겠다.
            val importance = NotificationManager.IMPORTANCE_HIGH //알림의 중요도를 설정한다.
                // DEFAULT : Basic, 어디서든 보이고, 소리를 만들지만 시각적으로 팝업이 생기지는 않는다.
                // HIGH : DEFAULT + 엿본다?
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply { //NotificationChannel 생성
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager   //as : 타입 캐스트 (실패시 null)
            notificationManager.createNotificationChannel(channel)  //시스템에 등록한다.
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.e("test","onCreateOptionsMenu()")
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.e("test","버튼 선택")
        if(item.itemId == R.id.addrutin)
        {
            Log.e("test","루틴추가 버튼 선택")
//            DataModel.rutinViewModel.lists.add(Rutin(title = "테스트", content = "내용입니다."))
            val intent = Intent(this,DetailActivity::class.java)
            startActivity(intent)

        }

        return super.onOptionsItemSelected(item)
    }

    companion object
    {

    }



}