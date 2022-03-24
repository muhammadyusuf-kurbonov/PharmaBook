package uz.qmgroup.pharmabook.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.qmgroup.pharmabook.medicines.MedicineDao
import uz.qmgroup.pharmabook.medicines.MedicineEntity

@Database(
    entities = [
        MedicineEntity::class
    ],
    version = 4,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3, spec = Migration2_3::class),
        AutoMigration(from = 3, to = 4),
    ]
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

    abstract fun medicineDao(): MedicineDao
}