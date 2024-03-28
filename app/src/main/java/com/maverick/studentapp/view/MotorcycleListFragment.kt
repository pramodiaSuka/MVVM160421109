package com.maverick.studentapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.maverick.studentapp.R
import com.maverick.studentapp.databinding.FragmentMotorcycleListBinding
import com.maverick.studentapp.viewmodel.ListViewModel
import com.maverick.studentapp.viewmodel.MotorcycleListViewModel

class MotorcycleListFragment : Fragment() {
    private  lateinit var viewModel: MotorcycleListViewModel
    private val motorcycleListAdapter = MotorcycleListAdapter(arrayListOf())
    private lateinit var binding:FragmentMotorcycleListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMotorcycleListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MotorcycleListViewModel::class.java)
        viewModel.refresh()

        binding.recViewMotorcycle.layoutManager = LinearLayoutManager(context)
        binding.recViewMotorcycle.adapter = motorcycleListAdapter

        observeViewModelMotorcycle()



//        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
//        viewModel.refresh()
//
//        binding.recView.layoutManager = LinearLayoutManager(context)
//        binding.recView.adapter = studentListAdapter
//
//        observeViewModel()
//
//        binding.refreshLayout.setOnRefreshListener {
//            binding.recView.visibility = View.GONE
//            binding.txtError.visibility = View.GONE
//            binding.progressLoad.visibility = View.VISIBLE
//            viewModel.refresh()
//            binding.refreshLayout.isRefreshing = false
//        }
    }

    fun observeViewModelMotorcycle(){
        viewModel.motorcyclesLD.observe(viewLifecycleOwner, Observer {
            motorcycleListAdapter.updateMotorcycleList(it)
        })

        viewModel.motorcycleLoadErrorLD.observe(viewLifecycleOwner, Observer {
//            if (it == true) {
//                binding.txtError?.visibility = View.VISIBLE
//            } else {
//                binding.txtError?.visibility = View.GONE
//            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {

        })
//        viewModel.studentsLD.observe(viewLifecycleOwner, Observer {
//            studentListAdapter.updateStudentList(it)
//        })
//
//        viewModel.studentLoadErrorLD.observe(viewLifecycleOwner, Observer {
//            if (it == true) {
//                binding.txtError?.visibility = View.VISIBLE
//            } else {
//                binding.txtError?.visibility = View.GONE
//            }
//        })
//
//        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
//            if (it == true) {
//                binding.recView.visibility = View.GONE
//                binding.progressLoad.visibility = View.VISIBLE
//            } else {
//                binding.recView.visibility = View.VISIBLE
//                binding.progressLoad.visibility = View.GONE
//            }
//        })
    }


}