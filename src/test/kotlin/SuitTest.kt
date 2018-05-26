import com.winterbe.expekt.expect
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class SuitTest : Spek({
    describe("get Suit by denoted suit") {

        var denotedSuit: Char
        var fullSuitName: Suit?
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

            it("should equal SPADES") {
                expect(fullSuitName).to.equal(null)
            }
        }
    }
})