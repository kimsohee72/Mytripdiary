package com.sohee.mynewapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sohee.mynewapplication.model.Jenla1
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Jenla1::class], version = 1, exportSchema = false)
abstract class Jenla1Database : RoomDatabase() {

    abstract fun Jenla1Dao() : Jenla1Dao
    companion object{
        @Volatile
        private var instance : Jenla1Database? = null

        // 싱글톤으로 생성 (자주 생성 시 성능 손해). 이미 존재할 경우 생성하지 않고 바로 반환
        fun getDatabase(context : Context) : Jenla1Database? {
            if(instance == null){
                synchronized(Jenla1Database::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        Jenla1Database::class.java,
                        "jenla1_database"
                    ).addCallback(object : RoomDatabase.Callback(){
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            fillInDb(context.applicationContext)
                        }
                    }).build()
                }
            }
            return instance
        }
        fun fillInDb(context: Context){
            CoroutineScope(Dispatchers.IO).launch {
                Jenla1Database.getDatabase(context)!!.Jenla1Dao().addUserDb(
                    USER_DATA
                )
            }
        }

    }
}
private val USER_DATA = arrayListOf(
    Jenla1(0,"전라북도","사람이 많았다")
)