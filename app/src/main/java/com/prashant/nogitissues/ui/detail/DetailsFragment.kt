package com.prashant.nogitissues.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bigsteptech.deazzle.common.gone
import com.prashant.nogitissues.R
import com.prashant.nogitissues.databinding.ActivityMainBinding
import com.prashant.nogitissues.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val args: DetailsFragmentArgs by navArgs()
    private val viewModel by viewModels<IssueDetailViewModel>()
    private val binding by lazy {
        FragmentDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setUpObservers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.issueDetails(args.issueId)
    }

    private fun setUpObservers() {
        viewModel.issueLiveData.observe(viewLifecycleOwner, {
            binding.model = it
            binding.progress.gone()
        })

        viewModel.messageLiveData.observe(viewLifecycleOwner, {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            binding.progress.gone()
        })

    }
}