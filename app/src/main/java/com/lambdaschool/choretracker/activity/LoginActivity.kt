package com.lambdaschool.choretracker.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.lambdaschool.choretracker.R
import com.lambdaschool.choretracker.fragment.RegistrationFragment
import com.lambdaschool.choretracker.model.LoginCredential
import com.lambdaschool.choretracker.model.LoginCredentialList
import com.lambdaschool.choretracker.model.RegisterAPI
import com.lambdaschool.choretracker.util.openSoftKeyboard
import kotlinx.android.synthetic.main.activity_login.*



class LoginActivity : AppCompatActivity(),
    RegistrationFragment.OnRegistrationFragmentInteractionListener {

    companion object {
        const val FRAG_TAG_REGISTRATION = "0HIOIHQ0IGOQG"
        const val LINEAR_LAYOUT_VISIBILITY_KEY = "KJUGBHA0S8IHBVGOU1H0EI9FNPIQSHJ0FIPAISDN09HA9SUID"
    }

    override fun onRegistrationFragmentInteraction(
        registrationInfo: RegisterAPI,
        clickedRegister: Boolean) {

        if (!clickedRegister && registrationInfo.name == LINEAR_LAYOUT_VISIBILITY_KEY) {
            ll_login.visibility = View.VISIBLE
            et_login_username.requestFocus()
            openSoftKeyboard(this, et_login_username)
        } else if (clickedRegister) {
            Toast.makeText(this, "Name: ${registrationInfo.name}\n" +
                    "Email: ${registrationInfo.email}\n" +
                    "Username: ${registrationInfo.username}\n" +
                    "Password: ${registrationInfo.password}",
                Toast.LENGTH_SHORT).show()

            simulateNetworkCall()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        LoginCredentialList.loginCredentialList.add(LoginCredential("parent", "", true))
        LoginCredentialList.loginCredentialList.add(LoginCredential("child", "", false))

        et_login_username.requestFocus()
        openSoftKeyboard(this, et_login_username)

        var counter = 0

        btn_login.setOnClickListener {

            simulateNetworkCall()

            LoginCredentialList.loginCredentialList.forEach {

                if (et_login_username.text.toString() == it.username) {
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

            ll_login.visibility = View.GONE

            val fragmentRegister = RegistrationFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.registration_fragment_holder, fragmentRegister, FRAG_TAG_REGISTRATION)
                .addToBackStack(null)
                .commit()
            /*val url = "https://github.com/Build-Week-Chore-Tracker/android"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)*/
        }
    }

    override fun onBackPressed() {
        val count = getBackStackCount()
        if (count != 0) {
            val fragment = getRegistrationFragmentByTag()
            fragment.closeFragmentCleanup()
        } else {
            super.onBackPressed()
        }
    }

    private fun simulateNetworkCall() {
        pb_login.visibility = View.VISIBLE
        val handler = Handler()
        handler.postDelayed({
            pb_login.visibility = View.INVISIBLE
        }, 3000)
    }

    private fun getBackStackCount(): Int {
        return supportFragmentManager.backStackEntryCount
    }

    private fun getRegistrationFragmentByTag(): RegistrationFragment {
        return supportFragmentManager.findFragmentByTag(FRAG_TAG_REGISTRATION) as RegistrationFragment
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
