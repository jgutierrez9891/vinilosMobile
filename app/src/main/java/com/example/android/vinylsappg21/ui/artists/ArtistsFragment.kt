package com.example.android.vinylsappg21.ui.artists

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.vinylsappg21.databinding.FragmentArtistsBinding
import com.example.android.vinylsappg21.models.Artist
import com.example.android.vinylsappg21.ui.adapters.ArtistsAdapter
import com.example.android.vinylsappg21.viewmodels.ArtistViewModel

class ArtistsFragment : Fragment() {

    private var _binding: FragmentArtistsBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: ArtistViewModel
    private var viewModelAdapter: ArtistsAdapter? = null
    private lateinit var artistsList: List<Artist>
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
        val view = binding.root
        viewModelAdapter = ArtistsAdapter()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.artistsRv
        recyclerView.layoutManager = GridLayoutManager(context,2)
        recyclerView.adapter = viewModelAdapter

        binding.searchTextArtista.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if (::artistsList.isInitialized) {
                    viewModelAdapter!!.artists =
                        artistsList.filter { it.name.contains(s, ignoreCase = true) }
                }
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        viewModel = ViewModelProvider(this, ArtistViewModel.Factory(activity.application)).get(ArtistViewModel::class.java)
        viewModel.artists.observe(viewLifecycleOwner, Observer<List<Artist>> {
            it.apply {
                viewModelAdapter!!.artists = this
                artistsList = this
            }
        })
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }
}