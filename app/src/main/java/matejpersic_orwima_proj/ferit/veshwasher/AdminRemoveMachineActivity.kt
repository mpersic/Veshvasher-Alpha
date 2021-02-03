package matejpersic_orwima_proj.ferit.veshwasher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.TextView

class AdminRemoveMachineActivity : AppCompatActivity() {
    lateinit var machineName:String
    lateinit var databaseHelper: DatabaseHelper
    lateinit var confirmText:TextView
    lateinit var yesBtn:Button
    lateinit var cancelBtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_remove_machine)
        machineName= intent.getStringExtra("machineName").toString()
        databaseHelper=DatabaseHelper(this)
        initializeViews()
        setUpListeners()
    }

    private fun setUpListeners() {
        yesBtn.setOnClickListener{
            databaseHelper.removeMachine(machineName)
            databaseHelper.removeIsUsingMachine(machineName)
            restartAdminActivity()
        }
        cancelBtn.setOnClickListener{
            restartAdminActivity()
        }
    }

    private fun restartAdminActivity() {
        startActivity(Intent(this,AdminMainActivity::class.java))
    }

    private fun initializeViews() {
        confirmText=findViewById(R.id.admin_confirm_remove)
        confirmText.text=machineName
        yesBtn=findViewById(R.id.yes_btn)
        cancelBtn=findViewById(R.id.cancel_btn)
    }
}