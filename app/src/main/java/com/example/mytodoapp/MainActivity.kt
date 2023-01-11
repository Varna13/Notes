package com.example.mytodoapp

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.core.view.children
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.Date

class MainActivity : AppCompatActivity() {

    private var noteId: Int = 0
    private val noteCategory: List<String> = listOf("friends", "students", "work", "personal", "Transaction")
    private val noteAdapter:NoteAdapter = NoteAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setUpNoteCategoryChips()

        noteAdapter.addNote(Note(1,"student","University", "MCA", Date(), Date()))
//        val etTitle = findViewById<EditText>(R.id.etTitle)
        val rvNotes = findViewById<RecyclerView>(R.id.rvTodo)
        findViewById<FloatingActionButton>(R.id.fabAddNote).setOnClickListener {
            showAddNoteDialog()
        }
//        val etBody = findViewById<EditText>(R.id.etBody)
//        val btnAddNote = findViewById<Button>(R.id.btnAddBody)
//        btnAddNote.setOnClickListener {
//            Log.d("var13", "onCreate: ${cgNoteCategory
//                .findViewById<Chip>(cgNoteCategory.checkedChipId).text}")
//            val noteTitle = etTitle.text.toString()
//            val noteBody = etBody.text.toString()
//            val category = cgNoteCategory.findViewById<Chip>(cgNoteCategory.checkedChipId).text
//            if (noteTitle.isBlank() || noteBody.isBlank()) return@setOnClickListener
//            noteAdapter.addNote(Note(
//                id = ++noteId,
//                body = noteBody,
//                title = noteTitle,
//                category = category.toString(), created = Date(), updated = Date()))
//
//            //clearing a textview after adding the note
//            etTitle.text.clear()
//            etBody.setText("")
//        }

//        cgNoteCategory.setOnCheckedStateChangeListener { group, checkedIds ->
//            if(checkedIds.size == 0) return@setOnCheckedStateChangeListener
//            Log.d("var13", "onCreate: $checkedIds,${(cgNoteCategory.findViewById<Chip>(checkedIds.first()) as Chip).text}")
//        }
        rvNotes.adapter = noteAdapter
        rvNotes.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvNotes.addItemDecoration(VerticalSpacerItemDecorator(24))

//        etTitle.setOnEditorActionListener { textView, i, keyEvent ->
//           if (i == EditorInfo.IME_ACTION_DONE){
//               noteAdapter.addNote(Note(
//                   id = ++noteId,
//                   body = etBody.text.toString(),
//                   title = etTitle.text.toString(),
//               category = "", created = Date(), updated = Date()))
//           }
//            return@setOnEditorActionListener true
//        }
    }
    // adding category chip to chip group programmatically
    private fun setUpNoteCategoryChips(cgNoteCategory: ChipGroup){
        for (categoryName in noteCategory){
            val chip = Chip(this)
            chip.text = categoryName
            chip.isCheckable = true
            cgNoteCategory.addView(chip)
        }
    }

    private fun showAddNoteDialog(){
        val view = LayoutInflater.from(this).inflate(R.layout.view_add_note, null, false)
        val chipGroupCategory = view.findViewById<ChipGroup>(R.id.cgNoteCategory)
        setUpNoteCategoryChips(chipGroupCategory)
        val etTitle = view.findViewById<EditText>(R.id.etTitle)
        val etBody = view.findViewById<EditText>(R.id.etBody)
        val btnAddNote = view.findViewById<Button>(R.id.btnAddBody)
//        chipGroupCategory.setOnCheckedStateChangeListener { group, checkedIds ->
//
//        }
        val addNoteDialog = AlertDialog.Builder(this)
            .setView(view)
            .create()

        btnAddNote.setOnClickListener {
            val category = chipGroupCategory.children.filter {
                (it as Chip).isChecked
            }.firstOrNull()?.let {
               ( it as Chip).text.toString()
            } ?: noteCategory.first()

            val title = etTitle.text.toString()
            val body = etBody.text.toString()
            if (title.isEmpty() && body.isEmpty()) return@setOnClickListener
            val note: Note = Note(id = ++noteId, title = title, body = body, category = category, Date(), Date())
            noteAdapter.addNote(note)
            addNoteDialog.dismiss()


        }
        addNoteDialog.show()


    }


}
