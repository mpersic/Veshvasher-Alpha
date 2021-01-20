package matejpersic_orwima_proj.ferit.veshwasher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import java.io.Serializable

class AdminMainActivity : AppCompatActivity(),Serializable {
    var tabLayout: TabLayout?=null
    var viewPager: ViewPager?=null
    var pagerAdapter: AdminPagerAdapter?=null
    lateinit var handler: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_main)
        handler=intent.getSerializableExtra("dbHelper") as DatabaseHelper
        //handler.insertUserData("admin","password")
        initializeViews()
        setUpPager()
    }
    private fun setUpPager() {
        //!! is non null
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Employees"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Machines"))
        val adapter=AdminPagerAdapter(supportFragmentManager, tabLayout!!.tabCount,handler)
        viewPager!!.adapter=adapter
        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager!!.currentItem=tab!!.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun initializeViews() {
        tabLayout = findViewById(R.id.AdminTabLayout)
        viewPager = findViewById(R.id.AdminViewPager)
    }
}