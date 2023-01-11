package com.example.mytodoapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

class NoteAdapter: Adapter<NoteAdapter.NoteVH>() {

    private val noteList: ArrayList<Note> = arrayListOf()

    inner class NoteVH(itemView: View): RecyclerView.ViewHolder(itemView){
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvCategory = itemView.findViewById<TextView>(R.id.tvCategory)
        private val tvBody = itemView.findViewById<TextView>(R.id.tvBody)
        private val ibtnDelete = itemView.findViewById<ImageButton>(R.id.ibtnDelete)
        private val ibtnEdit = itemView.findViewById<ImageButton>(R.id.ibtnEdit)

        fun bind(note: Note){
            tvTitle.text = note.title
            tvCategory.text = note.category
            tvBody.text = note.body
            ibtnDelete.setOnClickListener {
                removeNote(adapterPosition)
            }
            ibtnEdit.setOnClickListener {
                Toast.makeText(it.context, "You clicked Edit Button", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteVH {
        return NoteVH(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false))
    }

    override fun onBindViewHolder(holder: NoteVH, position: Int) {
        holder.bind(noteList[position])
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    fun addNote(note: Note){
        noteList.add(note)
//        notifyDataSetChanged()
        notifyItemInserted(noteList.size - 1)
    }

    private fun removeNote(position: Int){
        noteList.removeAt(position)
        notifyItemRemoved(position)
    }



}
