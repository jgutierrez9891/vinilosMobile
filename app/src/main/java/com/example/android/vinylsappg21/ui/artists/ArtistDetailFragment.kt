package com.example.android.vinylsappg21.ui.artists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.android.vinylsappg21.databinding.FragmentArtistDetailBinding
import com.example.android.vinylsappg21.databinding.FragmentArtistsBinding
import com.example.android.vinylsappg21.models.Artist
import com.example.android.vinylsappg21.ui.adapters.ArtistsAdapter
import com.example.android.vinylsappg21.viewmodels.ArtistDetailViewModel
import com.example.android.vinylsappg21.viewmodels.ArtistViewModel

class ArtistDetailFragment : Fragment() {

    private var _binding: FragmentArtistDetailBinding? = null
    //private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: ArtistDetailViewModel
    //private var viewModelAdapter: ArtistsAdapter? = null
    private lateinit var artistsList: List<Artist>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val artistsViewModel =
            ViewModelProvider(this).get(ArtistViewModel::class.java)

        _binding = FragmentArtistDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val view = binding.root
        //viewModelAdapter = ArtistsAdapter()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /*recyclerView = binding.albumsArtist
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
        })*/
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        val args: ArtistDetailFragmentArgs by navArgs()
        viewModel = ViewModelProvider(this, ArtistDetailViewModel.Factory(activity.application, args.artistId)).get(ArtistDetailViewModel::class.java)
        viewModel.artists.observe(viewLifecycleOwner, Observer<List<Artist>> {
            it.apply {
                //viewModelAdapter!!.artists = this
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
        /*if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }*/
    }
}