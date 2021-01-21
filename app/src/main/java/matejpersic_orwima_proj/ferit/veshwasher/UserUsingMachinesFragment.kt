package matejpersic_orwima_proj.ferit.veshwasher

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class UserUsingMachinesFragment(userEmail:String,dbHelper: DatabaseHelper) : Fragment() {

    private var number = 0
    private var helper = dbHelper
    private var email = userEmail

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
        //TODO treba izračunar koliko korisnik mašina koristi i pozdravit ga
        countMachines(email)
        userEmailTextView = view.findViewById(R.id.usingMachinesUser)
        userEmailTextView.text = email
        userMachineNumber = view.findViewById(R.id.usingMachinesNumber)
        userMachineNumber.text = number.toString()
    }

    fun countMachines(userID: String) {
        var counter: Int = 0
        val cursor: Cursor = helper.readAllMachinesUserIsUsing(userID)
        while (cursor.moveToNext()) {
            counter++
        }
        number = counter
    }
}