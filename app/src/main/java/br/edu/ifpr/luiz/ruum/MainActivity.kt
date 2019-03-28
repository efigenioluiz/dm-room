package br.edu.ifpr.luiz.ruum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.room.Room
import br.edu.ifpr.luiz.ruum.entities.Person
import br.edu.ifpr.luiz.ruum.entities.db.AppDatabase
import br.edu.ifpr.luiz.ruum.entities.db.DAO.PersonDAO
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var personDAO: PersonDAO
    lateinit var adapter:ArrayAdapter<Person>
    var personEditing: Person?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db= Room.databaseBuilder(applicationContext,AppDatabase::class.java,"bd.db").allowMainThreadQueries().build()

        personDAO= db.personDAO()

        btSave.setOnClickListener {savePerson() }
        listPeople.setOnItemClickListener { _, _, position, _->
            val person= adapter.getItem(position) as Person
            editPerson(person)
        }

        listPeople.setOnItemLongClickListener { _, _, position, _ ->
            val person= adapter.getItem(position) as Person
            personDAO.remove(person)
            loadData()
            true
        }

        loadData()
    }

    private fun editPerson(person: Person) {
        txtFirstName.setText(person.firstName)
        txtLastName.setText(person.lastName)
        txtTitle.setText(person.title)

        txtFirstName.requestFocus()
        personEditing=person
    }

    private fun savePerson() {
        val firstName=txtFirstName.text.toString()
        val lastName=txtLastName.text.toString()
        val title=txtTitle.text.toString()

        if(personEditing != null){

            personEditing?. let { person->
                person.firstName=firstName
                person.lastName=lastName
                person.title=title

                personDAO.update(person)
            }

        }else{
            val person= Person(firstName,lastName,title)
            personDAO.insert(person)
        }

        loadData()
    }

    private fun loadData(){
        val people= personDAO.getAll()
        adapter= ArrayAdapter(this,android.R.layout.simple_list_item_1,people)
        listPeople.adapter=adapter
        clean()

    }
    private fun clean(){
        txtFirstName.setText("")
        txtLastName.setText("")
        txtTitle.setText("")
        txtFirstName.requestFocus()
        personEditing=null
    }
}
