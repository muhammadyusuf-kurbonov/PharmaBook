package uz.qmgroup.pharmabook

import android.app.Application
import uz.qmgroup.pharmabook.repos.database.AppDatabase

class PharmaBookApp: Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabase.initializeInstance(this)
    }
}