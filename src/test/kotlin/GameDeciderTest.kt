import com.winterbe.expekt.expect
import io.mockk.every
import io.mockk.spyk
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class GameDeciderTest : Spek({
    describe("Game decider") {

        val player1 = spyk<Player>()
        val player2 = spyk<Player>()
        on("player1 has higher rank") {
            every { player1.hand?.rank } returns 9
            every { player2.hand?.rank } returns 7
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner to true for player1") {
                expect(player1.isWinner).to.equal(true)
            }
        }
    }
})