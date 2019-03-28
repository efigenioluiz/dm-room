package br.edu.ifpr.luiz.ruum.entities.db

import androidx.room.Database
import androidx.room.RoomDatabase
import br.edu.ifpr.luiz.ruum.entities.Person
import br.edu.ifpr.luiz.ruum.entities.db.DAO.PersonDAO

@Database(entities = arrayOf(Person::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDAO(): PersonDAO
}