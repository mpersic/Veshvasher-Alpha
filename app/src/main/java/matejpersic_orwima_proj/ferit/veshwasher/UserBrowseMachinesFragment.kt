package matejpersic_orwima_proj.ferit.veshwasher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class UserBrowseMachinesFragment : Fragment() {
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
        addMachines()
        //setUpApiCall(apicall)
        recyclerView.adapter=MachineRecyclerAdapter(machines)
    }

    private fun addMachines() {
        machines.add(Machine("Gorenje 12","1"))
        machines.add(Machine("Gorenje 123","4"))
    }

}