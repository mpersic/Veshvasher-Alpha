package matejpersic_orwima_proj.ferit.veshwasher

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable

class AdminMachinesFragment(dbHelper: DatabaseHelper) : Fragment(),Serializable {
    private var machines: ArrayList<Machine> = arrayListOf()
    private var databaseHelper:DatabaseHelper=dbHelper
    var machineName:String?=""
    var machineProgramme:String?=""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_admin_machines, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView =view.findViewById(R.id.machinesRecyclerView)
        recyclerView.layoutManager= LinearLayoutManager(context)
        //addMachines()
        //databaseHelper.initialValues()
        /*machineName=arguments?.getString("machineName")
        machineProgramme=arguments?.getString("machineProgramme")
        if((machineName?.isNotEmpty() as Boolean)||(machineProgramme?.isNotEmpty() as Boolean)) {
            machines.add(Machine(machineName as String, machineProgramme as String))
        }*/
        displayData()
        recyclerView.adapter=MachineRecyclerAdapter(machines)
    }

    private fun displayData(){
        val cursor: Cursor =databaseHelper.readAllMachines()
        if(cursor.count==0){
            Toast.makeText(context,"no data",Toast.LENGTH_SHORT).show()
        }
        else{
            while(cursor.moveToNext()){
                machines.add(Machine(cursor.getString(0),cursor.getString(1)))
            }
        }
    }

    fun displayMachine(machine: Machine) {
        machines.add(machine)
        displayData()
    }


}
