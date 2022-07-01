package com.olvera.moviedb.ui.playing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.olvera.moviedb.R
import com.olvera.moviedb.databinding.FragmentPlayingNowBinding
import com.olvera.moviedb.util.BaseFragment
import kotlinx.android.synthetic.main.fragment_playing_now.*

class PlayingNowFragment : BaseFragment<FragmentPlayingNowBinding, PlayingNowViewModel>() {

    private var _binding: FragmentPlayingNowBinding? = null
    private val binding get() = _binding!!

    override fun getViewModel(): Class<PlayingNowViewModel> = PlayingNowViewModel::class.java


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentPlayingNowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMoviePlayingNow()

        viewModel.moviewPlayingNow.observe(viewLifecycleOwner) {
            recyclerViewPlayingNow.visibility = View.VISIBLE
            recyclerViewPlayingNow.layoutManager = GridLayoutManager(context, 1)
            recyclerViewPlayingNow.adapter = PlayingNowAdapter(it) {
                val bundle = bundleOf("movie_details" to it)
                Navigation.findNavController(view)
                    .navigate(R.id.next_fragment, bundle)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun getViewBinding(): Int {
        return R.layout.fragment_playing_now
    }


}