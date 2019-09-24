package com.lambdaschool.choretracker.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.lambdaschool.choretracker.R
import com.lambdaschool.choretracker.model.LoginCredential
import com.lambdaschool.choretracker.model.LoginCredentialList
import com.lambdaschool.choretracker.util.openSoftKeyboard
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        LoginCredentialList.loginCredentialList.add(LoginCredential("parent", "", true))
        LoginCredentialList.loginCredentialList.add(LoginCredential("child", "", false))

        et_login_email.requestFocus()
        openSoftKeyboard(this, et_login_email)

        var counter = 0

        btn_login.setOnClickListener {

            LoginCredentialList.loginCredentialList.forEach {

                if (et_login_email.text.toString() == it.username) {
                    counter++
                    if (it.isParent) {
                        loginUserType(this, it.isParent)
                    } else {
                        loginUserType(this, it.isParent)
                    }
                }
            }

            if (counter == 0) {
                Toast.makeText(this, "please login as 'parent' or 'child'", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        btn_register.setOnClickListener {
            val url = "https://github.com/Build-Week-Chore-Tracker/android"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }

    private fun loginUserType(context: Context, isParent: Boolean) {
        var intent = Intent()
        if (isParent) {
            intent = Intent(context, ParentMainActivity::class.java)
        } else {
            intent = Intent(context, ChildMainActivity::class.java)
        }
        startActivity(intent)
    }
}
