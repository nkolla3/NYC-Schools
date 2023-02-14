package com.nyc.schools.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nyc.schools.R
import com.nyc.schools.SchoolsListViewModel
import com.nyc.schools.model.School
import com.nyc.schools.databinding.SchoolsListFragmentBinding
import com.nyc.schools.ui.details.SchoolDetailsFragment

class SchoolsListFragment : Fragment() {
    private lateinit var schoolsListViewAdapter: SchoolsListAdapter
    private lateinit var binding: SchoolsListFragmentBinding
    private lateinit var viewModel: SchoolsListViewModel

    companion object {
        fun newInstance() = SchoolsListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = SchoolsListFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(SchoolsListViewModel::class.java)

        setUpRecyclerView()
        setUpSchoolListObserver()
        if (viewModel.schoolsList.value.isNullOrEmpty()) {
            viewModel.getSchools()
        }
    }

    private fun setUpRecyclerView() {
        binding.schoolsListRecyclerView.apply {
            schoolsListViewAdapter = SchoolsListAdapter(getCardOnClickListener())
            adapter = schoolsListViewAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun getCardOnClickListener(): SchoolsListAdapter.SchoolCardClickListener =
        object : SchoolsListAdapter.SchoolCardClickListener {
            override fun onClick(school: School) {
                activity!!.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, SchoolDetailsFragment.newInstance(school.dbn))
                    .addToBackStack(null)
                    .commit()
            }
        }

    private fun setUpSchoolListObserver() {
        viewModel.apply {
            schoolsList.observe(viewLifecycleOwner, Observer { schoolsList ->
                schoolsListViewAdapter.apply {
                    setSchoolsList(schoolsList)
                }
            })
        }
    }
}