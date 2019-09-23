package com.lambdaschool.choretracker.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
            if (et_login_email.text.toString() == "parent") {
                val intent = Intent(this, ParentMainActivity::class.java)
                startActivity(intent)
            } else if (et_login_email.text.toString() == "child") {
                val intent = Intent(this, ChildMainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Accepted username is either\n\n'child' OR 'parent'", Toast.LENGTH_SHORT).show()
            }
        }

        btn_register.setOnClickListener {

        }
    }
}
