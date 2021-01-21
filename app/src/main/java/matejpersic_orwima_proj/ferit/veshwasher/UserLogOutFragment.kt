package matejpersic_orwima_proj.ferit.veshwasher

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class UserLogOutFragment : Fragment() {

    lateinit var button: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_log_out, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button=view.findViewById(R.id.btn_log_out_user)
        button.setOnClickListener {
            startUserActivity()
        }
    }
    private fun startUserActivity() {
        activity?.let {
            val intent= Intent(it,StartupActivity::class.java)
            startActivity(intent)
        }
    }
}