package matejpersic_orwima_proj.ferit.veshwasher

import android.content.ContentValues
import android.content.Context
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

    fun insertMachine(name:String, programmes:String, available:String){
        val db: SQLiteDatabase=writableDatabase
        val values: ContentValues= ContentValues()
        values.put("available",available)
        values.put("name",name)
        values.put("programmes",programmes)
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
}