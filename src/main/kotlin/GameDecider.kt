object GameDecider {

    fun compareSameRanks(player1: Player, player2: Player) {
        when {
            player1.hand?.winningCard!!.value.numericValue > player2.hand?.winningCard!!.value.numericValue -> {
                player1.isWinner = true
                player2.isWinner = false
            }
            player1.hand?.winningCard!!.value.numericValue < player2.hand?.winningCard!!.value.numericValue -> {
                player1.isWinner = false
                player2.isWinner = true
            }
            else -> {
                player1.isWinner = false
                player2.isWinner = false
                println("This match is a tie!")
            }
        }
    }

    fun compareHandRanks(player1: Player, player2: Player) {
        when {
            player1.hand!!.rank > player2.hand!!.rank -> {
                player1.isWinner = true
                player2.isWinner = false
            }
            player2.hand!!.rank > player1.hand!!.rank -> {
                player2.isWinner = true
                player1.isWinner = false
            }
            player1.hand!!.rank == player2.hand!!.rank -> GameDecider.compareSameRanks(player1, player2)
        }

    }
}