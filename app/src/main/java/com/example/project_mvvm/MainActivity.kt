package com.example.project_mvvm

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayMap
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.project_mvvm.databinding.ActivityMainBinding
import com.example.project_mvvm.model.classes.Cell
import com.example.project_mvvm.model.classes.Player
import com.example.project_mvvm.viewmodel.GameViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val NO_ONE: String = "No one"

    lateinit var gameViewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityGameBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        gameViewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        gameViewModel.Init("P1", "P2")
        setUpListener()
        activityGameBinding.gameViewModel = gameViewModel
    }


    fun setUpListener() {
        gameViewModel.getWinners().observe(this, Observer { onGameWinnerChange(it) })
    }


    fun onGameWinnerChange(winner: Player?) {
        var winnerName = if (winner?.name != null
            && winner != null
            && winner.name.isNotEmpty()
        ) winner.name else NO_ONE
        Toast.makeText(applicationContext, "$winnerName", Toast.LENGTH_SHORT).show()
    }

}
