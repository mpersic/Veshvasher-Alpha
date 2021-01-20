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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AdminMachinesFragment(dbHelper: DatabaseHelper) : Fragment() {
    private var machines: ArrayList<Machine> = arrayListOf()
    private var dbHelper=dbHelper
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
        addMachines()
        //setUpApiCall(apicall)
        recyclerView.adapter=MachineRecyclerAdapter(machines)
    }

    private fun getMachines(){
        var db=dbHelper.readableDatabase
        var rs=db.rawQuery("SELECT * FROM machines",null)
        if(rs.moveToNext()){
            Toast.makeText(context,rs.getString(1),Toast.LENGTH_SHORT).show()
        }
    }

    private fun addMachines() {
        dbHelper.insertMachine("Gorenje","1","1")
        dbHelper.insertMachine("Gorenje 5","1","1")
        machines.add(Machine("Gorenje 12","1"))
        machines.add(Machine("Gorenje 123","4"))
    }

}
