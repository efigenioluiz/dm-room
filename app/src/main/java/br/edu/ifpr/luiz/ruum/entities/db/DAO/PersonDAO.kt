package br.edu.ifpr.luiz.ruum.entities.db.DAO

import androidx.room.*
import br.edu.ifpr.luiz.ruum.entities.Person

@Dao
interface PersonDAO  {

    @Query("SELECT *FROM  tb_people")
    fun getAll(): List<Person>

    @Insert
    fun insert(person:Person)

    @Update
    fun update(person: Person)

    @Delete
    fun remove(person:Person)

    @Query("SELECT * FROM tb_people where id = :id LIMIT 1")
    fun findById(id: Int): Person?
}
