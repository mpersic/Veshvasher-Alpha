package matejpersic_orwima_proj.ferit.veshwasher

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController

class UserUsingMachinesFragment(userEmail:String,dbHelper: DatabaseHelper) : Fragment() {
    private var number:Int = 0
    private var helper = dbHelper
    private var email = userEmail

    lateinit var viewBtn: Button
    lateinit var userEmailTextView: TextView
    lateinit var userMachineNumber: TextView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_using_machines, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        countMachines(email)
        initializeViews(view)
        setUpListeners()
    }

    private fun initializeViews(view: View) {
        userEmailTextView = view.findViewById(R.id.usingMachinesUser)
        userEmailTextView.text = email
        userMachineNumber = view.findViewById(R.id.usingMachinesNumber)
        userMachineNumber.text = number.toString()
        viewBtn=view.findViewById(R.id.view_btn)
    }

    private fun setUpListeners() {
        viewBtn.setOnClickListener {
            activity?.let {
                val intent= Intent(it,UserUsingMachinesActivity::class.java).putExtra("Email",email)
                startActivity(intent)
            }
        }
    }

    private fun countMachines(userID: String) {
        var counter = 0
        val cursor: Cursor = helper.readAllMachinesUserIsUsing(userID)
        while (cursor.moveToNext()) {
            counter++
        }
        number = counter
    }
}