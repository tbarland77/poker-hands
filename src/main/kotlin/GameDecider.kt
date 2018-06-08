
object GameDecider {
    fun compareHandRanks(player1: Player, player2: Player) {
        when {
            player1.hand.rank > player2.hand.rank -> {
                player1.isWinner = true
                player2.isWinner = false
            }
            player2.hand.rank > player1.hand.rank -> {
                player2.isWinner = true
                player1.isWinner = false
            }
            player1.hand.rank == player2.hand.rank -> GameDecider.compareSameRanks(player1, player2)
        }
    }

    private fun setLowAceAndSort(player1: Player) {
        player1.hand.pokerHand.first().value = CardValue.ONE
        player1.hand.sortCards(player1.hand.pokerHand)
    }

    private fun compareSameRanks(player1: Player, player2: Player) {
        when {
            player1.hand.rank == 1 && player2.hand.rank == 1 -> {
                handleHighCardTie(player1, player2)
            }
            player1.hand.rank == 2 && player2.hand.rank == 2 -> {
                handlePairTie(player1, player2)
            }
            player1.hand.rank == 3 && player2.hand.rank == 3 -> {
                handleTwoPairTie(player1, player2)
            }
            player1.hand.rank == 4 && player2.hand.rank == 4 -> {
                handleThreeOfAKindTie(player1, player2)
            }
            player1.hand.rank == 5 && player2.hand.rank == 5 -> {
                handleStraightTie(player1, player2)
            }
            player1.hand.rank == 6 && player2.hand.rank == 6 -> {
                // call flush tie
            }
            player1.hand.rank == 7 && player2.hand.rank == 7 -> {
                // call full house tie
            }
            player1.hand.rank == 8 && player2.hand.rank == 8 -> {
                // call four of a kind tie
            }
            player1.hand.rank == 9 && player2.hand.rank == 9 -> {
                // call straight flush
            }
            else -> {
                println("This match is a tie!")
            }
        }
    }

    private fun handleHighCardTie(player1: Player, player2: Player) {
        for (i in 0 until player1.hand.pokerHand.size) {
            when {
                player1.hand.pokerHand[i].value.numericValue >
                        player2.hand.pokerHand[i].value.numericValue -> {
                    player1.isWinner = true
                    player1.winningCard = player1.hand.pokerHand[i].value
                    player2.isWinner = false
                    return
                }
                player2.hand.pokerHand[i].value.numericValue >
                        player1.hand.pokerHand[i].value.numericValue -> {
                    player1.isWinner = false
                    player2.isWinner = true
                    player2.winningCard = player2.hand.pokerHand[i].value
                    return
                }
            }
        }
        player1.isWinner = false
        player2.isWinner = false
    }

    private fun handlePairTie(player1: Player, player2: Player) {
        val cardValuesMapPlayer1 = player1.hand.pokerHand.groupingBy { it.value.numericValue }.eachCount()
        val cardValuesMapPlayer2 = player2.hand.pokerHand.groupingBy { it.value.numericValue }.eachCount()
        val pairValuePlayer1 = cardValuesMapPlayer1.maxBy { it.value }?.key
        val pairValuePlayer2 = cardValuesMapPlayer2.maxBy { it.value }?.key
            when {
                pairValuePlayer1!! >
                        pairValuePlayer2!! -> {
                    player1.isWinner = true
                    player1.winningCard = CardValue.getCardValueById(pairValuePlayer1)
                    player2.isWinner = false
                }
                pairValuePlayer1 <
                        pairValuePlayer2 -> {
                    player1.isWinner = false
                    player2.isWinner = true
                    player2.winningCard = CardValue.getCardValueById(pairValuePlayer2)
                }
                else -> {
                    handleHighCardTie(player1, player2)
            }
        }
    }

