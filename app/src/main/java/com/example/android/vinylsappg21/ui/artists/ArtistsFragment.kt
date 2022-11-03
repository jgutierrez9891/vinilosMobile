package com.example.android.vinylsappg21.ui.artists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.vinylsappg21.databinding.FragmentArtistsBinding
import com.example.android.vinylsappg21.viewmodels.ArtistViewModel

class ArtistsFragment : Fragment() {

    private var _binding: FragmentArtistsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val artistsViewModel =
            ViewModelProvider(this).get(ArtistViewModel::class.java)

        _binding = FragmentArtistsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGallery
        artistsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}