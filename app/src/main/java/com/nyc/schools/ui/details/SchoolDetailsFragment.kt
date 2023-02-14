package com.nyc.schools.ui.details

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.color
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nyc.schools.R
import com.nyc.schools.SchoolsListViewModel
import com.nyc.schools.model.SchoolDetail
import com.nyc.schools.databinding.SchoolDetailsFragmentBinding


class SchoolDetailsFragment : Fragment() {
    private var dbn: String = ""
    private lateinit var binding: SchoolDetailsFragmentBinding
    private lateinit var viewModel: SchoolsListViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = SchoolDetailsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(SchoolsListViewModel::class.java)
        dbn = arguments?.getString(DBN) ?: ""
        setSchoolDetailsListObserver()
        if (!viewModel.schoolsDetailsList.value.isNullOrEmpty()) {
            updateViews(viewModel.schoolsDetailsList.value!!)
        } else
            viewModel.getSchoolDetails()
    }

    private fun setSchoolDetailsListObserver() {
        viewModel.apply {
            schoolsDetailsList.observe(viewLifecycleOwner, Observer { schoolDetailsList ->
                updateViews(schoolDetailsList)
            })
        }
    }

    private fun updateViews(schoolDetailList: List<SchoolDetail>) {
        val schoolItem = schoolDetailList.firstOrNull() { dbn == it.dbn }
        schoolItem?.let {
            binding.apply {
                errorMessage.visibility = View.GONE
                schoolName.text = schoolItem.school_name
                noOfSATTestTakers.text =
                    getSpanableString(R.string.number_ofTest_takers, schoolItem.num_of_sat_test_takers)
                readingScore.text =
                    getSpanableString(R.string.reading_score, schoolItem.sat_critical_reading_avg_score)
                mathScore.text =
                    getSpanableString(R.string.math_score, schoolItem.sat_math_avg_score)
                writingScore.text =
                    getSpanableString(R.string.writing_score, schoolItem.sat_writing_avg_score)
            }
        } ?: setErrorMessageVisibility()
    }

    private fun setErrorMessageVisibility() {
        binding.errorMessage.visibility = View.VISIBLE
    }

    private fun getSpanableString(stringID: Int, value: String) = SpannableStringBuilder()
        .append(resources.getString(stringID))
        .color(Color.BLUE) { append(value) }

    companion object {
        private const val DBN = "dbn"
        fun newInstance(dbn: String): SchoolDetailsFragment =
            SchoolDetailsFragment()
                .apply {
                    val bundle = Bundle()
                    bundle.putString(DBN, dbn)
                    arguments = bundle
                }
    }
}