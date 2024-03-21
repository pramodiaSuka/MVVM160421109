package com.maverick.studentapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.maverick.studentapp.R
import com.maverick.studentapp.databinding.FragmentStudentDetailBinding
import com.maverick.studentapp.viewmodel.DetailViewModel

class StudentDetailFragment : Fragment() {
    private lateinit var viewModel:DetailViewModel
    private lateinit var binding:FragmentStudentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch()
        observeViewModel()
    }

    fun observeViewModel(){
        //Get observe
        viewModel.studentLD.observe(viewLifecycleOwner, Observer{
            binding.txtId.setText(it.id)
            binding.txtName.setText(it.name)
            binding.txtPhone.setText(it.phone)
            binding.txtBirthOfDate.setText(it.bod)
        })
    }
}