fun main(args: Array<String>) {
    val player1 = Player()
    val player2 = Player()

    player1.name = determinePlayerName()
    player2.name = determinePlayerName()

    determinePlayerRanks(player1,player2)

    determineWinner(player1,player2)

    printWinner(player1,player2)
}

fun determinePlayerName(): String {
    print("Enter a name for the current player: ")
    return readLine()!!
}

fun determinePlayerRanks(player1: Player, player2: Player) {
    player1.hand.rank = RankDecider.determineRank(player1.hand.pokerHand)
    player2.hand.rank = RankDecider.determineRank(player2.hand.pokerHand)
}

fun determineWinner(player1: Player, player2: Player) {
    GameDecider.compareHandRanks(player1, player2)
    println()
}

fun printWinner(player1: Player, player2: Player) {
    when {
        player1.isWinner -> println("${player1.name} wins with a rank of ${HandRank.getHandRankingById(player2.hand.rank)} ")
        player2.isWinner -> println("${player2.name} wins with a rank of ${HandRank.getHandRankingById(player2.hand.rank)} ")
        else -> println("This match is a tie!")
    }
}