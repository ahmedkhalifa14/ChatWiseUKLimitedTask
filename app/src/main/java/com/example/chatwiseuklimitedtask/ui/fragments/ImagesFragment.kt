package com.example.chatwiseuklimitedtask.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatwiseuklimitedtask.adapters.ImageAdapter
import com.example.chatwiseuklimitedtask.databinding.FragmentImagesBinding
import com.example.chatwiseuklimitedtask.ui.viewmodels.MainViewModel
import com.example.chatwiseuklimitedtask.utils.EventObserver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ImagesFragment : Fragment() {

    private var _binding: FragmentImagesBinding? = null
    private val binding get() = _binding
    private lateinit var imageAdapter: ImageAdapter
    private lateinit var imageRv: RecyclerView
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentImagesBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        subscribeToObservers()
        mainViewModel.getAllImages()
    }

    private fun subscribeToObservers() {
        lifecycleScope.launchWhenStarted {
            launch {
                mainViewModel.imagesState.collect(
                    EventObserver(
                        onLoading = {
                            binding!!.spinkit.isVisible = true
                        },
                        onSuccess = { images ->
                            binding!!.spinkit.isVisible = false
                            imageAdapter.differ.submitList(images)
                            Log.d("here", images.toString())
                        },
                        onError = {
                            binding!!.spinkit.isVisible = false
                            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT)
                                .show()
                        }
                    )
                )

            }
        }
    }

    private fun setupRecyclerView() {
        imageRv = binding!!.imagesRv
        imageAdapter = ImageAdapter()
        imageRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        imageRv.adapter = imageAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}