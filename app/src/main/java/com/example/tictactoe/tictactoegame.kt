package com.example.tictactoe

class TicTacToeGame {

    enum class Player { X, O }
    enum class State { RUNNING, X_WON, O_WON, DRAW }

    private val board = Array(9) { null as Player? }
    var currentPlayer: Player = Player.X
        private set

    var state: State = State.RUNNING
        private set

}
