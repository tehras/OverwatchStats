package com.github.tehras.overwatchstats.fragments.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.tehras.overwatchstats.R
import com.github.tehras.overwatchstats.fragments.BaseFragment

/**
 * Created by tehras on 8/28/16.
 */
class CompetativePlayFragment : BaseFragment() {

    companion object {
        fun create(): CompetativePlayFragment {
            return CompetativePlayFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_competative_layout, container, false)
    }
}
