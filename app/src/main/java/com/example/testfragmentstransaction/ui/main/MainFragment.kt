package com.example.testfragmentstransaction.ui.main

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.testfragmentstransaction.R
import java.util.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance(fragmentNumber: Int) = MainFragment().apply {
            arguments = Bundle().apply {
                putInt(FRAGMENT_NUMBER_KEY, fragmentNumber)
            }
        }

        private const val FRAGMENT_NUMBER_KEY = "NUMBER_KEY"
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentNumber = requireArguments().getInt(FRAGMENT_NUMBER_KEY, 0)

        view.setBackgroundColor(getRandomColor())
        view.findViewById<TextView>(R.id.message).text = "Fragment number - $fragmentNumber"
    }

    private fun getRandomColor(): Int =
        with(Random()) {
            Color.argb(255, nextInt(256), nextInt(256), nextInt(256))
        }
}
