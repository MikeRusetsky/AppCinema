package com.mikerusetsky.appcinema.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikerusetsky.appcinema.databinding.FragmentFavoritesBinding
import com.mikerusetsky.appcinema.domain.Film
import com.mikerusetsky.appcinema.utils.AnimationHelper
import com.mikerusetsky.appcinema.view.MainActivity
import com.mikerusetsky.appcinema.view.rv_adapters.FilmListRecyclerAdapter
import com.mikerusetsky.appcinema.view.rv_adapters.TopSpacingItemDecoration


class FavoritesFragment : Fragment() {

    private lateinit var filmsAdapter: FilmListRecyclerAdapter
    private lateinit var binding5 : FragmentFavoritesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding5 = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding5.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Получаем список при транзакции фрагмента
        val favoritesList: List<Film> = emptyList()

        AnimationHelper.performFragmentCircularRevealAnimation(binding5.favoritesFragmentRoot, requireActivity(), 1)

        binding5.favoritesRecycler.apply {
            filmsAdapter =
                FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
                    override fun click(film: Film) {
                        (requireActivity() as MainActivity).launchDetailsFragment(film)
                    }
                })
            //Присваиваем адаптер
            adapter = filmsAdapter
            //Присвои layoutmanager
            layoutManager = LinearLayoutManager(requireContext())
            //Применяем декоратор для отступов
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
        //Кладем нашу БД в RV
        filmsAdapter.addItems(favoritesList)
    }
}
