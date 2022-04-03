package uz.qmgroup.pharmabook.repos.database

import androidx.room.DeleteColumn
import androidx.room.DeleteTable
import androidx.room.migration.AutoMigrationSpec

@DeleteColumn(
    tableName = "MedicineEntity",
    columnName = "refId"
)
@DeleteTable(tableName = "TagEntity")
@DeleteTable(tableName = "MedicineTagCrossRef")
class Migration2_3: AutoMigrationSpec
