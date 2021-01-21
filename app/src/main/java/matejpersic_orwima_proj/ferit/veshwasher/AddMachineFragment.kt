package matejpersic_orwima_proj.ferit.veshwasher

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction


class AddMachineFragment(dbHelper:DatabaseHelper) : Fragment() {

    var fc:FragmentCommunicator?=null
    var databaseHelper:DatabaseHelper=dbHelper
    lateinit var machineName:TextView
    lateinit var machineProgram:TextView
    lateinit var addMachineButton: Button
    private lateinit var communicator: FragmentCommunicator

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
                //fc.onButtonClicked(machineName.text.toString(),
                  //                 machineProgram.text.toString())
                //TODO treba stavit da ide na fragment 0 i da ga rekreira, navodno kao na lvu
                machineName.text=""
                machineProgram.text=""
            }
        }
        //machineName.addTextChangedListener(context)
    }

}