package matejpersic_orwima_proj.ferit.veshwasher

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class AddMachineFragment(dbHelper:DatabaseHelper) : Fragment(),TextWatcher {

    var fc:AdminFragmentCommunicator?=null
    var databaseHelper:DatabaseHelper=dbHelper
    lateinit var machineName:TextView
    lateinit var machineProgram:TextView
    lateinit var addMachineButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_machine, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners(view)
    }

    private fun setUpListeners(view:View) {
        machineName=view.findViewById(R.id.admin_machineName)
        machineProgram=view.findViewById(R.id.admin_machine_program)
        addMachineButton=view.findViewById(R.id.admin_machine_btn_add)
        addMachineButton.setOnClickListener {
            if(machineName.equals(""))
                Toast.makeText(context,"enter machine name",Toast.LENGTH_LONG).show()
            else if (machineProgram.equals(""))
                Toast.makeText(context,"enter machine program",Toast.LENGTH_LONG).show()
            else {
                databaseHelper.insertMachine(
                    machineName.text.toString(),
                    machineProgram.text.toString(),
                    "1"
                )
                fc!!.onButtonClicked(machineName.text.toString(),
                                machineProgram.text.toString())
                machineName.text=""
                machineProgram.text=""
            }
        }
        //machineName.addTextChangedListener(context)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AdminFragmentCommunicator)
        {
            fc = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        fc=null
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
    }

}