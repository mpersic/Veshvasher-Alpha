package matejpersic_orwima_proj.ferit.veshwasher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import java.io.Serializable

class AdminMainActivity : AppCompatActivity(),FragmentCommunicator {
    var tabLayout: TabLayout?=null
    lateinit var viewPager: ViewPager
    lateinit var adapter:AdminPagerAdapter
    lateinit var userEmail:String
    lateinit var userPassword:String
    lateinit var handler: DatabaseHelper
    var machinesFragment:AdminMachinesFragment?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_main)
        var bundle=intent.extras
        handler= DatabaseHelper(this)
        if (bundle != null) {
            userEmail= bundle.getString("Email").toString()
            userPassword=bundle.getString("Password").toString()
            if(!handler.userPresent(userEmail,userPassword)){
                handler.insertUserData(userEmail,userPassword)
            }
        }
        initializeViews()
        setUpPager(handler)
    }
    private fun setUpPager(handler:DatabaseHelper) {
        //!! is non null
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Machines"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Add machine"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Log out"))
        adapter=AdminPagerAdapter(supportFragmentManager, tabLayout!!.tabCount,handler)
        viewPager.adapter=adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem=tab!!.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun initializeViews() {
        tabLayout = findViewById(R.id.AdminTabLayout)
        viewPager = findViewById(R.id.AdminViewPager)
    }

    override fun onButtonClicked(machineName:String,machineProgramme:String) {
        //viewPager.currentItem =0
        machinesFragment=adapter.getMachineFragment(handler)
        machinesFragment!!.displayMachine(machineName,machineProgramme)
    }


    //,fragmentcommunicator
    /*override fun passNewMachine(machine: Machine) {
        val bundle=Bundle()
        bundle.putString("machineName",machine.name)
        bundle.putString("machineProgramme",machine.programme)
        val transaction=this.supportFragmentManager.beginTransaction()
        val fragmentAdminMachinesFragment=AdminMachinesFragment(handler)
        fragmentAdminMachinesFragment.arguments=bundle

        transaction.replace(R.id.AdminViewPager,fragmentAdminMachinesFragment)
        transaction.commit()
    }*/

}