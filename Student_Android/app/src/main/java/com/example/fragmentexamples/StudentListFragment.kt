package com.example.fragmentexamples

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast

class StudentListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var studentAdapter: StudentAdapter
    private val studentList = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_list, container, false)
        recyclerView = view.findViewById(R.id.rvStudents)
        recyclerView.layoutManager = LinearLayoutManager(context)
        studentAdapter = StudentAdapter(studentList) { studentName ->
            // Khi nhấn vào 1 sinh viên trong danh sách
            Toast.makeText(context, "Clicked: $studentName", Toast.LENGTH_SHORT).show()
            // Tại đây bạn có thể gọi đến Activity để chuyển màn hình hoặc hiển thị chi tiết
        }
        recyclerView.adapter = studentAdapter

        // Dummy data
        studentList.addAll(listOf("Nguyen Van A", "Tran Thi B", "Le Van C"))
        studentAdapter.notifyDataSetChanged()

        return view
    }

    // Hàm gọi từ Activity để cập nhật danh sách hoặc chuyển tiếp thông tin
    fun updateContent(newStudent: String) {
        studentList.add(newStudent)
        studentAdapter.notifyItemInserted(studentList.size - 1)
    }
}
