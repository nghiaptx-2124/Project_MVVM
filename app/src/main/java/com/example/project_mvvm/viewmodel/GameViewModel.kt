package com.example.project_mvvm.viewmodel

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.databinding.ObservableArrayMap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_mvvm.R
import com.example.project_mvvm.model.classes.Cell
import com.example.project_mvvm.model.classes.Game
import com.example.project_mvvm.model.classes.Player
import com.example.project_mvvm.model.enumclass.PlayerValue


class GameViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var cells: ObservableArrayMap<String, Int>
    private lateinit var game: Game

    fun Init(p1: String, p2: String) {
        game = Game(p1, p2)
        cells = ObservableArrayMap()

    }

    //Bat su kien BindingData
    fun onClickAtCell(r: Int, c: Int) {

        if (game.cells[r][c] == null) {
            game.cells[r][c] = game.currentPlayer?.let {
                Cell(it)
            }
            var res = 0
            if (game.currentPlayer?.valuePlayer == PlayerValue.VALUE_X) {
                res = R.drawable.ic_cross
            } else {
                res = R.drawable.ic_oval
            }
            cells.put(r.toString() + c.toString(), res)

            if (game.isGameEnded()) {
                resetGame()
            } else {
                game.switchPlayer()
            }
        }
    }


    fun getWinners(): MutableLiveData<Player?> {
        return game.winner
    }


    fun resetGame() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(Runnable {
            game.reset()
            cells.clear()
        }, 1000)
    }
}