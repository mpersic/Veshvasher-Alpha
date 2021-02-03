package matejpersic_orwima_proj.ferit.veshwasher

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.Serializable

class DatabaseHelper(context: Context):SQLiteOpenHelper(context,dbname,factory, version),Serializable{
    companion object{
        internal val dbname="database"
        internal val factory=null
        internal val version=1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE users(email VARCHAR(30) PRIMARY KEY,password VARCHAR(10))")
        db?.execSQL("CREATE TABLE machines(name VARCHAR(30) PRIMARY KEY, programmes VARCHAR(10), available CHAR(1))")
        db?.execSQL("CREATE TABLE isUsing(machineID REFERENCES machines(id),userID REFERENCES users(email), PRIMARY KEY (machineID,userID))")
        db?.execSQL("INSERT INTO machines VALUES('Gorenje 1','Premium','1')")
        db?.execSQL("INSERT INTO machines VALUES('Gorenje 2','Premium','1')")
        db?.execSQL("INSERT INTO machines VALUES('Gorenje 3','Standard','1')")
        db?.execSQL("INSERT INTO users VALUES('admin','password')")
    }

    fun userPresent(email:String,password: String):Boolean{
        val db=writableDatabase
        val query="SELECT * FROM users WHERE email='$email' AND password='$password'"
        val cursor=db.rawQuery(query,null)
        if(cursor.count<=0){
            cursor.close()
            return false
        }
        cursor.close()
        return true
    }

    fun adminIsPresent(email:String,password: String):Boolean{
        if( email == "admin" && password == "password" )
        {
            val db=writableDatabase
            val query="SELECT * FROM users WHERE email='$email' AND password='$password'"
            val cursor=db.rawQuery(query,null)
            if(cursor.count<=0){
                cursor.close()
                return false
            }
            cursor.close()
            return true
        }
        return false
    }

    fun insertAdmin(email:String,password: String){
        if(!adminIsPresent(email,password)){
            val db: SQLiteDatabase=writableDatabase
            val values: ContentValues= ContentValues()
            values.put("email",email)
            values.put("password",password)
            db.insert("users",null,values)
            db.close()
        }
    }

    fun insertMachine(name:String, programmes:String, available:String){
        val db: SQLiteDatabase=writableDatabase
        val values: ContentValues= ContentValues()
        values.put("name",name)
        values.put("programmes",programmes)
        values.put("available",available)
        db.insert("machines",null,values)
        db.close()
    }

    fun insertUserData(email:String,password:String){
        val db: SQLiteDatabase=writableDatabase
        val values: ContentValues= ContentValues()
        values.put("email",email)
        values.put("password",password)
        db.insert("users",null,values)
        db.close()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun initialValues() {
        insertMachine("Gorenje 1","1, 2","1")
        insertMachine("Gorenje 2","1","0")
        insertMachine("Gorenje 3","2","1")
        insertMachine("Gorenje 4","1,2","1")
        insertUserData("plenart@gmail.com","pero123")
        //insertIsUsing("Gorenje 2","plenart@gmail.com")
    }

     fun insertIsUsing(machineName:String, user:String) {
        val db: SQLiteDatabase=writableDatabase
        val values: ContentValues= ContentValues()
        values.put("machineID",machineName)
        values.put("userID",user)
        db.insert("isUsing",null,values)
        db.close()
        updateMachine(machineName)
    }

    private fun updateMachine(name: String) {
        val available="0"
        val db=writableDatabase
        val query="UPDATE machines SET available='$available' WHERE name='$name' "
        db.execSQL(query)
    }

    fun readAllMachines(): Cursor {
        val query:String="SELECT * FROM machines"
         val db:SQLiteDatabase=readableDatabase
         var cursor:Cursor?=null
         cursor=db.rawQuery(query,null)
         return cursor
    }

    fun readAllMachinesUserIsUsing(userID:String): Cursor {
        val query:String="SELECT * FROM isUsing WHERE userID='$userID'"
        val db:SQLiteDatabase=readableDatabase
        var cursor:Cursor?=null
        cursor=db.rawQuery(query,null)
        return cursor
    }

    fun readAllAvailableMachines(): Cursor {
        val available ="1"
        val query ="SELECT * FROM machines WHERE available=$available"
        val db:SQLiteDatabase=readableDatabase
        var cursor:Cursor?=null
        cursor=db.rawQuery(query,null)
        return cursor
    }

    fun readAllMachinesFromUser(userID:String):Cursor{
        val query ="SELECT name, programmes FROM isUsing,machines WHERE machineID=name AND userID='$userID'"
        val db:SQLiteDatabase=readableDatabase
        var cursor:Cursor?=null
        cursor=db.rawQuery(query,null)
        return cursor
    }

    fun removeMachine(machineName: String) {
        val query="DELETE FROM machines WHERE name='$machineName'"
        val db:SQLiteDatabase=writableDatabase
        db.execSQL(query)
        db.close()
    }

    fun insertIsNotUsing(name: String, userEmail: Any) {
        val query="DELETE FROM isUsing WHERE machineID='$name' AND userID='$userEmail'"
        val db:SQLiteDatabase=writableDatabase
        db.execSQL(query)
        machineIsAvailable(name)
    }

    fun removeIsUsingMachine(machineName: String) {
        val query="DELETE FROM isUsing WHERE machineID='$machineName'"
        val db:SQLiteDatabase=writableDatabase
        db.execSQL(query)
        db.close()
        machineIsAvailable(machineName)
    }
    fun machineIsAvailable(machineName: String){
        val db:SQLiteDatabase=writableDatabase
        val available="1"
        val query1="UPDATE machines SET available='$available' WHERE name='$machineName' "
        db.execSQL(query1)
        db.close()
    }
}