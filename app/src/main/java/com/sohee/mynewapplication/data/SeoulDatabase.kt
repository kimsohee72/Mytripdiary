package com.sohee.mynewapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sohee.mynewapplication.model.Seoul
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Seoul::class], version = 1, exportSchema = false)
abstract class SeoulDatabase : RoomDatabase() {

    abstract fun SeoulDao() : SeoulDao

    companion object{
        @Volatile
        private var instance : SeoulDatabase? = null

        // 싱글톤으로 생성 (자주 생성 시 성능 손해). 이미 존재할 경우 생성하지 않고 바로 반환
        fun getDatabase(context : Context) : SeoulDatabase? {
            if(instance == null){
                synchronized(SeoulDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SeoulDatabase::class.java,
                        "user_database"
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
                getDatabase(context)!!.SeoulDao().addUserDb(
                    USER_DATA
                )
            }
        }
    }
}
private val USER_DATA = arrayListOf(
    Seoul(0,"안국역","베이글이 맛있었다"),
    Seoul(0,"건대입구역","사람이 많았다")
)

