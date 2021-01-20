package matejpersic_orwima_proj.ferit.veshwasher

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text


class EmployeeRecyclerAdapter(private var employees: List<Employee>) :
        RecyclerView.Adapter<EmployeeRecyclerAdapter.ViewHolder>() {
    inner class ViewHolder (itemView:View):RecyclerView.ViewHolder(itemView){
            var employeeName: TextView =itemView.findViewById(R.id.employeeName)
            var employeeSalary: TextView=itemView.findViewById(R.id.employee_salary)
            var employeeAge: TextView=itemView.findViewById(R.id.employee_age)
            var removeEmployee: ImageView=itemView.findViewById(R.id.removeWorkerImage)
            init {
                removeEmployee.setOnClickListener{
                    //TODO
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val v = LayoutInflater.from(parent.context).inflate(R.layout.employee_cell,parent,false)
        return ViewHolder(v)
    }


    override fun getItemCount(): Int {
        return employees.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.employeeAge.text=employees[position].employee_age
        holder.employeeName.text=employees[position].employee_name
        holder.employeeSalary.text=employees[position].employee_salary
        //holder.removeEmployee.setImageResource(R.drawable.err)
    }


}