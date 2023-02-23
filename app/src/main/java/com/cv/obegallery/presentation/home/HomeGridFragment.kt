package com.cv.obegallery.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.cv.obegallery.databinding.FragmentHomeGridBinding
import com.cv.obegallery.presentation.main.MainViewModel
import com.cv.obegallery.retrofit.Result
import com.cv.obegallery.utils.GetSortedData
import com.cv.obegallery.utils.gone
import com.cv.obegallery.utils.visible

class HomeGridFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentHomeGridBinding? = null
    private val binding get() = _binding!!

    lateinit var homeGridAdapter: HomeGridAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeGridBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.nasaDataLiveData.observe(
            viewLifecycleOwner,
            Observer {

                when (it) {

                    is Result.Success -> {

                        binding.shimmerGridLayout.newsShimmerLayout.stopShimmer()
                        binding.shimmerGridLayout.newsShimmerLayout.gone()
                        binding.recyclerViewPhotos.visible()

                        homeGridAdapter = HomeGridAdapter(onItemClick = { position, view ->

                            onItemClick(position, view)
                        }, {

                            if (mainViewModel.selectedIndex == it) {
                                startPostponedEnterTransition()
                            }
                        }).apply {

                            if (it.data != null && it.data.size > 0) {
                                submitList(GetSortedData(it.data).invoke())
                            }
                        }

                        binding.recyclerViewPhotos.adapter = homeGridAdapter
                    }
                    is Result.Error -> {
                        binding.shimmerGridLayout.newsShimmerLayout.stopShimmer()
                        binding.shimmerGridLayout.newsShimmerLayout.gone()
                    }
                    is Result.Loading -> {

                        binding.shimmerGridLayout.newsShimmerLayout.startShimmer()
                        binding.shimmerGridLayout.newsShimmerLayout.visible()
                        binding.recyclerViewPhotos.gone()
                    }
                }
            }
        )
    }

    fun onItemClick(position: Int, view: View?) {
        mainViewModel.selectedIndex = position
        val extras =
            FragmentNavigatorExtras((view as ImageView) to view.transitionName)
        findNavController().navigate(
            HomeGridFragmentDirections.actionFactsFragmentToDetailsFragment(),
            extras
        )
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
}