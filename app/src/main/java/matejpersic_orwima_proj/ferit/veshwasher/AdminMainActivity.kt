package matejpersic_orwima_proj.ferit.veshwasher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class AdminMainActivity : AppCompatActivity(),AdminFragmentCommunicator {
    var tabLayout: TabLayout?=null
    lateinit var viewPager: ViewPager
    lateinit var adapter:AdminPagerAdapter
    lateinit var handler: DatabaseHelper
    lateinit var machinesFragment:AdminMachinesFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_main)
        handler= DatabaseHelper(this)
        initializeViews()
        //handler.initialValues()
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
        viewPager.currentItem =0
        machinesFragment=adapter.getMachineFragment(handler)
        machinesFragment.displayMachine(machineName,machineProgramme)
    }


}