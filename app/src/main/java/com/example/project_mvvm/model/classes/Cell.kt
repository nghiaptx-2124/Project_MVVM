package com.example.project_mvvm.model.classes

import com.example.project_mvvm.model.enumclass.PlayerValue

class Cell {
    var player: Player

    constructor(player: Player) {
        this.player = player
    }

    constructor(name: String, value: String) {
        this.player = Player(name, value)
    }

    fun isEmpty(): Boolean {
        return player == null || player.valuePlayer == PlayerValue.VALUE_EMPTY
    }

}