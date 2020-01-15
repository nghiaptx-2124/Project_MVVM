package com.example.project_mvvm.model.classes

import com.example.project_mvvm.model.enumclass.PlayerValue

class Player(name: String, value: String, score: Int) {
    var valuePlayer: PlayerValue
    var score: Int
    var name: String
    //Kiem tra gia tri truyen vao la X hay O
    init {
        this.name = name
        this.score = score
        if (value.equals("x", true)) {
            valuePlayer = PlayerValue.VALUE_X
        } else if (value.equals("o", true)) {
            valuePlayer = PlayerValue.VALUE_O
        } else {
            valuePlayer = PlayerValue.VALUE_EMPTY
        }
    }


}
