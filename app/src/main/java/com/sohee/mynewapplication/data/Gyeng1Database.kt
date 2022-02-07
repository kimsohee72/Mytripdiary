package com.sohee.mynewapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sohee.mynewapplication.model.Gyeng1
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Gyeng1::class], version = 1, exportSchema = false)
abstract class Gyeng1Database : RoomDatabase() {

    abstract fun Gyeng1Dao() : Gyeng1Dao
    companion object{
        @Volatile
        private var instance : Gyeng1Database? = null

        // 싱글톤으로 생성 (자주 생성 시 성능 손해). 이미 존재할 경우 생성하지 않고 바로 반환
        fun getDatabase(context : Context) : Gyeng1Database? {
            if(instance == null){
                synchronized(Gyeng1Database::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        Gyeng1Database::class.java,
                        "gyeng1_database"
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
                Gyeng1Database.getDatabase(context)!!.Gyeng1Dao().addUserDb(
                    USER_DATA
                )
            }
        }

    }
}
private val USER_DATA = arrayListOf(
    Gyeng1(0,"대구","베이글이 맛있었다"),
)