enum class Suit(private val denoteSuit: Char) {
    CLUBS('C'), DIAMONDS('D'), HEARTS('H'), SPADES('S');

    fun getDenoteSuit(): Int {
        return denoteSuit.toInt()
    }

    companion object {
        // Gets the suit based on the passed in denoted suit
        fun getSuitByDenoteSuit(denotedSuit: Char): Suit? {

            var cSuit: Suit? = null

            when (denotedSuit) {
                'C' -> cSuit = CLUBS
                'D' -> cSuit = DIAMONDS
                'H' -> cSuit = HEARTS
                'S' -> cSuit = SPADES

                else -> {
                }
            }
            return cSuit

        }
    }
}