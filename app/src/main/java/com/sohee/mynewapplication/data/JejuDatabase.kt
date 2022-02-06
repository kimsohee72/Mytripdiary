package com.sohee.mynewapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sohee.mynewapplication.model.Jeju
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Jeju::class], version = 1, exportSchema = false)
abstract class JejuDatabase : RoomDatabase() {

    abstract fun jejuDao() : JejuDao

    companion object{
        @Volatile
        private var instance : JejuDatabase? = null

        // 싱글톤으로 생성 (자주 생성 시 성능 손해). 이미 존재할 경우 생성하지 않고 바로 반환
        fun getDatabase(context : Context) : JejuDatabase? {
            if(instance == null){
                synchronized(JejuDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        JejuDatabase::class.java,
                        "jeju_database"
                    ).addCallback(object : RoomDatabase.Callback(){
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                        }
                    }).build()
                }
            }
            return instance
        }

    }
}
