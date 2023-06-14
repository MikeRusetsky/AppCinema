package com.mikerusetsky.appcinema

import android.os.Bundle
import android.transition.Scene
import android.transition.Slide
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikerusetsky.appcinema.databinding.FragmentHomeBinding
import com.mikerusetsky.appcinema.databinding.MergeHomeScreenContentBinding
import java.util.Locale


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var filmsAdapter : FilmListRecyclerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AnimationHelper.performFragmentCircularRevealAnimation(binding.homeFragmentRoot, requireActivity(), 1)

        //Подключаем слушателя изменений введенного текста в поиска
        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            //Этот метод отрабатывает при нажатии кнопки "поиск" на софт клавиатуре
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            //Этот метод отрабатывает на каждое изменения текста
            override fun onQueryTextChange(newText: String): Boolean {
                //Если ввод пуст то вставляем в адаптер всю БД
                if (newText.isEmpty()) {
                    filmsAdapter.addItems(filmsDataBase)
                    return true
                }
                //Фильтруем список на поискк подходящих сочетаний
                val result = filmsDataBase.filter {
                    //Чтобы все работало правильно, нужно и запрос, и имя фильма приводить к нижнему регистру
                    it.title.toLowerCase(Locale.getDefault()).contains(newText.toLowerCase(Locale.getDefault()))
                }
                //Добавляем в адаптер
                filmsAdapter.addItems(result)
                return true
            }
        })

        //находим наш RV
        initRecycler()
        //Кладем нашу БД в RV
        filmsAdapter.addItems(filmsDataBase)
    }

    //Инициализируем наш адаптер в конструктор передаем анонимно инициализированный интерфейс,
        private fun initRecycler() {
        binding.mainRecycler.apply {
            filmsAdapter =
                FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
                    override fun click(film: Film) {
                        (requireActivity() as MainActivity).launchDetailsFragment(film)
                    }

                })
            //Присваиваем адаптер
            adapter = filmsAdapter
            //Присвоим layoutmanager
            layoutManager = LinearLayoutManager(requireContext())
            //Применяем декоратор для отступов
            val decorator = TopSpacingItemDecoration(7)
            addItemDecoration(decorator)
        }

        //Кладем нашу БД в RV
        filmsAdapter.addItems(filmsDataBase)
    }

    val filmsDataBase = listOf(
        Film(
            "Back in Future", R.drawable.back, "Teenager Marty, with the " +
                    "help of a time machine built by his friend Professor Doc Brown, gets from the 80s to the distant 50s. " +
                    "There he meets his future parents, still teenagers, and a professor friend, quite young.", 7.7f
        ),
        Film(
            "Coco",
            R.drawable.coco,
            "12 year old Miguel lives in a Mexican village in a family of shoemakers and secretly dreams of becoming a musician. Secretly, because music is considered a curse in his family. Once his great-great-grandfather left his wife, Miguel's great-great-grandmother, for the sake of a dream that now does not allow his " +
                    "great-great-grandson to live in peace. Since then, the musical theme in the family has become a taboo",6.4f
        ),
        Film(
            "Fight Club",
            R.drawable.fight,
            "An insurance company employee suffers from chronic insomnia and is desperately trying to escape from a painfully boring life. One day, on a regular business trip, he meets a certain Tyler Durden," +
                    " a charismatic soap merchant with a perverted philosophy Tyler is sure that self-improvement is for the weak, and the only thing worth living for is self-destruction.", 8.3f
        ),
        Film(
            "Forrest Gump", R.drawable.forrest, "\n" +
                    "From childhood, the guy suffered from leg diseases, the neighbor boys teased him, but one day Forrest discovered his incredible ability to run. Childhood friend Jenny always supported and protected him, but soon they parted ways.",7.9f
        ),
        Film(
            "Green Mile",
            R.drawable.mile,
            "Paul Edgecomb is the head of the death row at Cold Mountain Prison, each of whose prisoners once walks the" +
                    "green mile on the way to the place of execution. Paul has seen many prisoners and guards during his work" +
                    "However, the giant John Coffey, accused of a terrible crime, has become one of the most unusual inhabitants of the block.",6.0f
        ),
        Film(
            "Inter",
            R.drawable.inter,
            "When drought, dust storms, and plant extinction lead humanity into a food crisis, " +
                    "a team of researchers and scientists set out through a wormhole (which supposedly connects regions of space-time over a long distance) on a journey to surpass previous limitations for human space travel and find a planet with suitable for human conditions", 8.8f
        ),
        Film(
            "Lord of Rings",
            R.drawable.lordof,
            "Sauron, the lord of the forces of darkness, sends his countless army under the walls of Minas Tirith, the fortress of Light's Hope. He looks forward to a close victory, but this is what prevents him from noticing two tiny figures - hobbits approaching Mount Doom, " +
                    "where they have to destroy the Ring of Omnipotence.",4.5f
        ),
        Film(
            "The Shawshank Redemption",
            R.drawable.show,
            "Accountant Andy Dufresne is accused of killing his wife and her lover. Once in a prison called Shawshank, he is faced with cruelty and lawlessness that reigns on both sides of the bars. Everyone who enters these walls becomes their slave for the rest of their lives. But Andy, who has a lively mind and a kind soul, " +
                    "finds an approach to both prisoners and guards, seeking their special disposition.",8.3f
        )
    )
}