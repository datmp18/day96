package com.example.studentmanagement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentexamples.R

class SearchStudentFragment : Fragment() {

    private lateinit var etSearch: EditText
    private lateinit var btnSearch: Button
    private lateinit var rvResults: RecyclerView
    private lateinit var adapter: StudentAdapter
    private val allStudents = StudentRepository.getAllStudents() // Danh sách giả lập

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search_student, container, false)

        etSearch = view.findViewById(R.id.etSearch)
        btnSearch = view.findViewById(R.id.btnSearch)
        rvResults = view.findViewById(R.id.rvSearchResults)

        adapter = StudentAdapter(emptyList())
        rvResults.layoutManager = LinearLayoutManager(context)
        rvResults.adapter = adapter

        btnSearch.setOnClickListener {
            val keyword = etSearch.text.toString().trim()
            val results = allStudents.filter {
                it.name.contains(keyword, ignoreCase = true) ||
                        it.id.contains(keyword, ignoreCase = true)
            }
            adapter.updateData(results)
        }

        return view
    }

    // -----------------------------
    // Adapter nội bộ cho RecyclerView
    // -----------------------------
    inner class StudentAdapter(private var studentList: List<Student>) :
        RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

        inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val tvName: TextView = itemView.findViewById(R.id.tvStudentName)
            val tvId: TextView = itemView.findViewById(R.id.tvStudentId)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_student, parent, false)
            return StudentViewHolder(view)
        }

        override fun getItemCount(): Int = studentList.size

        override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
            val student = studentList[position]
            holder.tvName.text = student.name
            holder.tvId.text = student.id
        }

        fun updateData(newList: List<Student>) {
            studentList = newList
            notifyDataSetChanged()
        }
    }

    // -----------------------------
    // Model dữ liệu Sinh viên
    // -----------------------------
    data class Student(val id: String, val name: String)

    // -----------------------------
    // Repository giả lập dữ liệu sinh viên
    // -----------------------------
    object StudentRepository {
        fun getAllStudents(): List<Student> {
            return listOf(
                Student("S001", "Nguyễn Văn A"),
                Student("S002", "Trần Thị B"),
                Student("S003", "Lê Văn C"),
                Student("S004", "Phạm Thị D"),
                Student("S005", "Lý Văn E")
            )
        }
    }
}
