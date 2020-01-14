package com.example.project_mvvm.model.classes

import androidx.lifecycle.MutableLiveData
import com.example.project_mvvm.model.enumclass.PlayerValue

class Cell {
    var player: Player


    constructor(player: Player) {
        this.player = player

    }

    fun isEmpty(): Boolean {
        return player == null || player.valuePlayer == PlayerValue.VALUE_EMPTY
    }

}