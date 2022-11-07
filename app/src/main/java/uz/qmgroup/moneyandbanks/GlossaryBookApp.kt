package uz.qmgroup.moneyandbanks

import android.app.Application
import uz.qmgroup.moneyandbanks.database.AppDatabase

class GlossaryBookApp: Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabase.initializeInstance(this)
    }
}