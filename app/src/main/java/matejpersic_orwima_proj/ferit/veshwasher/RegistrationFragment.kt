package matejpersic_orwima_proj.ferit.veshwasher

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegistrationFragment(dbHelper: DatabaseHelper) : Fragment() {

    var registerEmail: EditText?=null
    var registerPassword: EditText?=null
    var registerConfirmPassword: EditText?=null
    var registerButton: Button?=null
    var databaseHelper:DatabaseHelper=dbHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews(view)
        setUpListeners(view)
    }

    private fun setUpListeners(view: View,) {
        registerButton!!.setOnClickListener {
            if (registerPassword!!.text.toString().equals(registerConfirmPassword!!.text.toString())) {

                if (databaseHelper.userPresent(registerEmail!!.text.toString(),registerPassword!!.text.toString())) {
                    Toast.makeText(activity, "User is already registered", Toast.LENGTH_SHORT).show()
                } else {
                    databaseHelper.insertUserData(registerEmail!!.text.toString(), registerPassword!!.text.toString())
                    if (userIsAdmin())
                        startAdminActivity()
                    else
                        startUserActivity(registerEmail!!.text.toString())
                }
            } else {
                Toast.makeText(activity, "Passwords dont match!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startUserActivity(registerEmail:String) {
        activity?.let {
            val intent=Intent(it,UserBottomNavActivity::class.java)
            intent.putExtra("Email",registerEmail)
            startActivity(intent)
        }
    }

    private fun startAdminActivity() {
        activity?.let {
            val intent= Intent(it,AdminMainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun userIsAdmin(): Boolean {
        return registerEmail!!.text.toString().equals("admin")&&registerPassword!!.text.toString().equals("password")
    }

    private fun initializeViews(view: View) {
        registerEmail=view.findViewById(R.id.register_et_mail)
        registerPassword=view.findViewById(R.id.register_et_password)
        registerConfirmPassword=view.findViewById(R.id.register_et_confirm_password)
        registerButton=view.findViewById(R.id.register_btn)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }
}