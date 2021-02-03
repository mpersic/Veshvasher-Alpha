package matejpersic_orwima_proj.ferit.veshwasher

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class MachineRecyclerAdapter(private var machines: List<Machine>) :
    RecyclerView.Adapter<MachineRecyclerAdapter.ViewHolder>() {
    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        var machineName: TextView =itemView.findViewById(R.id.machineName)
        var machineProgramme: TextView =itemView.findViewById(R.id.machineProgramme)
        init {
            itemView.setOnClickListener {
                var position: Int = adapterPosition
                if (machines.size > position) {
                    //dbHelper.insertIsUsing(machines[position].name,userEmail)
                    //userFragmentCommunicator?.onButtonClicked(userEmail)
                    //machines.removeAt(position)
                    //notifyItemRemoved(position)
                    adminRemovingMachineActivity(itemView,machines[position].name)
                }
            }
        }
    }

    private fun adminRemovingMachineActivity(itemView: View,machineName: String) {
        itemView.context.startActivity(Intent(itemView.context,AdminRemoveMachineActivity::class.java).putExtra("machineName",machineName))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val v = LayoutInflater.from(parent.context).inflate(R.layout.admin_machine_cell,parent,false)
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