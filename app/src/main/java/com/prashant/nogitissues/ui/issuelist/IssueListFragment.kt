package com.prashant.nogitissues.ui.issuelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bigsteptech.deazzle.common.gone
import com.prashant.nogitissues.data.IssueItem
import com.prashant.nogitissues.databinding.FragmentDetailsBinding
import com.prashant.nogitissues.databinding.FragmentIssueListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IssueListFragment : Fragment(), PagedIssueAdapter.ItemClickListener {

    private val viewModel by viewModels<IssuesViewModel>()
    private val binding by lazy {
        FragmentIssueListBinding.inflate(layoutInflater)
    }
    private lateinit var pagedAdapter: PagedIssueAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setUpUi()
        setUpObservers()
        return binding.root
    }

    private fun setUpObservers() {

        viewModel.getPagedData()?.observe(viewLifecycleOwner, { pagedList ->

            pagedList?.let {
                pagedAdapter.submitList(it)
            }
            binding.progress.gone()
        })

    }

    private fun setUpUi() {
        with(binding) {
            pagedAdapter = PagedIssueAdapter(this@IssueListFragment)
            issueRecyclerView.apply {
                this.adapter = pagedAdapter
                setHasFixedSize(true)
            }

        }
    }

    override fun onItemClicked(item: IssueItem) {
        item.number?.let {
            val action = IssueListFragmentDirections.actionIssueListFragmentToIssueDetailsFragment(it)
            findNavController().navigate(action)
        }
    }
}