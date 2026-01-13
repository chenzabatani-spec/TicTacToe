package com.example.tictactoe

class TicTacToeGame {

    enum class Player { X, O }
    enum class State { RUNNING, X_WON, O_WON, DRAW }

    private val board = Array(9) { null as Player? }
    var currentPlayer: Player = Player.X
        private set

    var state: State = State.RUNNING
        private set

    fun getCell(index: Int): Player? = board[index]

    fun play(index: Int): Boolean {
        if (state != State.RUNNING) return false
        if (index !in 0..8) return false
        if (board[index] != null) return false

        board[index] = currentPlayer
        updateStateAfterMove()

        if (state == State.RUNNING) {
            currentPlayer = if (currentPlayer == Player.X) Player.O else Player.X
        }
        return true
    }

    fun reset() {
        for (i in board.indices) board[i] = null
        currentPlayer = Player.X
        state = State.RUNNING
    }

    private fun updateStateAfterMove() {
        val winner = checkWinner()
        state = when (winner) {
            Player.X -> State.X_WON
            Player.O -> State.O_WON
            null -> if (board.all { it != null }) State.DRAW else State.RUNNING
        }
    }

    private fun checkWinner(): Player? {
        val lines = arrayOf(
            intArrayOf(0, 1, 2),
            intArrayOf(3, 4, 5),
            intArrayOf(6, 7, 8),
            intArrayOf(0, 3, 6),
            intArrayOf(1, 4, 7),
            intArrayOf(2, 5, 8),
            intArrayOf(0, 4, 8),
            intArrayOf(2, 4, 6)
        )

        for (line in lines) {
            val a = board[line[0]]
            val b = board[line[1]]
            val c = board[line[2]]
            if (a != null && a == b && b == c) return a
        }
        return null
    }

}
