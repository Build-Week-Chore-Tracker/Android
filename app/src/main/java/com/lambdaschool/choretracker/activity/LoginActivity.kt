package com.lambdaschool.choretracker.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.lambdaschool.choretracker.R
import com.lambdaschool.choretracker.model.Chore
import com.lambdaschool.choretracker.model.ChoreList
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
        ChoreList.choreList.add(Chore("Take out trash1", "aoshd09fiqlokwihjoinsa;DKH", 10, false, 0, 0))
        ChoreList.choreList.add(Chore("Take out trash2", "aoshd09fiqlokwihjoinsa;DKH", 10, false, 0, 0))
        ChoreList.choreList.add(Chore("Take out trash3", "aoshd09fiqlokwihjoinsa;DKH", 10, false, 0, 0))
        ChoreList.choreList.add(Chore("Take out trash4", "aoshd09fiqlokwihjoinsa;DKH", 10, false, 0, 0))
        ChoreList.choreList.add(Chore("Take out trash5", "aoshd09fiqlokwihjoinsa;DKH", 10, false, 0, 0))
        ChoreList.choreList.add(Chore("Take out trash6", "aoshd09fiqlokwihjoinsa;DKH", 10, false, 0, 0))
        ChoreList.choreList.add(Chore("Take out trash7", "aoshd09fiqlokwihjoinsa;DKH", 10, false, 0, 0))
        ChoreList.choreList.add(Chore("Take out trash8", "aoshd09fiqlokwihjoinsa;DKH", 10, false, 0, 0))
        ChoreList.choreList.add(Chore("Take out trash9", "aoshd09fiqlokwihjoinsa;DKH", 10, false, 0, 0))
        ChoreList.choreList.add(Chore("Take out trash10", "aoshd09fiqlokwihjoinsa;DKH", 10, false, 0, 0))
        ChoreList.choreList.add(Chore("Take out trash11", "aoshd09fiqlokwihjoinsa;DKH", 10, false, 0, 0))
        ChoreList.choreList.add(Chore("Take out trash12", "aoshd09fiqlokwihjoinsa;DKH", 10, false, 0, 0))
        ChoreList.choreList.add(Chore("Take out trash13", "aoshd09fiqlokwihjoinsa;DKH", 10, false, 0, 0))
        ChoreList.choreList.add(Chore("Take out trash14", "aoshd09fiqlokwihjoinsa;DKH", 10, false, 0, 0))
        ChoreList.choreList.add(Chore("Take out trash15", "aoshd09fiqlokwihjoinsa;DKH", 10, false, 0, 0))
        ChoreList.choreList.add(Chore("Take out trash16", "aoshd09fiqlokwihjoinsa;DKH", 10, false, 0, 0))
        ChoreList.choreList.add(Chore("Take out trash17", "aoshd09fiqlokwihjoinsa;DKH", 10, false, 0, 0))
        ChoreList.choreList.add(Chore("Take out trash18", "aoshd09fiqlokwihjoinsa;DKH", 10, false, 0, 0))
        ChoreList.choreList.add(Chore("Take out trash19", "aoshd09fiqlokwihjoinsa;DKH", 10, false, 0, 0))

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
