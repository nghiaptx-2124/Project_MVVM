package com.example.project_mvvm.model.classes

import com.example.project_mvvm.model.enumclass.PlayerValue

class Player {
     var name: String
     var valuePlayer: PlayerValue

    //Kiem tra gia tri truyen vao la X hay O
    constructor (name: String, value: String) {
        this.name = name
        if (value.equals("x", true)) {
            valuePlayer = PlayerValue.VALUE_X
        } else if (value.equals("o", true)) {
            valuePlayer = PlayerValue.VALUE_O
        } else {
            valuePlayer = PlayerValue.VALUE_EMPTY
        }
    }

}