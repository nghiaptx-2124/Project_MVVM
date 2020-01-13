package com.example.project_mvvm.model.enumclass

enum class PlayerValue(value: String) {
    VALUE_EMPTY("VALUE_EMPTY"),
    VALUE_X("VALUE_X"),
    VALUE_O("VALUE_O");

    override fun toString(): String {
        val s = super.toString()
        if (s.equals("VALUE_X")) {
            return "X"
        } else {
            return "O"
        }
    }
}