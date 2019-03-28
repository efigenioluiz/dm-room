package br.edu.ifpr.luiz.ruum.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_people")
data class Person(
    @ColumnInfo(name = "first_name")
    var firstName: String,
    @ColumnInfo(name = "last_name")
    var lastName: String,
    var title: String

){
    @PrimaryKey(autoGenerate = true)
    var id: Int= 0

    val montName get()=  " ${title} ${firstName} ${lastName}"
    override fun toString()= montName

}