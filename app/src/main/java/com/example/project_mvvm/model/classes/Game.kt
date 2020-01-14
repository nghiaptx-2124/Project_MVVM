package com.example.project_mvvm.model.classes

import androidx.lifecycle.MutableLiveData
import com.example.project_mvvm.model.enumclass.PlayerValue

class Game (playerOneName: String, playerTwoName: String)  {
    var BOARD_SIZE: Int = 3

    var player1: Player?
    var player2: Player?
    var currentPlayer: Player?
    var scorePlayer1: Int
    var scorePlayer2: Int
    var cells = arrayOf<Array<Cell?>>()

    val winner: MutableLiveData<Player?> = MutableLiveData()


    init {
        cells = Array(
            BOARD_SIZE
        ) { arrayOfNulls<Cell>(BOARD_SIZE) }
        player1 =
            Player(playerOneName, "x")
        scorePlayer1 = 0
        player2 =
            Player(playerTwoName, "o")
        scorePlayer2 = 0
        currentPlayer = player1
    }

    fun switchPlayer() {
        currentPlayer = if (currentPlayer == player1) player2 else player1
    }

    //Game kết thúc khi thỏa 3 hàm

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

    fun increasePointWinner(): Int {
        if (winner.value == player1)
            return scorePlayer1++
        else if (winner.value == player2)
            return scorePlayer2++
        else
            return 0
    }

    //Nếu có 3 ô hàng ngang giống nhau

    fun hasThreeSameOnHorizontalsCells(): Boolean {
        val value: PlayerValue = currentPlayer!!.valuePlayer

        return areEquals(value, cells[0][0], cells[0][1], cells[0][2])
                || areEquals(value, cells[1][0], cells[1][1], cells[1][2])
                || areEquals(value, cells[2][0], cells[2][1], cells[2][2])

    }

    //Nếu có 3 ô hàng dọc giống nhau

    fun hasThreeSameOnVericalsCells(): Boolean {
        val value: PlayerValue = currentPlayer!!.valuePlayer

        return areEquals(value, cells[0][0], cells[1][0], cells[2][0])
                || areEquals(value, cells[0][1], cells[1][1], cells[2][1])
                || areEquals(value, cells[0][2], cells[1][2], cells[2][2])
    }

    //Nếu có 3 ô đường chéo giống nhau

    fun hasThreeSameOnDiagnoCells(): Boolean {
        val value: PlayerValue = currentPlayer!!.valuePlayer

        return areEquals(value, cells[0][0], cells[1][1], cells[2][2])
                || areEquals(value, cells[0][2], cells[1][1], cells[2][0])

    }

    //Kiểm tra đã điền hết các ô

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

    //Kiểm tra ô đã được điền hay chưa

    fun areEquals(value: PlayerValue, vararg cell: Cell?): Boolean {
        cell.forEach {
            if (it == null || it.isEmpty() || it.player != currentPlayer || it.player.valuePlayer != value) {
                return false
            }
        }
        return true
    }

    //Reset giá trị về ban đầu
    fun reset() {
        cells = Array(BOARD_SIZE, { arrayOfNulls<Cell>( BOARD_SIZE ) })
        currentPlayer = player1
    }
}