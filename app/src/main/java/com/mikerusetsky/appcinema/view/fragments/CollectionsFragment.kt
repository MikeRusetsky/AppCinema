package com.mikerusetsky.appcinema.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mikerusetsky.appcinema.databinding.FragmentCollectionsBinding
import com.mikerusetsky.appcinema.utils.AnimationHelper


class CollectionsFragment : Fragment() {
    private lateinit var binding4: FragmentCollectionsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding4 = FragmentCollectionsBinding.inflate(inflater, container, false)
        return binding4.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AnimationHelper.performFragmentCircularRevealAnimation(binding4.collectionsFragmentRoot, requireActivity(), 4)
    }
}