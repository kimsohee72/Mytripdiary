package com.sohee.mynewapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sohee.mynewapplication.model.Gyeng2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Gyeng2::class], version = 1, exportSchema = false)
abstract class Gyeng2Database : RoomDatabase() {

    abstract fun Gyeng2Dao() : Gyeng2Dao
    companion object{
        @Volatile
        private var instance : Gyeng2Database? = null

        // 싱글톤으로 생성 (자주 생성 시 성능 손해). 이미 존재할 경우 생성하지 않고 바로 반환
        fun getDatabase(context : Context) : Gyeng2Database? {
            if(instance == null){
                synchronized(Gyeng2Database::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        Gyeng2Database::class.java,
                        "gyeng2_database"
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
                Gyeng2Database.getDatabase(context)!!.Gyeng2Dao().addUserDb(
                    USER_DATA
                )
            }
        }

    }
}
private val USER_DATA = arrayListOf(
    Gyeng2(0,"부산","베이글이 맛있었다")

)