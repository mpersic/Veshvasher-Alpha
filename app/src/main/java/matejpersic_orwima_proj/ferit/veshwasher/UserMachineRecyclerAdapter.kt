package matejpersic_orwima_proj.ferit.veshwasher

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserMachineRecyclerAdapter (private var machines: List<Machine>) :
RecyclerView.Adapter<UserMachineRecyclerAdapter.ViewHolder>() {
    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        var machineName: TextView =itemView.findViewById(R.id.userMachineName)
        var machineProgramme: TextView =itemView.findViewById(R.id.userMachineProgramme)
        //var removeMachine: ImageView =itemView.findViewById(R.id.removeMachineImage)
        init {
           /* removeMachine.setOnClickListener{
                //TODO
            }*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val v = LayoutInflater.from(parent.context).inflate(R.layout.machine_cell,parent,false)
        return ViewHolder(v)
    }


    override fun getItemCount(): Int {
        return machines.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.machineName.text=machines[position].name
        holder.machineProgramme.text=machines[position].programme
        //holder.removeMachine.setImageResource(R.drawable.err)
    }
}