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

class LoginFragment(dbHelper: DatabaseHelper) : Fragment() {

    var databaseHelper:DatabaseHelper=dbHelper
    var loginEmailEt: EditText? = null
    var loginPasswordEt: EditText? = null
    var loginButton: Button? = null
    var dbHelper=dbHelper

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginEmailEt = view.findViewById(R.id.login_et_Email)
        loginPasswordEt = view.findViewById(R.id.login_et_Password)
        loginButton = view.findViewById(R.id.login_btn)
        setUpListeners()
    }

    private fun setUpListeners() {
        loginButton!!.setOnClickListener {
            if(databaseHelper!!.userPresent(loginEmailEt!!.text.toString(),loginPasswordEt!!.text.toString())){
                if(userIsAdmin()){
                    startAdminActivity(loginEmailEt!!.text.toString(),dbHelper)
                }
                else{
                    startUserActivity()
                }
            }
            else
                Toast.makeText(activity,"Invalid credentials!",Toast.LENGTH_SHORT).show()
        }
            Toast.makeText(activity,"Invalid credentials!",Toast.LENGTH_SHORT).show()
        }

    private fun startUserActivity() {
        activity?.let {
            val intent= Intent(it,UserBottomNavActivity::class.java)
            startActivity(intent)
        }
    }

    private fun startAdminActivity(loginEmail:String,dbHelper: DatabaseHelper) {
        activity?.let {
            val intent= Intent(it,AdminMainActivity::class.java)
            intent.putExtra("loginEmail",loginEmail)
            intent.putExtra("dbHelper",dbHelper)
            startActivity(intent)
        }
    }

    private fun userIsAdmin(): Boolean {
        return loginEmailEt!!.text.toString().equals("admin")&&loginPasswordEt!!.text.toString().equals("password")
    }
}
