package matejpersic_orwima_proj.ferit.veshwasher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView

class UserBottomNavActivity : AppCompatActivity() {
    lateinit var  userUsingMachinesFragment: UserUsingMachinesFragment
    lateinit var userBrowseMachinesFragment: UserBrowseMachinesFragment
    lateinit var userLogOutFragment: UserLogOutFragment


    lateinit var userEmail:String
    lateinit var userPassword:String
    lateinit var handler: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_bottomnav_activity)

        var bundle=intent.extras
        handler= DatabaseHelper(this)
       /* if (bundle != null) {
            userEmail= bundle.getString("Email").toString()
            userPassword=bundle.getString("Password").toString()
            if(!handler.userPresent(userEmail,userPassword)){
                handler.insertUserData(userEmail,userPassword)
            }
        }*/

        //initializing default framgent
        userBrowseMachinesFragment= UserBrowseMachinesFragment(handler)
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout,userBrowseMachinesFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        val bottomNavigation: BottomNavigationView =findViewById(R.id.btm_nav)
        bottomNavigation.setOnNavigationItemReselectedListener { item->
            when(item.itemId){
                R.id.usingMachines->{
                    userUsingMachinesFragment= UserUsingMachinesFragment("POZZZZ")
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout,userUsingMachinesFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.availableMachines->{
                    userBrowseMachinesFragment= UserBrowseMachinesFragment(handler)
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
            true
        }
    }
}