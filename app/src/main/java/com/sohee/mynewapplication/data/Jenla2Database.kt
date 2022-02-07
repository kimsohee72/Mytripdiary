package com.sohee.mynewapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sohee.mynewapplication.model.Jenla2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Jenla2::class], version = 1, exportSchema = false)
abstract class Jenla2Database : RoomDatabase() {

    abstract fun Jenla2Dao() : Jenla2Dao
    companion object{
        @Volatile
        private var instance : Jenla2Database? = null

        // 싱글톤으로 생성 (자주 생성 시 성능 손해). 이미 존재할 경우 생성하지 않고 바로 반환
        fun getDatabase(context : Context) : Jenla2Database? {
            if(instance == null){
                synchronized(Jenla2Database::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        Jenla2Database::class.java,
                        "jenla2_database"
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
                getDatabase(context)!!.Jenla2Dao().addUserDb(
                    USER_DATA
                )
            }
        }

    }
}
private val USER_DATA = arrayListOf(
    Jenla2(0,"전주","비빔밥 맛있었다")
)