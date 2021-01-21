package matejpersic_orwima_proj.ferit.veshwasher

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class UserBrowseMachinesFragment(dbHelper:DatabaseHelper) : Fragment() {
    private var helper=dbHelper
    private var machines: ArrayList<Machine> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_browse_machines, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView =view.findViewById(R.id.userMachinesRecyclerView)
        recyclerView.layoutManager= LinearLayoutManager(context)
        //addMachines()
        recyclerView.adapter=UserMachineRecyclerAdapter(machines)
        //helper.initialValues()
        //helper.clearAll()
        displayData()
        //addMachines()
    }

    private fun addMachines() {
        machines.add(Machine("Gorenje 12","1"))
        machines.add(Machine("Gorenje 123","4"))
    }

    private fun displayData(){
        val cursor: Cursor=helper.readAllAvailableMachines()
        if(cursor.count==0){
            Toast.makeText(context,"no data",Toast.LENGTH_SHORT).show()
        }
        else{
            while(cursor.moveToNext()){
                machines.add(Machine(cursor.getString(0),cursor.getString(1)))
            }
        }
    }

}