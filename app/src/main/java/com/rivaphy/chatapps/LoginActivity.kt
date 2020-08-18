package com.rivaphy.chatapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //untuk toolbar
        setSupportActionBar(toolbar_register)
        supportActionBar!!.title = getString(R.string.text_login) //buat set nama toolbar
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) //buat back ke welcome activity
        toolbar_login.setNavigationOnClickListener {
            val intentToWelcome = Intent(this, WelcomeActivity::class.java)
            startActivity(intentToWelcome)
            finish()
        }
    }
}
