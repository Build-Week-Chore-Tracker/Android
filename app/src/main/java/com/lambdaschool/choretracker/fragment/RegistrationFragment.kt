package com.lambdaschool.choretracker.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.lambdaschool.choretracker.R
import com.lambdaschool.choretracker.activity.LoginActivity
import com.lambdaschool.choretracker.model.RegisterAPI
import com.lambdaschool.choretracker.util.openSoftKeyboard
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_registration.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [RegistrationFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [RegistrationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegistrationFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnRegistrationFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        et_registration_name.requestFocus()
        openSoftKeyboard(context, et_registration_name)

        ib_registration_fragment_close.setOnClickListener {
            closeFragmentCleanup()
        }

        btn_registration_submit.setOnClickListener {
            val regName = et_registration_name.text.toString()
            val regUserName = et_registration_username.text.toString()
            val regEmail = et_registration_email.text.toString()
            val regPassword = et_registration_password.text.toString()

            listener?.onRegistrationFragmentInteraction(RegisterAPI(
                regName,
                regUserName,
                regEmail,
                regPassword), true)

        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnRegistrationFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnRegistrationFragmentInteractionListener {
        fun onRegistrationFragmentInteraction(registrationInfo: RegisterAPI, clickedRegister: Boolean)
    }

    fun closeFragmentCleanup() {
        listener?.onRegistrationFragmentInteraction(RegisterAPI(
            LoginActivity.LINEAR_LAYOUT_VISIBILITY_KEY, "", "", ""),
            false)

        activity?.supportFragmentManager?.beginTransaction()
            ?.remove(this)
            ?.commit()
        activity?.supportFragmentManager?.popBackStack()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegistrationFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegistrationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
