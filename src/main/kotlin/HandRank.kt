enum class HandRank constructor(val ranking: Int) {
    HIGHCARD(1), PAIR(2), TWOPAIRS(3), THREEOFAKIND(4), STRAIGHT(5), FLUSH(6), FULLHOUSE(7), FOUROFAKIND(8), STRAIGHTFLUSH(9);


    companion object {

        fun getHandRankingById(id: Int): HandRank? {

            var handValue: HandRank? = null

            when (id) {
                1 -> handValue = HIGHCARD
                2 -> handValue = PAIR
                3 -> handValue = TWOPAIRS
                4 -> handValue = THREEOFAKIND
                5 -> handValue = STRAIGHT
                6 -> handValue = FLUSH
                7 -> handValue = FULLHOUSE
                8 -> handValue = FOUROFAKIND
                9 -> handValue = STRAIGHTFLUSH
                else -> {
                }
            }
            return handValue
        }
    }

}