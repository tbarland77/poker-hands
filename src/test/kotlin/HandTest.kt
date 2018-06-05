import com.winterbe.expekt.expect
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class HandTest : Spek({
    describe("sort cards") {
        lateinit var playerHand: Array<Card>
        val rank = 0
        beforeEachTest {
            playerHand = arrayOf(
                    Card(Suit.HEARTS, CardValue.QUEEN),
                    Card(Suit.HEARTS, CardValue.FIVE),
                    Card(Suit.HEARTS, CardValue.KING),
                    Card(Suit.HEARTS, CardValue.TWO),
                    Card(Suit.HEARTS, CardValue.THREE)
            )
        }

        on("an unsorted player hand") {
            val hand = Hand(playerHand, rank)
            hand.sortCards(playerHand)
            for (i in 0..4) {
                println(playerHand[i])
            }
            it("should be sorted") {
                expect(playerHand[0].value).to.equal(CardValue.TWO)
            }
        }
    }
    describe("build poker hand") {
        on("creation of poker hand") {
            val hand = Hand(pokerHand = emptyArray(), rank = 0)
            it("should not be null") {
                for(i in 0..4) {
                    expect(hand.pokerHand[i]).not.to.equal(null)
                }
            }
        }
    }
})