import com.winterbe.expekt.expect
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

//TODO look into mockito or something similar for mocking possibly MockK?
class GameDeciderTest : Spek({
    describe("Game decider") {
        var player1: Player()
        var player2: Player()
        on("player1 has higher rank") {
            // set player1 has higher rank here

            it("should set is winner to true for player1") {
                expect(player1.isWinner).to.equal(true)
            }
        }
    }
})