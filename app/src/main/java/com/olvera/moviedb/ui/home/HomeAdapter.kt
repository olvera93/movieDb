package com.olvera.moviedb.ui.home


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.olvera.moviedb.R
import com.olvera.moviedb.databinding.ItemPopularViewPagerBinding
import com.olvera.moviedb.model.Movie
import com.olvera.moviedb.ui.movieRated.MovieAdapter

class HomeAdapter(var popularMovieList: List<Movie>):
    PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any { // instantiateItem se llama cuando el viewPager necesita una nueva vista
        val inflater = LayoutInflater.from(container.context) // inflate la vista que se va a mostrar
        val binding = DataBindingUtil.inflate<ItemPopularViewPagerBinding>( // DataBindingUtil.inflate es una función que infla la vista que se va a mostrar
            inflater,
            R.layout.item_popular_view_pager,
            container,
            false
        )
        binding.popular = popularMovieList[position]//binding.imageView.setImageResource(images[position])
        binding.root.setOnClickListener {
            val bundle = bundleOf("movie_details" to popularMovieList[position])
            Navigation.findNavController(it)
                .navigate(R.id.next_fragment, bundle)
        }
        container.addView(binding.root)

        return binding.root
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean { // isViewFromObject se llama cuando el viewPager necesita saber si la vista que se está mostrando es la que se está mostrando
        return view == `object`
    }

    override fun getCount(): Int = popularMovieList.size

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) { // destroyItem se llama cuando el viewPager necesita destruir una vista
        container.removeView(`object` as ConstraintLayout)
    }
}