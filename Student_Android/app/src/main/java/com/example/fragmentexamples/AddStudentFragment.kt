package com.example.studentmanagement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fragmentexamples.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class AddStudentFragment : Fragment() {

    private lateinit var etStudentName: TextInputEditText
    private lateinit var etStudentId: TextInputEditText
    private lateinit var btnAddStudent: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_student, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views
        etStudentName = view.findViewById(R.id.etStudentName)
        etStudentId = view.findViewById(R.id.etStudentId)
        btnAddStudent = view.findViewById(R.id.btnAddStudent)

        btnAddStudent.setOnClickListener {
            addStudent()
        }
    }

    private fun addStudent() {
        val name = etStudentName.text.toString().trim()
        val id = etStudentId.text.toString().trim()

        if (name.isEmpty() || id.isEmpty()) {
            showErrorMessage("Vui lòng điền đầy đủ thông tin")
            return
        }

        // Here you would typically add the student to your database or ViewModel
        val student = Student(name, id)

        // Show success message
        showSuccessMessage("Đã thêm sinh viên: ${student.name} (${student.id})")

        // Clear fields
        etStudentName.text?.clear()
        etStudentId.text?.clear()
    }

    private fun showErrorMessage(message: String) {
        view?.let {
            Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun showSuccessMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    data class Student(val name: String, val id: String)
}