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
import java.io.Serializable

class LoginFragment(dbHelper: DatabaseHelper) : Fragment(),Serializable {

    var databaseHelper:DatabaseHelper=dbHelper
    var loginEmailEt: EditText? = null
    var loginPasswordEt: EditText? = null
    var loginButton: Button? = null

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
            if(databaseHelper.userPresent(loginEmailEt!!.text.toString(),loginPasswordEt!!.text.toString())){
                if(userIsAdmin()){
                    startAdminActivity()
                }
                else{
                    startUserActivity(loginEmailEt!!.text.toString())
                }
            }
            else
                if(loginEmailEt!!.text.toString().trim() != ""&&loginPasswordEt!!.text.toString().trim() != "")
                Toast.makeText(activity,"Invalid credentials!",Toast.LENGTH_SHORT).show()
        }
        if(loginEmailEt!!.text.toString().trim() != ""&&loginPasswordEt!!.text.toString().trim() != "")
            Toast.makeText(activity,"Invalid credentials!",Toast.LENGTH_SHORT).show()
        }

    private fun startUserActivity(loginEmail:String) {
        activity?.let {
            val intent= Intent(it,UserBottomNavActivity::class.java)
            intent.putExtra("Email",loginEmail)
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
        return loginEmailEt!!.text.toString().equals("admin")&&loginPasswordEt!!.text.toString().equals("password")
    }
}
