import java.util.*

class Hand(var pokerHand: Array<Card> = Array(5) {Card()}, var rank: Int = 0) {

    init {
        pokerHand = buildPokerHand()
    }

    fun sortCards(playerHand: Array<Card>): Array<Card> {
        playerHand.sortWith(Comparator { c1: Card, c2: Card -> c1.value.compareTo(c2.value) })
        playerHand.reverse()
        return playerHand
    }

    private fun buildPokerHand(): Array<Card> {
        val pokerHand: Array<Card> = Array(5) {Card()}
        for(i in 0..4) {
            val card = Card(suit = Suit.HEARTS, value = CardValue.TWO)
            card.suit = Suit.values().toList().shuffled().first()
            card.value = CardValue.values().toList().shuffled().first()
            pokerHand[i] = card
        }
        return pokerHand
    }

}