import com.winterbe.expekt.expect
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class HandRankTest : Spek({
    describe("get hand rank by id") {
        var valueId: Int
        var handRank: HandRank

        beforeEachTest {
            valueId = 0
            handRank = HandRank.HIGHCARD
        }
        on("an id of 1") {
            valueId = 1
            handRank = HandRank.getHandRankingById(valueId)

            it("should equal HIGH CARD") {
                expect(handRank).to.equal(HandRank.HIGHCARD)
            }
        }

        on("an id of 2") {
            valueId = 2
            handRank = HandRank.getHandRankingById(valueId)

            it("should equal PAIR") {
                expect(handRank).to.equal(HandRank.PAIR)
            }
        }

        on("an id of 3") {
            valueId = 3
            handRank = HandRank.getHandRankingById(valueId)

            it("should equal TWO PAIRS") {
                expect(handRank).to.equal(HandRank.TWOPAIRS)
            }
        }

        on("an id of 4") {
            valueId = 4
            handRank = HandRank.getHandRankingById(valueId)

            it("should equal THREE OF A KIND") {
                expect(handRank).to.equal(HandRank.THREEOFAKIND)
            }
        }

        on("an id of 5") {
            valueId = 5
            handRank = HandRank.getHandRankingById(valueId)

            it("should equal STRAIGHT") {
                expect(handRank).to.equal(HandRank.STRAIGHT)
            }
        }

        on("an id of 6") {
            valueId = 6
            handRank = HandRank.getHandRankingById(valueId)

            it("should equal FLUSH") {
                expect(handRank).to.equal(HandRank.FLUSH)
            }
        }

        on("an id of 7") {
            valueId = 7
            handRank = HandRank.getHandRankingById(valueId)

            it("should equal FULL HOUSE") {
                expect(handRank).to.equal(HandRank.FULLHOUSE)
            }
        }

        on("an id of 8") {
            valueId = 8
            handRank = HandRank.getHandRankingById(valueId)

            it("should equal FOUR OF A KIND") {
                expect(handRank).to.equal(HandRank.FOUROFAKIND)
            }
        }

        on("an id of 9") {
            valueId = 9
            handRank = HandRank.getHandRankingById(valueId)

            it("should equal STRAIGHT FLUSH") {
                expect(handRank).to.equal(HandRank.STRAIGHTFLUSH)
            }
        }

        on("an id of invalid") {
            valueId = 99
            handRank = HandRank.getHandRankingById(valueId)

            it("should equal default value") {
                expect(handRank).to.equal(HandRank.HIGHCARD)
            }
        }
    }
})