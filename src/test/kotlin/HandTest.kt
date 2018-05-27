import com.winterbe.expekt.expect
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class HandTest : Spek({
    describe("sort cards") {

        val playerHand: Array<Card?> =
                arrayOf(
                        Card(Suit.HEARTS, CardValue.QUEEN),
                        Card(Suit.HEARTS, CardValue.FIVE),
                        Card(Suit.HEARTS, CardValue.KING),
                        Card(Suit.HEARTS, CardValue.TWO),
                        Card(Suit.HEARTS, CardValue.THREE)
                )
        val rank = 9
        val winningCard = playerHand[1]

        on("an unsorted player hand") {
            val hand = Hand(playerHand, rank, winningCard)
            hand.sortCards(playerHand)
            for (i in 0..4) {
                println(playerHand[i])
            }
            it("should be sorted") {
                expect(playerHand[0]?.value).to.equal(CardValue.TWO)
            }
        }
    }
})