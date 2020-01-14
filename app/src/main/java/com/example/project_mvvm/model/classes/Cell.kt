package com.example.project_mvvm.model.classes

import com.example.project_mvvm.model.enumclass.PlayerValue

class Cell(player: Player) {
    var player: Player

    init {
        this.player = player
    }

    fun isEmpty(): Boolean {
        return player == null || player.valuePlayer == PlayerValue.VALUE_EMPTY
    }

}