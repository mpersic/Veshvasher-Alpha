package matejpersic_orwima_proj.ferit.veshwasher

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView

class UserBottomNavActivity : AppCompatActivity(),UserFragmentCommunicator {
    lateinit var  userUsingMachinesFragment: UserUsingMachinesFragment
    lateinit var userBrowseMachinesFragment: UserBrowseMachinesFragment
    lateinit var userLogOutFragment: UserLogOutFragment
    lateinit var userEmail:String
    lateinit var handler: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_bottomnav_activity)


        userEmail= intent.getStringExtra("Email").toString()
        //Toast.makeText(this,userEmail,Toast.LENGTH_LONG).show()
        handler= DatabaseHelper(this)
        //initializing default framgent
        userBrowseMachinesFragment= UserBrowseMachinesFragment(handler,userEmail)
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout,userBrowseMachinesFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        val bottomNavigation: BottomNavigationView =findViewById(R.id.btm_nav)
        bottomNavigation.setOnNavigationItemReselectedListener { item->
            when(item.itemId){
                R.id.usingMachines->{
                    userUsingMachinesFragment= UserUsingMachinesFragment(userEmail,handler)
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout,userUsingMachinesFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.availableMachines->{
                    userBrowseMachinesFragment= UserBrowseMachinesFragment(handler,userEmail)
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout, userBrowseMachinesFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.logout->{
                    userLogOutFragment= UserLogOutFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout,userLogOutFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
            }
        }
    }

    override fun onButtonClicked(Email: String) {
       // userUsingMachinesFragment= UserUsingMachinesFragment(userEmail,handler)
        //supportFragmentManager.beginTransaction().replace(R.id.frame_layout, userBrowseMachinesFragment)
            //    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
             //   .commit()
        var frg: Fragment = supportFragmentManager.findFragmentByTag(findViewById(R.id.MachinesUser))!!
        val ft = supportFragmentManager.beginTransaction()
        ft.detach(frg)
        ft.attach(frg)
        ft.commit()
       // machinesFragment=adapter.getMachineFragment(handler)
        //machinesFragment.displayMachine(machineName,machineProgramme)
    }
}