    private fun handleTwoPairTie(player1: Player, player2: Player) {
        val cardValuesMapPlayer1 = player1.hand.pokerHand.groupingBy { it.value.numericValue }.eachCount()
        val cardValuesMapPlayer2 = player2.hand.pokerHand.groupingBy { it.value.numericValue }.eachCount()
        val player1Pairs = cardValuesMapPlayer1.filter { it.value == 2 }
        val player2Pairs = cardValuesMapPlayer2.filter { it.value == 2 }
        val higherPairValuePlayer1 = player1Pairs.maxBy { it.key }?.key
        val higherPairValuePlayer2 = player2Pairs.maxBy { it.key }?.key
        val lowerPairValuePlayer1 = player1Pairs.minBy { it.key }?.key
        val lowerPairValuePlayer2 = player2Pairs.minBy { it.key }?.key
        when {
            higherPairValuePlayer1 !! >
                    higherPairValuePlayer2!! -> {
                player1.isWinner = true
                player1.winningCard = CardValue.getCardValueById(higherPairValuePlayer1)
                player2.isWinner = false
            }
            higherPairValuePlayer1 <
                    higherPairValuePlayer2 -> {
                player1.isWinner = false
                player2.isWinner = true
                player2.winningCard = CardValue.getCardValueById(higherPairValuePlayer2)
            }
            lowerPairValuePlayer1!! >
                lowerPairValuePlayer2!! -> {
            player1.isWinner = true
            player1.winningCard = CardValue.getCardValueById(lowerPairValuePlayer1)
            player2.isWinner = false
            }
            lowerPairValuePlayer1 <
                    lowerPairValuePlayer2 -> {
                player1.isWinner = false
                player2.isWinner = true
                player2.winningCard = CardValue.getCardValueById(lowerPairValuePlayer2)
            }
            else -> {
                handleHighCardTie(player1, player2)
            }
        }
    }

    private fun handleThreeOfAKindTie(player1: Player, player2: Player) {
        val cardValuesMapPlayer1 = player1.hand.pokerHand.groupingBy { it.value.numericValue }.eachCount()
        val cardValuesMapPlayer2 = player2.hand.pokerHand.groupingBy { it.value.numericValue }.eachCount()
        val threeOfAKindValuePlayer1 = cardValuesMapPlayer1.maxBy { it.value }?.key
        val threeOfAKindValuePlayer2 = cardValuesMapPlayer2.maxBy { it.value }?.key
        when {
            threeOfAKindValuePlayer1!! >
                    threeOfAKindValuePlayer2!! -> {
                player1.isWinner = true
                player1.winningCard = CardValue.getCardValueById(threeOfAKindValuePlayer1)
                player2.isWinner = false
            }
            threeOfAKindValuePlayer1 <
                    threeOfAKindValuePlayer2 -> {
                player1.isWinner = false
                player2.isWinner = true
                player2.winningCard = CardValue.getCardValueById(threeOfAKindValuePlayer2)
            }
            else -> {
                handleHighCardTie(player1, player2)
            }
        }
    }

    private fun handleStraightTie(player1: Player, player2: Player) {
        when {
            player1.hand.pokerHand.first().value == CardValue.ACE && player1.hand.pokerHand.last().value == CardValue.TEN && player1.hand.pokerHand.first().value !== CardValue.ACE -> {
                player1.isWinner = false
                player2.isWinner = true
                player1.winningCard = CardValue.getCardValueById(player1.hand.pokerHand.first().value.numericValue)
            }

            player2.hand.pokerHand.first().value == CardValue.ACE && player2.hand.pokerHand.last().value == CardValue.TEN && player1.hand.pokerHand.first().value !== CardValue.ACE -> {
                player1.isWinner = false
                player2.isWinner = true
                player2.winningCard = CardValue.getCardValueById(player2.hand.pokerHand.first().value.numericValue)
            }

            player1.hand.pokerHand.first().value == CardValue.ACE && player1.hand.pokerHand.last().value == CardValue.TWO -> {
                setLowAceAndSort(player1)
                compareHandRanks(player1, player2)
            }
            player2.hand.pokerHand.first().value == CardValue.ACE && player2.hand.pokerHand.last().value == CardValue.TWO -> {
                setLowAceAndSort(player1)
                compareHandRanks(player1, player2)
            }

            player1.hand.pokerHand.first().value == CardValue.ACE && player1.hand.pokerHand.last().value == CardValue.TEN && player2.hand.pokerHand.first().value == CardValue.ACE -> {
                player2.isWinner = false
                player1.isWinner = false
            }
            else -> {
                handleHighCardTie(player1, player2)
            }
        }
    }
}