package com.sohee.mynewapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sohee.mynewapplication.model.Gang
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Gang::class], version = 1, exportSchema = false)
abstract class GangDatabase : RoomDatabase() {

    abstract fun gangDao() : GangDao
    companion object{
        @Volatile
        private var instance : GangDatabase? = null

        // 싱글톤으로 생성 (자주 생성 시 성능 손해). 이미 존재할 경우 생성하지 않고 바로 반환
        fun getDatabase(context : Context) : GangDatabase? {
            if(instance == null){
                synchronized(GangDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        GangDatabase::class.java,
                        "gang_database"
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
                GangDatabase.getDatabase(context)!!.gangDao().addUserDb(
                    USER_DATA
                )
            }
        }

    }
}
private val USER_DATA = arrayListOf(
    Gang(0,"속초","순두부"),
    Gang(0,"강릉","사람이 많았다")
)