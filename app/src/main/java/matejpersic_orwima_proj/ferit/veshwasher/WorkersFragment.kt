package matejpersic_orwima_proj.ferit.veshwasher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable


class WorkersFragment : Fragment() {

    private lateinit var apicall: Call<List<Employee>>
    private var employees: ArrayList<Employee> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_workers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView=view.findViewById(R.id.workersRecyclerView)
        recyclerView.layoutManager=LinearLayoutManager(context)
        addEmployees()
        //setUpApiCall(apicall)
        recyclerView.adapter=EmployeeRecyclerAdapter(employees)
    }

    /*private fun setUpApiCall(apiCall:Call<List<Employee>>) {
        apiCall = NetworkUtils.getApiInterface().getEmployees()
        apiCall.enqueue(object:Callback<List<Employee>>() {
            @Override
            override fun onResponse(call:Call<List<Employee>>, response: Response<List<Employee>>) {
                if (response.isSuccessful() && response.body() != null) {
                    showEmployees(response.body())
                }
            }
            @Override
            override fun onFailure(call:Call<List<Employee>>, t:Throwable) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        })
    }*/

    private fun addEmployee(){

    }
    private fun addEmployees() {
        employees.add(Employee(1,"joka","44","21320"))
        employees.add(Employee(2,"joka","44","21320"))
    }
}