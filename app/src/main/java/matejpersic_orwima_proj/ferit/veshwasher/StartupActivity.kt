package matejpersic_orwima_proj.ferit.veshwasher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class StartupActivity : AppCompatActivity() {
    var tabLayout: TabLayout?=null
    var viewPager: ViewPager?=null
    var pagerAdapter: LoginRegisterScreenSlidePagerAdapter?=null

    var registerEmail: EditText?=null
    var registerPassword: EditText?=null
    var registerConfirmPassword: EditText?=null
    var registerButton: Button?=null

    var loginEmailEt:EditText?=null
    var loginPasswordEt:EditText?=null
    var loginButton: Button?=null

    lateinit var handler: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handler= DatabaseHelper(this)
        //handler.insertUserData("admin","password")
        initializeViews()
        setUpPager(handler)
    }


    private fun setUpPager(helper: DatabaseHelper) {
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Login"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Register"))
        val adapter=LoginRegisterScreenSlidePagerAdapter(supportFragmentManager, tabLayout!!.tabCount,helper)
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
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
    }
}