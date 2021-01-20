package matejpersic_orwima_proj.ferit.veshwasher

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class LoginRegisterScreenSlidePagerAdapter(fm: FragmentManager, var totalTabs: Int, var dbHelper:DatabaseHelper) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                LoginFragment(dbHelper)
            }
            1 -> {
                RegistrationFragment(dbHelper)
            }
            else -> return getItem(position)
        }
    }
    override fun getCount(): Int {
        return totalTabs
    }
}
