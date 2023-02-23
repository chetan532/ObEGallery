package com.cv.obegallery.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.cv.obegallery.databinding.FragmentHomeGridBinding
import com.cv.obegallery.presentation.main.MainViewModel
import com.cv.obegallery.retrofit.Result

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

        mainViewModel.getNasaData()
        mainViewModel.nasaDataLiveData.observe(
            viewLifecycleOwner,
            Observer {

                when (it) {

                    is Result.Success -> {

                        homeGridAdapter = HomeGridAdapter().apply {
                            submitList(it.data)
                        }

                        binding.recyclerViewPhotos.adapter = homeGridAdapter
                    }
                    is Result.Error -> {
                    }
                    is Result.Loading -> {
                    }
                }
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}