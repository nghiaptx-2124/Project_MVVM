package com.example.project_mvvm.viewmodel

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
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
    var cells: ObservableArrayMap<String, Int> = ObservableArrayMap()
    private var game: Game = Game("P1", "P2")


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
                resetGame()
            } else {
                game.switchPlayer()
            }
        }

    }

    fun getPoint(): String {
        return "${game.increasePointWinner()}"
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