package uz.qmgroup.moneyandbanks.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.qmgroup.moneyandbanks.term.TermEntity
import uz.qmgroup.moneyandbanks.term.TermsDao

@Database(
    entities = [
        TermEntity::class
    ],
    version = 1,
    autoMigrations = []
)
abstract class AppDatabase: RoomDatabase(){

    companion object{
        var Instance: AppDatabase? = null
        private set

        fun initializeInstance(context: Context){
            if (Instance == null) {
                Instance = Room.databaseBuilder(context, AppDatabase::class.java, "main")
                    .build()
            }
        }
    }

    abstract fun medicineDao(): TermsDao
}