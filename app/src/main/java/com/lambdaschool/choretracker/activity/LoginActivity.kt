package com.lambdaschool.choretracker.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lambdaschool.choretracker.R
import com.lambdaschool.choretracker.util.openSoftKeyboard
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        et_login_email.requestFocus()
        openSoftKeyboard(this, et_login_email)

        btn_login.setOnClickListener {

        }

        btn_register.setOnClickListener {

        }
    }
}
