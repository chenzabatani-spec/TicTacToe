package com.example.tictactoe

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvStatus: TextView
    private lateinit var btnPlayAgain: Button
    private lateinit var buttons: List<Button>

    private val game = TicTacToeGame()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        tvStatus = findViewById(R.id.tvStatus)
        btnPlayAgain = findViewById(R.id.btnPlayAgain)

        buttons = listOf(
            findViewById(R.id.btn0),
            findViewById(R.id.btn1),
            findViewById(R.id.btn2),
            findViewById(R.id.btn3),
            findViewById(R.id.btn4),
            findViewById(R.id.btn5),
            findViewById(R.id.btn6),
            findViewById(R.id.btn7),
            findViewById(R.id.btn8)
        )

        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                if (game.play(index)) {
                    render()
                }
            }
        }

        btnPlayAgain.setOnClickListener {
            game.reset()
            render()
        }

        render()
    }

    private fun render() {
        buttons.forEachIndexed { i, btn ->
            val cell = game.getCell(i)
            btn.text = when (cell) {
                TicTacToeGame.Player.X -> "X"
                TicTacToeGame.Player.O -> "O"
                null -> ""
            }
            btn.isEnabled = (cell == null && game.state == TicTacToeGame.State.RUNNING)
        }

        when (game.state) {
            TicTacToeGame.State.RUNNING -> {
                tvStatus.text = getString(R.string.player_turn, game.currentPlayer)
                btnPlayAgain.visibility = Button.GONE
            }
            TicTacToeGame.State.X_WON -> {
                tvStatus.text = getString(R.string.x_wins)
                btnPlayAgain.visibility = Button.VISIBLE
            }
            TicTacToeGame.State.O_WON -> {
                tvStatus.text = getString(R.string.o_wins)
                btnPlayAgain.visibility = Button.VISIBLE
            }
            TicTacToeGame.State.DRAW -> {
                tvStatus.text = getString(R.string.draw)
                btnPlayAgain.visibility = Button.VISIBLE
            }
        }
    }
}
