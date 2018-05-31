object RankDecider {
        fun hasAPair(cards: Array<Card>): Boolean {
            val value = IntArray(5)
            for (i in cards.indices) {
                val card = cards[i]
                value[i] = card.value.numericValue
            }
            val distinctValues = value.distinct()
            return distinctValues.size == 4 || distinctValues.size == 2
        }

        fun hasTwoPair(cards: Array<Card>): Boolean {
            val value = IntArray(5)
            for (i in cards.indices) {
                val card = cards[i]
                value[i] = card.value.numericValue
            }
            val distinctValues = value.distinct()
            return distinctValues.size == 3
        }

        fun hasThreeOfAKind(cards: Array<Card>): Boolean {
            val value = IntArray(5)
            var counter = 0
            for (i in cards.indices) {
                val card = cards[i]
                value[i] = card.value.numericValue
            }

            for (i in cards.indices) {
                for (j in i + 1 until cards.size) {
                    if (value[i] == value[j]) {
                        counter++
                    }
                    if (j == cards.size - 1 && counter != 2) {
                        counter = 0 //start again
                    } else if (j == cards.size - 1 && counter >= 2) {
                        return true
                    }
                }
            }
            return false
        }

        fun hasStraight(cards: Array<Card>): Boolean {
            var id: Int
            var cardValue: Int
            for (i in 0 until cards.size - 1) {
                val card = cards[i]
                val nextCard = cards[i + 1]
                cardValue = card.value.numericValue
                id = cardValue + 1
                if (id != nextCard.value.numericValue) {
                    return false
                }
            }
            return true
        }

        fun hasFlush(cards: Array<Card>): Boolean {
            val uniqueSuits = cards.associateBy({ it.suit }, {it.suit.getDenoteSuit()})
            return uniqueSuits.size == 1
        }

        fun hasFullHouse(cards: Array<Card>): Boolean {
            return hasAPair(cards) && hasThreeOfAKind(cards)
        }

        fun hasFourOfAKind(cards: Array<Card>): Boolean {
            val cardValuesMap = cards.groupingBy { it.value.numericValue }.eachCount()
            for (card in cardValuesMap) {
                if (card.value == 4) {
                    return true
                }
            }
            return false
        }

        fun hasStraightFlush(cards: Array<Card>): Boolean {
            return hasFlush(cards) && hasStraight(cards)
        }
    }