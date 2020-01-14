package com.example.project_mvvm.model.classes

import androidx.databinding.ObservableArrayMap
import androidx.lifecycle.MutableLiveData
import com.example.project_mvvm.model.enumclass.PlayerValue

class Game {
    var BOARD_SIZE: Int = 3

    var player1: Player?
    var player2: Player?
    var currentPlayer: Player?

    var cells = arrayOf<Array<Cell?>>()

    val winner: MutableLiveData<Player?> = MutableLiveData()
    val cellSS: MutableLiveData<ObservableArrayMap<String, Int>> = MutableLiveData()


    constructor(playerOneName: String, playerTwoName: String) {
        cells = Array(
            BOARD_SIZE
        ) { arrayOfNulls<Cell>(BOARD_SIZE) }
        player1 =
            Player(playerOneName, "x")
        player2 =
            Player(playerTwoName, "o")

        currentPlayer = player1
    }

    fun switchPlayer() {
        currentPlayer = if (currentPlayer == player1) player2 else player1
    }

    fun isGameEnded(): Boolean {
        if (hasThreeSameOnHorizontalsCells()
            || hasThreeSameOnVericalsCells()
            || hasThreeSameOnDiagnoCells()
        ) {
            winner.value = currentPlayer
            return true
        } else if (isBoardFull()) {
            winner.value = null
            return true
        }
        return false
    }


    fun hasThreeSameOnHorizontalsCells(): Boolean {
        val value: PlayerValue = currentPlayer!!.valuePlayer

        return areEquals(value, cells[0][0], cells[0][1], cells[0][2])
                || areEquals(value, cells[1][0], cells[1][1], cells[1][2])
                || areEquals(value, cells[2][0], cells[2][1], cells[2][2])

    }

    fun hasThreeSameOnVericalsCells(): Boolean {
        val value: PlayerValue = currentPlayer!!.valuePlayer

        return areEquals(value, cells[0][0], cells[1][0], cells[2][0])
                || areEquals(value, cells[0][1], cells[1][1], cells[2][1])
                || areEquals(value, cells[0][2], cells[1][2], cells[2][2])
    }

    fun hasThreeSameOnDiagnoCells(): Boolean {
        val value: PlayerValue = currentPlayer!!.valuePlayer

        return areEquals(value, cells[0][0], cells[1][1], cells[2][2])
                || areEquals(value, cells[0][2], cells[1][1], cells[2][0])

    }

    fun isBoardFull(): Boolean {
        for (numDong in 0 until BOARD_SIZE) {
            for (numCot in 0 until BOARD_SIZE) {
                if (cells[numDong][numCot] == null || cells[numDong][numCot]!!.isEmpty()) {
                    return false
                }
            }
        }
        return true
    }

    fun areEquals(value: PlayerValue, vararg cell: Cell?): Boolean {
        cell.forEach {
            if (it == null || it.isEmpty() || it.player != currentPlayer || it.player.valuePlayer != value) {
                return false
            }
        }
        return true
    }

    fun reset() {

        cells = Array(BOARD_SIZE, { arrayOfNulls<Cell>(
            BOARD_SIZE
        ) })
        currentPlayer = player1
    }
}