package com.example.projetojokenp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.example.projetojokenp.databinding.FragmentResultBinding



@Suppress("DEPRECATION")
class ResultFragment : Fragment() {
    lateinit var engine: JokenpoEngine
    lateinit var bind : FragmentResultBinding
    lateinit var resultText: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        engine = JokenpoEngine(resources.getStringArray(R.array.available_plays_arrays))
        bind = FragmentResultBinding.inflate(inflater,container,false)
        val currentPlay = arguments?.getString("currentPlay")
        currentPlay?.let {
            updateResultText(it)
        }
        resultText = bind.resultlabel
        setHasOptionsMenu(true)
        return bind.root
    }

    private fun updateResultText(currentPlay : String){
        val resultGame = engine.calculateResult(currentPlay)
        resultText.text = when(resultGame){
            Result.Win -> "Você ganhou"
            Result.Loss -> "Você perdeu"
            else -> "Você empatou"
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar,menu)

    }

    @Deprecated("Deprecated in Java")
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("LifeCycle","onAttach")
    }


}