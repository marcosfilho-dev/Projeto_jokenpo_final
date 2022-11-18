package com.example.projetojokenp

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.service.media.MediaBrowserService.BrowserRoot
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.example.projetojokenp.databinding.FragmentPlayerBinding


@Suppress("DEPRECATION")
class PlayerFragment : Fragment() {
    lateinit var root: View
    lateinit var selectedPlay: Spinner
    lateinit var listener : JogadorListener
    private lateinit var onItemSelectedListener: OnItemSelectedListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
            onItemSelectedListener = context as OnItemSelectedListener
        listener = context as JogadorListener
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentPlayerBinding.inflate(inflater,container,false)
        root = binding.root
        selectedPlay = binding.spinner
        setHasOptionsMenu(true)
        setupSelectplaySpinner()
        return root
    }
    fun onItemSelectedListener(){

    }
    private fun setupSelectplaySpinner(){
        val adapter = ArrayAdapter.createFromResource(requireContext(),
            R.array.available_plays_arrays,
        android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        selectedPlay.adapter = adapter
        //selectedPlay.onItemSelectedListener = onItemSelectedListener
        selectedPlay.onItemSelectedListener = listener

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar,menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return  item.onNavDestinationSelected(findNavController())
    }
    override fun onResume() {
        super.onResume()
        Log.d("LifeCycle","onResume")
    }

    override fun onStart() {
        super.onStart()
        Log.d("LifeCycle","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LifeCycle","on")
        Log.d("LifeCycle","onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("LifeCycle","onDetach")
    }

    /*override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("LifeCycle","onAttach")
    }*/


    interface  JogadorListener : AdapterView.OnItemSelectedListener{
        var thisContext : Context
        fun onPlaySelected(selectedPlay: String){
            fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val availablePlays = resources.getStringArray(R.array.available_plays_arrays)
                onPlaySelected(availablePlays[position])
                Toast.makeText(this,"Jogada selecionada : $currentPlay", Toast.LENGTH_SHORT).show()
        }
    }


}