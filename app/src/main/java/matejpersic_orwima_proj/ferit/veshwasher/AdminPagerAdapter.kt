package matejpersic_orwima_proj.ferit.veshwasher

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class AdminPagerAdapter(fm: FragmentManager, var totalTabs: Int,var dbHelper:DatabaseHelper) :
FragmentPagerAdapter(fm) {
    private lateinit var addMachineFragment:AddMachineFragment
    override fun getCount(): Int {
        return totalTabs
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                WorkersFragment()
            }
            1 -> {
                AdminMachinesFragment(dbHelper)
            }
            2 ->{
                   AddMachineFragment(dbHelper)
            }
            3->{
                UserLogOutFragment()
            }
            else -> return WorkersFragment()
        }
    }
}