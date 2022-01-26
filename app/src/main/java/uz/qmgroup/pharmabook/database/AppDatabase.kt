package uz.qmgroup.pharmabook.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import uz.qmgroup.pharmabook.medicines.MedicineDao
import uz.qmgroup.pharmabook.medicines.MedicineEntity
import uz.qmgroup.pharmabook.tags.TagDao
import uz.qmgroup.pharmabook.tags.TagEntity

@Database(
    entities = [
        MedicineEntity::class,
        TagEntity::class,
        MedicineTagCrossRef::class
    ],
    version = 2,
    autoMigrations = [
        AutoMigration(from =1, to = 2),
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
    abstract fun tagDao(): TagDao
    abstract fun medicineTagCrossRefDao(): MedicineTagCrossRefDao
}