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
    describe("Game decider compare same ranks") {
        on("player1 has higher winning card") {
            every { player1.hand?.rank } returns 7
            every { player2.hand?.rank } returns 7
            every { player1.hand?.winningCard } returns Card(suit = Suit.HEARTS, value = CardValue.QUEEN)
            every { player2.hand?.winningCard } returns Card(suit = Suit.HEARTS, value = CardValue.JACK)
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner to true for player1") {
                expect(player1.isWinner).to.equal(true)
            }
        }
        on("player2 has higher winning card") {
            every { player1.hand?.rank } returns 1
            every { player2.hand?.rank } returns 7
            every { player1.hand?.winningCard } returns Card(suit = Suit.HEARTS, value = CardValue.FIVE)
            every { player2.hand?.winningCard } returns Card(suit = Suit.HEARTS, value = CardValue.JACK)
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner to true for player2") {
                expect(player2.isWinner).to.equal(true)
            }
        }
    }
})