package matejpersic_orwima_proj.ferit.veshwasher

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class UserUsingMachinesActivity : AppCompatActivity() {
    lateinit var helper:DatabaseHelper
    private var machines: ArrayList<Machine> = arrayListOf()
    lateinit var recyclerAdapter:UserUsingMachineRecyclerAdapter
    lateinit var doneBtn: Button
    lateinit var email:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_using_machines)
        email= intent.getStringExtra("Email").toString()
        helper= DatabaseHelper(this)
        val recyclerView: RecyclerView =findViewById(R.id.userUsingMachinesRecyclerView)
        doneBtn=findViewById(R.id.userUsingMachinesButton)
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerAdapter= UserUsingMachineRecyclerAdapter(machines,helper,email)
        //addMachines()
        recyclerView.adapter=recyclerAdapter
        //helper.initialValues()
        //helper.clearAll()
        displayData()
        setUpListeners()
    }

    private fun setUpListeners() {
        doneBtn.setOnClickListener {
            startActivity(Intent(this,UserBottomNavActivity::class.java).putExtra("Email",email))
        }
    }

    private fun displayData(){
        val cursor: Cursor =helper.readAllMachinesFromUser(email)
        if(cursor.count==0){
            Toast.makeText(this,"no data", Toast.LENGTH_SHORT).show()
        }
        else{
            while(cursor.moveToNext()){
                machines.add(Machine(cursor.getString(0),cursor.getString(1)))
            }
        }
    }
}