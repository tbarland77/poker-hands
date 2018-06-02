object GameDecider {

    private fun compareSameRanks(player1: Player, player2: Player) {
        when {
            player1.hand?.rank == 1 && player2.hand?.rank == 1 -> {
                handleHighCardTie(player1, player2)
            }
            player1.hand?.rank == 2 && player2.hand?.rank == 2 -> {
                // call pair tie
            }
            player1.hand?.rank == 3 && player2.hand?.rank == 3 -> {
                // call two pair tie
            }
            player1.hand?.rank == 4 && player2.hand?.rank == 4 -> {
                // call three of a kind tie
            }
            player1.hand?.rank == 5 && player2.hand?.rank == 5 -> {
                // call straight tie
            }
            player1.hand?.rank == 6 && player2.hand?.rank == 6 -> {
                // call flush tie
            }
            player1.hand?.rank == 7 && player2.hand?.rank == 7 -> {
                // call full house tie
            }
            player1.hand?.rank == 8 && player2.hand?.rank == 8 -> {
                // call four of a kind tie
            }
            player1.hand?.rank == 9 && player2.hand?.rank == 9 -> {
                // call straight flush
            }
            else -> {
                println("This match is a tie!")
            }
        }
    }

    private fun handleHighCardTie(player1: Player, player2: Player) {
        for (i in 0 until player1.hand?.pokerHand!!.size) {
            when {
                player1.hand?.pokerHand?.get(i)?.value!!.numericValue >
                        player2.hand?.pokerHand?.get(i)?.value!!.numericValue -> {
                    player1.isWinner = true
                    player1.winningCard = player1.hand!!.pokerHand[i]!!
                    player2.isWinner = false
                }
                player2.hand?.pokerHand?.get(i)?.value!!.numericValue >
                        player1.hand?.pokerHand?.get(i)?.value!!.numericValue -> {
                    player1.isWinner = false
                    player2.isWinner = true
                    player2.winningCard = player2.hand!!.pokerHand[i]!!
                }
                i == player1.hand?.pokerHand!!.lastIndex -> {
                    player1.isWinner = false
                    player2.isWinner = false
                }
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