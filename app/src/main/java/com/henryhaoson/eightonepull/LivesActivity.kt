package com.henryhaoson.eightonepull

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class LivesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lives)
        initView()
    }

    fun initView() {
        findViewById<Button>(R.id.lives_ttt).setOnClickListener {
            gotoRecord("rtmp://101.132.176.147/live/jiayue?key=henry123")
        }
        findViewById<Button>(R.id.lives_enter).setOnClickListener {
            val url = findViewById<EditText>(R.id.lives_url).text
            if (!TextUtils.isEmpty(url.trim())) {
                gotoRecord(url.toString())
            }
        }
    }

    fun gotoRecord(url: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(MainActivity.MEDIA_URL, url)
        startActivity(intent)
    }
}