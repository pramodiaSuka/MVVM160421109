package com.maverick.studentapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.maverick.studentapp.R
import com.maverick.studentapp.databinding.FragmentStudentDetailBinding
import com.maverick.studentapp.model.Student
import com.maverick.studentapp.viewmodel.DetailViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment(), ButtonUpdateClickListener, ButtonCreateNotifListener {
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
        binding.listener = this
        binding.notifListener = this
        binding.student = Student("", "", "", "", "https://randomuser.me/api/portraits/women/12.jpg")

        var studentId = ""

        if (arguments != null){
            studentId = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentId
        }

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(studentId)
        observeViewModel()
    }

    fun observeViewModel(){
        //Get observe
        viewModel.studentLD.observe(viewLifecycleOwner, Observer{
            var student = it
//            binding.btnUpdate?.setOnClickListener {
//                Observable.timer(5, TimeUnit.SECONDS)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe {
//                        Log.d("Messages", "five seconds")
//                        MainActivity.showNotification(student.name.toString(), "A new notification created", R.drawable.baseline_person_24)
//                    }
//            }
            binding.student = student
//            binding.txtId.setText(it.id)
//            binding.txtName.setText(it.name)
//            binding.txtPhone.setText(it.phone)
//            binding.txtBirthOfDate.setText(it.bod)
//            val picasso = Picasso.Builder(requireContext())
//            picasso.listener { picasso, uri, exception ->
//                exception.printStackTrace()
//            }
//            picasso.build().load(it.photoUrl).into(binding.imageView2, object:
//                Callback {
//                override fun onSuccess() {
//                    binding.imageView2.visibility = View.VISIBLE
//                }
//
//                override fun onError(e: Exception?) {
//                    Log.e("picasso_error", e.toString())
//                }
//            })
        })
    }

    override fun onButtonUpdateClick(v: View) {
        Toast.makeText(requireContext(), "This is update on student ID "+v.tag.toString() , Toast.LENGTH_LONG).show()

    }

    override fun onButtonCreateNotif(v: View) {
        Observable.timer(5, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("Messages", "five seconds")
                MainActivity.showNotification(v.tag.toString(), "A new notification created", R.drawable.baseline_person_24)
            }
    }
}