enum class CardValue(val numericValue: Int) {
    ONE(1),TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13), ACE(14);


    companion object {
        // Determines the card numericValue based on the id that gets passed
        fun getCardValueById(id: Int): CardValue {

            var cValue: CardValue = TWO

            when (id) {
                1 -> cValue = ONE
                2 -> cValue = TWO
                3 -> cValue = THREE
                4 -> cValue = FOUR
                5 -> cValue = FIVE
                6 -> cValue = SIX
                7 -> cValue = SEVEN
                8 -> cValue = EIGHT
                9 -> cValue = NINE
                10 -> cValue = TEN
                11 -> cValue = JACK
                12 -> cValue = QUEEN
                13 -> cValue = KING
                14 -> cValue = ACE

                else -> {
                }
            }
            return cValue
        }
    }
}