import java.util.*

class Hand {

    var pokerHand: Array<Card?>
    var rank: Int = 0
    var winningCard: Card? = null

    constructor(pokerHand: Array<Card?>, rank: Int, winningCard: Card?) {
        this.pokerHand = pokerHand
        this.rank = rank
        this.winningCard = winningCard
    }

    constructor(playerHand: Array<Card?>) {
        pokerHand = playerHand

    }

    fun sortCards(playerHand: Array<Card?>): Array<Card?> {

        playerHand.sortWith(Comparator { c1: Card?, c2: Card? -> c1?.value!!.compareTo(c2!!.value) })
        return playerHand
    }

}