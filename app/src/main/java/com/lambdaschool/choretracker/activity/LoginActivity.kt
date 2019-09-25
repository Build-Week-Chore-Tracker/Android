package com.lambdaschool.choretracker.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.lambdaschool.choretracker.R
import com.lambdaschool.choretracker.fragment.RegistrationFragment
import com.lambdaschool.choretracker.model.CredentialsAPI
import com.lambdaschool.choretracker.util.Prefs
import com.lambdaschool.choretracker.util.openSoftKeyboard
import com.lambdaschool.choretracker.viewmodel.LoginActivityViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(),
    RegistrationFragment.OnRegistrationFragmentInteractionListener {

    companion object {
        const val FRAG_TAG_REGISTRATION = "0HIOIHQ0IGOQG"
        const val LINEAR_LAYOUT_VISIBILITY_KEY = "KJUGBHA0S8IHBVGOU1H0EI9FNPIQSHJ0FIPAISDN09HA9SUID"
    }

    lateinit var viewModel: LoginActivityViewModel
    var prefs: Prefs? = null

    override fun onRegistrationFragmentInteraction(
        registrationInfo: CredentialsAPI,
        clickedRegister: Boolean) {

        if (!clickedRegister && registrationInfo.name == LINEAR_LAYOUT_VISIBILITY_KEY) {

            ll_login.visibility = View.VISIBLE
            et_login_username.requestFocus()
            openSoftKeyboard(this, et_login_username)

        } else if (clickedRegister) {

            if (registrationInfo.username.isEmpty() || registrationInfo.password.isEmpty()) {
                Toast.makeText(this, "Username & Password fields are required for registration", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        prefs = Prefs(this)

        et_login_username.requestFocus()
        openSoftKeyboard(this, et_login_username)

        btn_login.setOnClickListener {

            val logUserName = et_login_username.text.toString()
            val logPassword = et_login_password.text.toString()

            if (logUserName.isNotEmpty() && logPassword.isNotEmpty()) {
                viewModel.loginUser(CredentialsAPI("", logUserName, "", logPassword))
                    .observe(this, Observer {
                        if (it) {
                            val loginCreds = prefs?.readLoginCredentials()
                            Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
                        }
                    })
            }
        }

        btn_register.setOnClickListener {

            ll_login.visibility = View.GONE

            val fragmentRegister = RegistrationFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.registration_fragment_holder, fragmentRegister, FRAG_TAG_REGISTRATION)
                .addToBackStack(null)
                .commit()
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
