package com.example.project_mvvm.viewmodel

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.databinding.ObservableArrayMap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.project_mvvm.R
import com.example.project_mvvm.model.classes.Cell
import com.example.project_mvvm.model.classes.Game
import com.example.project_mvvm.model.classes.Player
import com.example.project_mvvm.model.enumclass.PlayerValue


class GameViewModel(application: Application) : AndroidViewModel(application) {
    var cells: ObservableArrayMap<String, Int> = ObservableArrayMap()
    private var game: Game = Game("P1", "P2")

    var scorePlayer1: MutableLiveData<Int> = MutableLiveData()
    var scorePlayer2: MutableLiveData<Int> = MutableLiveData()
    val winner: MutableLiveData<Player?> = MutableLiveData()

    //Bat su kien BindingData
    fun onClickAtCell(row: Int, column: Int) {
        if (game.cells[row][column] == null) {
            game.cells[row][column] = game.currentPlayer?.let {
                Cell(it)
            }
            var res = 0
            if (game.currentPlayer?.valuePlayer == PlayerValue.VALUE_X) {
                res = R.drawable.ic_cross
            } else {
                res = R.drawable.ic_oval
            }
            cells.put(row.toString() + column.toString(), res)

            if (game.isGameEnded()) {
                winner.value = game.winner
                scorePlayer1.value = game.increasePointPlayer1()
                scorePlayer2.value = game.increasePointPlayer2()
                resetGame()
            } else {
                game.switchPlayer()
            }
        }

    }

    fun getPointPlayer1(): MutableLiveData<Int> {
        return scorePlayer1
    }

    fun getPointPlayer2(): MutableLiveData<Int> {
        return scorePlayer2
    }


    fun getWinners(): MutableLiveData<Player?> {
        return winner

    }


    fun resetGame() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(Runnable {
            game.reset()
            cells.clear()
        }, 1000)
    }
}