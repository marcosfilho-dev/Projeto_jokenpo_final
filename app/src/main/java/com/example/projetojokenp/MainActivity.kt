package com.example.projetojokenp


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.GravityCompat
import androidx.core.view.isInvisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.projetojokenp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), PlayerFragment.JogadorListener {
    lateinit var  drawer: DrawerLayout
    lateinit var navDrawer : NavigationView
    lateinit var bottomNav: BottomNavigationView
     lateinit var  navController: NavController
     lateinit var appBarConfiguration: AppBarConfiguration
     var currentPlay: String = "Pedra"

     //lateinit var editText : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("LifeCycle", "onCreate")

        val binding = ActivityMainBinding.inflate(layoutInflater)
        val toolbar = binding.toolbar
        setContentView(binding.root)
        setSupportActionBar(toolbar)


        drawer = binding.root
        navDrawer = binding.navView
        bottomNav = binding.bottomNav

        // Está tendo o editText para mostrar o funcionamento do OnSaveInstanceState \\
        //savedInstanceState?.getString("editTextvalue")?.let {
           // editText.setText(it)
        //}

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(setOf(R.id.playerFragment, R.id.resultFragment), drawer)

        navController.addOnDestinationChangedListener{ _,destination, _ ->
            when(destination.id){
                R.id.homeFragment -> bottomNav.visibility = View.GONE
                    else -> bottomNav.visibility = View.VISIBLE
            }
        }
        setupActionBarWithNavController(navController, appBarConfiguration)
        navDrawer.setupWithNavController(navController)

        bottomNav.setupWithNavController(navController)
        bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.resultFragment -> {
                    val args = Bundle()
                    args.putString("currentPlay",currentPlay)
                    navController.navigate(it.itemId, args)
                }
                else -> navController.navigate(it.itemId)
            }
            true
        }
        navDrawer.setupWithNavController(navController)
        navDrawer.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.resultFragment -> {
                    val args = Bundle()
                    args.putString("currentPlay",currentPlay)
                    navController.navigate(it.itemId, args)
                }
                else -> navController.navigate(it.itemId)
            }
          true
        }
          //setupToolbar()
        //setupDrawer()


    }


    /*override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
       // outState.putString("editTextValue",editText.text.toString())
        // Está tendo o editText para mostrar o funcionamento do OnSaveInstanceState \\
    }*/

    override fun onResume() {
        super.onResume()
        Log.d("LifeCycle", "onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LifeCycle", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LifeCycle", "onDestroy")
    }

    override fun onSupportNavigateUp(): Boolean {
        return  navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override var thisContext: Context
        get() = applicationContext
        set(value) {}

    override fun onPlaySelected(selectedPlay: String) {
        currentPlay = selectedPlay
    }


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val availablePlays = resources.getStringArray(R.array.available_plays_arrays)
        onPlaySelected(availablePlays[position])
        Toast.makeText(this,"Jogada selecionada : $currentPlay", Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}