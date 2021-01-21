package matejpersic_orwima_proj.ferit.veshwasher

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class AdminPagerAdapter(fm: FragmentManager, var totalTabs: Int,var dbHelper:DatabaseHelper) :
FragmentPagerAdapter(fm) {
    private var addMachineFragment:AdminMachinesFragment?=null
    override fun getCount(): Int {
        return totalTabs
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                if(addMachineFragment==null)
                addMachineFragment= AdminMachinesFragment(dbHelper)
                return addMachineFragment as AdminMachinesFragment
            }
            1 ->{
                   AddMachineFragment(dbHelper)
            }
            2->{
                UserLogOutFragment()
            }
            else -> return AdminMachinesFragment(dbHelper)
        }
    }
    fun getMachineFragment(dbHelper: DatabaseHelper):AdminMachinesFragment{
       return addMachineFragment!!
    }
}