import com.winterbe.expekt.expect
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class RankDeciderTest  : Spek({
    var cards: Array<Card>
    var expected: Boolean

    beforeGroup {
        cards = arrayOf(
                Card(Suit.HEARTS, CardValue.QUEEN),
                Card(Suit.SPADES, CardValue.FIVE),
                Card(Suit.DIAMONDS, CardValue.KING),
                Card(Suit.HEARTS, CardValue.TWO),
                Card(Suit.HEARTS, CardValue.TWO)
        )
        expected = false
    }
    describe("has a pair") {

        on("a hand with a pair") {
            cards = arrayOf(
                    Card(Suit.HEARTS, CardValue.QUEEN),
                    Card(Suit.SPADES, CardValue.FIVE),
                    Card(Suit.DIAMONDS, CardValue.KING),
                    Card(Suit.HEARTS, CardValue.TWO),
                    Card(Suit.HEARTS, CardValue.TWO)
            )
            it("should return true") {
                expected = true
                expect(RankDecider.hasAPair(cards)).to.equal(expected)
            }
        }
        on("a hand with no pair") {
            cards = arrayOf(
                    Card(Suit.HEARTS, CardValue.QUEEN),
                    Card(Suit.SPADES, CardValue.FIVE),
                    Card(Suit.DIAMONDS, CardValue.KING),
                    Card(Suit.HEARTS, CardValue.THREE),
                    Card(Suit.HEARTS, CardValue.TWO)
            )
            it("should return false") {
                expected = false
                expect(RankDecider.hasAPair(cards)).to.equal(expected)
            }
        }
    }
    describe("has two pair") {

        on("a hand with two pairs") {
            cards = arrayOf(
                    Card(Suit.HEARTS, CardValue.QUEEN),
                    Card(Suit.SPADES, CardValue.THREE),
                    Card(Suit.SPADES, CardValue.THREE),
                    Card(Suit.HEARTS, CardValue.TWO),
                    Card(Suit.HEARTS, CardValue.TWO)
            )
            it("should return true") {
                expected = true
                expect(RankDecider.hasTwoPair(cards)).to.equal(expected)
            }
        }
        on("a hand with no pairs") {
            cards = arrayOf(
                    Card(Suit.HEARTS, CardValue.QUEEN),
                    Card(Suit.SPADES, CardValue.FIVE),
                    Card(Suit.DIAMONDS, CardValue.KING),
                    Card(Suit.HEARTS, CardValue.THREE),
                    Card(Suit.HEARTS, CardValue.TWO)
            )
            it("should return false") {
                expected = false
                expect(RankDecider.hasTwoPair(cards)).to.equal(expected)
            }
        }
    }

    describe("has three of a kind") {


        on("a hand with three of a kind") {
            cards = arrayOf(
                    Card(Suit.HEARTS, CardValue.QUEEN),
                    Card(Suit.SPADES, CardValue.THREE),
                    Card(Suit.HEARTS, CardValue.TWO),
                    Card(Suit.HEARTS, CardValue.TWO),
                    Card(Suit.HEARTS, CardValue.TWO)
            )
            it("should return true") {
                expected = true
                expect(RankDecider.hasThreeOfAKind(cards)).to.equal(expected)
            }
        }
        on("a hand without three of a kind") {
            cards = arrayOf(
                    Card(Suit.HEARTS, CardValue.QUEEN),
                    Card(Suit.SPADES, CardValue.FIVE),
                    Card(Suit.DIAMONDS, CardValue.KING),
                    Card(Suit.HEARTS, CardValue.THREE),
                    Card(Suit.HEARTS, CardValue.TWO)
            )
            it("should return false") {
                expected = false
                expect(RankDecider.hasThreeOfAKind(cards)).to.equal(expected)
            }
        }
    }

    describe("has a straight") {
        on("a hand with a straight") {
            cards = arrayOf(
                    Card(Suit.DIAMONDS, CardValue.SIX),
                    Card(Suit.SPADES, CardValue.SEVEN),
                    Card(Suit.HEARTS, CardValue.EIGHT),
                    Card(Suit.HEARTS, CardValue.NINE),
                    Card(Suit.HEARTS, CardValue.TEN)
            )
            it("should return true") {
                expected = true
                expect(RankDecider.hasStraight(cards)).to.equal(expected)
            }
        }
        on("a hand with no straight") {
            cards = arrayOf(
                    Card(Suit.HEARTS, CardValue.QUEEN),
                    Card(Suit.SPADES, CardValue.FIVE),
                    Card(Suit.DIAMONDS, CardValue.KING),
                    Card(Suit.HEARTS, CardValue.THREE),
                    Card(Suit.HEARTS, CardValue.TWO)
            )
            it("should return false") {
                expected = false
                expect(RankDecider.hasStraight(cards)).to.equal(expected)
            }
        }
    }

    describe("has a flush") {
        on("a hand with a flush") {
            cards = arrayOf(
                    Card(Suit.HEARTS, CardValue.NINE),
                    Card(Suit.HEARTS, CardValue.JACK),
                    Card(Suit.HEARTS, CardValue.EIGHT),
                    Card(Suit.HEARTS, CardValue.NINE),
                    Card(Suit.HEARTS, CardValue.TEN)
            )
            it("should return true") {
                expected = true
                expect(RankDecider.hasFlush(cards)).to.equal(expected)
            }
        }
        on("a hand with no flush") {
            cards = arrayOf(
                    Card(Suit.HEARTS, CardValue.QUEEN),
                    Card(Suit.SPADES, CardValue.FIVE),
                    Card(Suit.DIAMONDS, CardValue.KING),
                    Card(Suit.HEARTS, CardValue.THREE),
                    Card(Suit.HEARTS, CardValue.TWO)
            )
            it("should return false") {
                expected = false
                expect(RankDecider.hasFlush(cards)).to.equal(expected)
            }
        }
    }

    describe("has a full house") {
        on("a hand with a full house") {
            cards = arrayOf(
                    Card(Suit.HEARTS, CardValue.NINE),
                    Card(Suit.DIAMONDS, CardValue.NINE),
                    Card(Suit.SPADES, CardValue.EIGHT),
                    Card(Suit.HEARTS, CardValue.EIGHT),
                    Card(Suit.HEARTS, CardValue.EIGHT)
            )
            it("should return true") {
                expected = true
                expect(RankDecider.hasFullHouse(cards)).to.equal(expected)
            }
        }
        on("a hand with no full house") {
            cards = arrayOf(
                    Card(Suit.HEARTS, CardValue.QUEEN),
                    Card(Suit.SPADES, CardValue.FIVE),
                    Card(Suit.DIAMONDS, CardValue.KING),
                    Card(Suit.HEARTS, CardValue.THREE),
                    Card(Suit.HEARTS, CardValue.TWO)
            )
            it("should return false") {
                expected = false
                expect(RankDecider.hasFlush(cards)).to.equal(expected)
            }
        }
        on("a hand with only a pair") {
            cards = arrayOf(
                    Card(Suit.HEARTS, CardValue.QUEEN),
                    Card(Suit.SPADES, CardValue.QUEEN),
                    Card(Suit.DIAMONDS, CardValue.KING),
                    Card(Suit.HEARTS, CardValue.THREE),
                    Card(Suit.HEARTS, CardValue.TWO)
            )
            it("should return false") {
                expected = false
                expect(RankDecider.hasFlush(cards)).to.equal(expected)
            }
        }

        on("a hand with only three of a kind") {
            cards = arrayOf(
                    Card(Suit.HEARTS, CardValue.QUEEN),
                    Card(Suit.SPADES, CardValue.QUEEN),
                    Card(Suit.DIAMONDS, CardValue.QUEEN),
                    Card(Suit.HEARTS, CardValue.THREE),
                    Card(Suit.HEARTS, CardValue.TWO)
            )
            it("should return false") {
                expected = false
                expect(RankDecider.hasFlush(cards)).to.equal(expected)
            }
        }
    }

    describe("has four of a kind") {
        on("a hand with four of a kind") {
            cards = arrayOf(
                    Card(Suit.HEARTS, CardValue.NINE),
                    Card(Suit.HEARTS, CardValue.TEN),
                    Card(Suit.HEARTS, CardValue.TEN),
                    Card(Suit.HEARTS, CardValue.TEN),
                    Card(Suit.HEARTS, CardValue.TEN)
            )
            it("should return true") {
                expected = true
                expect(RankDecider.hasFourOfAKind(cards)).to.equal(expected)
            }
        }
        on("a hand with no four of a kind") {
            cards = arrayOf(
                    Card(Suit.HEARTS, CardValue.QUEEN),
                    Card(Suit.SPADES, CardValue.FIVE),
                    Card(Suit.DIAMONDS, CardValue.KING),
                    Card(Suit.HEARTS, CardValue.THREE),
                    Card(Suit.HEARTS, CardValue.TWO)
            )
            it("should return false") {
                expected = false
                expect(RankDecider.hasFourOfAKind(cards)).to.equal(expected)
            }
        }
        on("a hand with a full house") {
            cards = arrayOf(
                    Card(Suit.HEARTS, CardValue.QUEEN),
                    Card(Suit.SPADES, CardValue.QUEEN),
                    Card(Suit.DIAMONDS, CardValue.KING),
                    Card(Suit.HEARTS, CardValue.KING),
                    Card(Suit.HEARTS, CardValue.QUEEN)
            )
            it("should return false") {
                expected = false
                expect(RankDecider.hasFourOfAKind(cards)).to.equal(expected)
            }
        }
    }

    describe("has a straight flush") {
        on("a hand with a straight flush") {
            cards = arrayOf(
                    Card(Suit.HEARTS, CardValue.NINE),
                    Card(Suit.HEARTS, CardValue.TEN),
                    Card(Suit.HEARTS, CardValue.JACK),
                    Card(Suit.HEARTS, CardValue.QUEEN),
                    Card(Suit.HEARTS, CardValue.KING)
            )
            it("should return true") {
                expected = true
                expect(RankDecider.hasStraightFlush(cards)).to.equal(expected)
            }
        }
        on("a hand with no straight flush") {
            cards = arrayOf(
                    Card(Suit.HEARTS, CardValue.QUEEN),
                    Card(Suit.SPADES, CardValue.FIVE),
                    Card(Suit.DIAMONDS, CardValue.KING),
                    Card(Suit.HEARTS, CardValue.THREE),
                    Card(Suit.HEARTS, CardValue.TWO)
            )
            it("should return false") {
                expected = false
                expect(RankDecider.hasStraightFlush(cards)).to.equal(expected)
            }
        }
        on("a hand with only a straight") {
            cards = arrayOf(
                    Card(Suit.HEARTS, CardValue.EIGHT),
                    Card(Suit.SPADES, CardValue.NINE),
                    Card(Suit.DIAMONDS, CardValue.TEN),
                    Card(Suit.HEARTS, CardValue.JACK),
                    Card(Suit.HEARTS, CardValue.QUEEN)
            )
            it("should return false") {
                expected = false
                expect(RankDecider.hasStraightFlush(cards)).to.equal(expected)
            }
        }
        on("a hand with only a flush") {
            cards = arrayOf(
                    Card(Suit.HEARTS, CardValue.EIGHT),
                    Card(Suit.HEARTS, CardValue.ACE),
                    Card(Suit.HEARTS, CardValue.THREE),
                    Card(Suit.HEARTS, CardValue.KING),
                    Card(Suit.HEARTS, CardValue.QUEEN)
            )
            it("should return false") {
                expected = false
                expect(RankDecider.hasStraightFlush(cards)).to.equal(expected)
            }
        }
    }
})