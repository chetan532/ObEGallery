package com.cv.obegallery.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.SharedElementCallback
import androidx.core.view.doOnPreDraw
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import androidx.viewpager2.widget.ViewPager2
import com.cv.obegallery.R
import com.cv.obegallery.databinding.FragmentDetailsBinding
import com.cv.obegallery.models.NasaData
import com.cv.obegallery.presentation.main.MainViewModel
import com.cv.obegallery.retrofit.Result
import com.cv.obegallery.utils.DepthPageTransformer
import com.cv.obegallery.utils.GetSortedData

class DetailsFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    val detailsPagerAdapter by lazy {
        DetailsPagerAdapter(onItemClick = {
            onItemClick(it)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareSharedElementTransition()
        postponeEnterTransition()
        (view.parent as? ViewGroup)?.doOnPreDraw {
            startPostponedEnterTransition()
        }

        binding.toolbar.setNavigationOnClickListener { findNavController().navigateUp() }

        mainViewModel.nasaDataLiveData.observeForever {
            when (it) {
                is Result.Success -> {
                    if (it.data != null) {
                        detailsPagerAdapter.submitList(GetSortedData(it.data).invoke())
                    }
                }
                is Result.Error -> {}
                is Result.Loading -> {}
            }
        }

        setupViewPager()
    }

    fun onItemClick(nasaData: NasaData) {
        findNavController().navigate(
            DetailsFragmentDirections.actionDetailsFragmentToBottomSheetFragment(
                nasaData
            )
        )
    }

    private fun setupViewPager() {
        binding.viewPager.adapter = detailsPagerAdapter
        binding.viewPager.setCurrentItem(mainViewModel.selectedIndex, false)
        binding.viewPager.setPageTransformer(DepthPageTransformer())
        binding.viewPager.offscreenPageLimit = 2
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mainViewModel.selectedIndex = position
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun prepareSharedElementTransition() {
        val transition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.image_shared_element_transition)
        sharedElementEnterTransition = transition

        setEnterSharedElementCallback(
            object : SharedElementCallback() {
                override fun onMapSharedElements(
                    names: List<String>,
                    sharedElements: MutableMap<String, View>
                ) {

                    val selectedViewHolder =
                        (binding.viewPager[0] as RecyclerView).findViewHolderForAdapterPosition(
                            mainViewModel.selectedIndex
                        ) ?: return

                    sharedElements[names[0]] =
                        selectedViewHolder.itemView.findViewById(R.id.largeImage)
                }
            })
    }
}