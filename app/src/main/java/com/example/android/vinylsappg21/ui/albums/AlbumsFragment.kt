package com.example.android.vinylsappg21.ui.albums

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.vinylsappg21.GlobalStuff
import com.example.android.vinylsappg21.R
import com.example.android.vinylsappg21.databinding.FragmentAlbumsBinding
import com.example.android.vinylsappg21.models.Album
import com.example.android.vinylsappg21.ui.adapters.AlbumsAdapter
import com.example.android.vinylsappg21.viewmodels.AlbumViewModel

class AlbumsFragment : Fragment() {

    private var _binding: FragmentAlbumsBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: AlbumViewModel
    private var viewModelAdapter: AlbumsAdapter? = null
    private lateinit var albumsList: List<Album>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val albumsViewModel =
            ViewModelProvider(this).get(AlbumViewModel::class.java)

        _binding = FragmentAlbumsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val view = binding.root
        viewModelAdapter = AlbumsAdapter()

        val button: Button = view.findViewById(R.id.fetch_button)

        if(GlobalStuff.userType == 0) {
            button.setVisibility(View.GONE);
        } else {
            button.setVisibility(View.VISIBLE);
        }

        button.setOnClickListener { v ->
            val activity = v!!.context as AppCompatActivity;
            activity?.let{
                val intent = Intent (it, CreateAlbumActivity::class.java)
                it.startActivity(intent)
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.albumsRv
        recyclerView.layoutManager = GridLayoutManager(context,2)
        recyclerView.adapter = viewModelAdapter
        binding.searchTextAlbums.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if (::albumsList.isInitialized) {
                    viewModelAdapter!!.albums =
                        albumsList.filter { it.name.contains(s, ignoreCase = true) }
                }
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        viewModel = ViewModelProvider(this, AlbumViewModel.Factory(activity.application)).get(AlbumViewModel::class.java)
        viewModel.albums.observe(viewLifecycleOwner, Observer<List<Album>> {
            it.apply {
                viewModelAdapter!!.albums = this
                albumsList = this
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