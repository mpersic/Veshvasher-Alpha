package matejpersic_orwima_proj.ferit.veshwasher

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserUsingMachineRecyclerAdapter (var machines: ArrayList<Machine>,var databaseHelper: DatabaseHelper,var email:String) :
    RecyclerView.Adapter<UserUsingMachineRecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var machineName: TextView = itemView.findViewById(R.id.userMachineName)
        var machineProgramme: TextView = itemView.findViewById(R.id.userMachineProgramme)
        var dbHelper = databaseHelper
        var userEmail=email

        init {
            itemView.setOnClickListener {
                var position: Int = adapterPosition
                if (machines.size > position) {
                    dbHelper.insertIsNotUsing(machines[position].name,userEmail)
                    machines.removeAt(position)
                    notifyItemRemoved(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.machine_cell, parent, false)
        return ViewHolder(v)
    }


    override fun getItemCount(): Int {
        return machines.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.machineName.text = machines[position].name
        holder.machineProgramme.text = machines[position].programme
    }
}
