package com.lambdaschool.choretracker.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.lambdaschool.choretracker.R
import com.lambdaschool.choretracker.fragment.RegistrationFragment
import com.lambdaschool.choretracker.model.ChildLoginCredential
import com.lambdaschool.choretracker.model.CredentialsAPI
import com.lambdaschool.choretracker.model.LoginReturnedAPI
import com.lambdaschool.choretracker.util.Prefs
import com.lambdaschool.choretracker.util.openSoftKeyboard
import com.lambdaschool.choretracker.viewmodel.LoginActivityViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(),
    RegistrationFragment.OnRegistrationFragmentInteractionListener {

    companion object {
        const val FRAG_TAG_REGISTRATION = "0HIOIHQ0IGOQG"
        const val LINEAR_LAYOUT_VISIBILITY_KEY = "KJUGBHA0S8IHBVGO0FIPAISDN09HA9SUID"
    }

    lateinit var viewModel: LoginActivityViewModel
    var prefs: Prefs? = null

    override fun onRegistrationFragmentInteraction(key: String, clickedRegister: Boolean) {

        if (!clickedRegister && key == LINEAR_LAYOUT_VISIBILITY_KEY) {

            pb_login.visibility = View.INVISIBLE
            ll_login.visibility = View.VISIBLE
            et_login_username.requestFocus()
            openSoftKeyboard(this, et_login_username)

        } else if (clickedRegister) {
            pb_login.visibility = View.VISIBLE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        prefs = Prefs(this)
        viewModel = ViewModelProviders.of(this).get(LoginActivityViewModel::class.java)

        et_login_username.requestFocus()
        openSoftKeyboard(this, et_login_username)

        btn_login.setOnClickListener {

            val logUserName = et_login_username.text.toString()
            val logPassword = et_login_password.text.toString()

            if (logUserName.isNotEmpty() && logPassword.isNotEmpty()) {
                pb_login.visibility = View.VISIBLE
                viewModel.loginUser(CredentialsAPI("", logUserName, "", logPassword))
                    .observe(this, Observer {
                        if (it) {
                            val loginCreds = prefs?.getLoginCredentials()
                            pb_login.visibility = View.INVISIBLE
                            Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()

                            val intent = Intent(this, ParentMainActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
                        }
                    })
            } else {
                Toast.makeText(this, "Please enter a username & password", Toast.LENGTH_SHORT).show()
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

        btn_login_child.setOnClickListener {

            simulateNetworkCall()
            val logUserName = et_login_username.text.toString()
            val logPassword = et_login_password.text.toString()
            var childId = -1

            viewModel.createChildLoginCredential(ChildLoginCredential(logUserName, logPassword))

            val item = viewModel.getChildLoginCredentialForUsernamePassword(logUserName, logPassword)
            val a = item.value?.username
            val b = item.value?.password
            val c = item.value?.child_id

            if (item.value?.username == logUserName && item.value?.password == logPassword) {
                item.value?.child_id?.let {
                    childId = it
                }

                prefs?.deleteLoginCredentials()
                prefs?.createLoginCredentialEntry(LoginReturnedAPI("", "", childId))
                val intent = Intent(this, ChildMainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
            }


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
        }, 1500)
    }

    private fun getBackStackCount(): Int {
        return supportFragmentManager.backStackEntryCount
    }

    private fun getRegistrationFragmentByTag(): RegistrationFragment {
        return supportFragmentManager.findFragmentByTag(FRAG_TAG_REGISTRATION) as RegistrationFragment
    }
}
