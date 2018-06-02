import com.winterbe.expekt.expect
import io.mockk.every
import io.mockk.spyk
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class GameDeciderTest : Spek({
    val player1 = spyk<Player>()
    val player2 = spyk<Player>()
    describe("Game decider compare hands") {
        on("player1 has higher rank") {
            every { player1.hand?.rank } returns 9
            every { player2.hand?.rank } returns 7
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner to true for player1") {
                expect(player1.isWinner).to.equal(true)
            }
        }
        on("player2 has higher rank") {
            every { player1.hand?.rank } returns 1
            every { player2.hand?.rank } returns 7
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner to true for player2") {
                expect(player2.isWinner).to.equal(true)
            }
        }
    }
    describe("hand high card tie") {
        val player1Hand : Array<Card?> =  arrayOf(
            Card(Suit.HEARTS, CardValue.KING),
            Card(Suit.DIAMONDS, CardValue.QUEEN),
            Card(Suit.HEARTS, CardValue.JACK),
            Card(Suit.CLUBS, CardValue.TEN),
            Card(Suit.HEARTS, CardValue.SEVEN)
        )
        val player2Hand : Array<Card?> = player1Hand.clone()
        player2Hand[4] =   Card(Suit.HEARTS, CardValue.FOUR)
        on("player1 has higher high card") {
            every { player1.hand?.rank } returns 1
            every { player1.winningCard } returns Card(Suit.HEARTS, CardValue.SEVEN)
            every { player2.hand?.rank } returns 1
            every { player1.hand?.pokerHand } returns player1Hand
            every { player2.hand?.pokerHand } returns player2Hand
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner to true for player1") {
                expect(player1.isWinner).to.equal(true)
                expect(player1.winningCard.value.numericValue).to.equal(7)
            }
        }
        on("player2 has higher high card") {
            player2Hand[4] =  Card(Suit.HEARTS, CardValue.EIGHT)
            every { player1.hand?.rank } returns 1
            every { player2.hand?.rank } returns 1
            every { player1.hand?.pokerHand } returns player1Hand
            every { player2.winningCard } returns Card(Suit.HEARTS, CardValue.EIGHT)
            every { player2.hand?.pokerHand } returns player2Hand
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner to true for player2") {
                expect(player2.isWinner).to.equal(true)
                expect(player2.winningCard.value.numericValue).to.equal(8)
            }
        }
        on("equal hands") {
            player2Hand[4] = player1Hand[4]
            every { player1.hand?.rank } returns 1
            every { player2.hand?.rank } returns 1
            every { player1.hand?.pokerHand } returns player1Hand
            every { player2.hand?.pokerHand } returns player2Hand
            GameDecider.compareHandRanks(player1, player2)
            it("should result in a tie") {
                expect(player1.isWinner).to.equal(false)
                expect(player1.isWinner).to.equal(false)
            }
        }
    }
})