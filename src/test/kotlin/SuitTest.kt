import com.winterbe.expekt.expect
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class SuitTest : Spek({
    var validSuit: Int
    var card: Card
    var denotedSuit: Char
    var fullSuitName: Suit

    beforeGroup {
        validSuit = 0
        card = Card(suit = Suit.CLUBS, value = CardValue.TWO)
        denotedSuit = 'C'
        fullSuitName = Suit.HEARTS

    }

    describe("get denoted suit") {
        card = Card(suit = Suit.CLUBS, value = CardValue.TWO)
        on("a denoted suit of C") {
            card.suit = Suit.SPADES
            validSuit = card.suit.getDenoteSuit()

            it("should equal CLUBS") {
                expect(validSuit).not.to.equal(-1)
            }
        }
    }

    describe("get Suit by denoted suit") {

        on("a denoted suit of C") {
            denotedSuit = 'C'
            fullSuitName = Suit.getSuitByDenoteSuit(denotedSuit)

            it("should equal CLUBS") {
                expect(fullSuitName).to.equal(Suit.CLUBS)
            }
        }

        on("a denoted suit of D") {
             denotedSuit = 'D'
             fullSuitName = Suit.getSuitByDenoteSuit(denotedSuit)

            it("should equal CLUBS") {
                expect(fullSuitName).to.equal(Suit.DIAMONDS)
            }
        }

        on("a denoted suit of H") {
             denotedSuit = 'H'
             fullSuitName = Suit.getSuitByDenoteSuit(denotedSuit)

            it("should equal HEARTS") {
                expect(fullSuitName).to.equal(Suit.HEARTS)
            }
        }

        on("a denoted suit of S") {
             denotedSuit = 'S'
             fullSuitName = Suit.getSuitByDenoteSuit(denotedSuit)

            it("should equal SPADES") {
                expect(fullSuitName).to.equal(Suit.SPADES)
            }
        }

        on("a denoted suit of unknown") {
             denotedSuit = 'V'
             fullSuitName = Suit.getSuitByDenoteSuit(denotedSuit)

            it("should default to CLUBS") {
                expect(fullSuitName).to.equal(Suit.CLUBS)
            }
        }
    }
})