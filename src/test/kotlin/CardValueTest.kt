import com.winterbe.expekt.expect
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class CardValueTest : Spek({
    describe("get card numericValue by id") {

        var valueId: Int
        var cardValue: CardValue
        beforeEachTest {
            valueId = 0
            cardValue = CardValue.TWO
        }
        on("an id of 1") {
            valueId = 1
            cardValue = CardValue.getCardValueById(valueId)

            it("should equal ONE") {
                expect(cardValue).to.equal(CardValue.ONE)
            }
        }
        on("an id of 2") {
            valueId = 2
            cardValue = CardValue.getCardValueById(valueId)

            it("should equal TWO") {
                expect(cardValue).to.equal(CardValue.TWO)
            }
        }

        on("an id of 3") {
            valueId = 3
            cardValue = CardValue.getCardValueById(valueId)

            it("should equal THREE") {
                expect(cardValue).to.equal(CardValue.THREE)
            }
        }

        on("an id of 4") {
            valueId = 4
            cardValue = CardValue.getCardValueById(valueId)

            it("should equal FOUR") {
                expect(cardValue).to.equal(CardValue.FOUR)
            }
        }

        on("an id of 5") {
            valueId = 5
            cardValue = CardValue.getCardValueById(valueId)

            it("should equal FIVE") {
                expect(cardValue).to.equal(CardValue.FIVE)
            }
        }

        on("an id of 6") {
            valueId = 6
            cardValue = CardValue.getCardValueById(valueId)

            it("should equal SIX") {
                expect(cardValue).to.equal(CardValue.SIX)
            }
        }

        on("an id of 7") {
            valueId = 7
            cardValue = CardValue.getCardValueById(valueId)

            it("should equal SEVEN") {
                expect(cardValue).to.equal(CardValue.SEVEN)
            }
        }

        on("an id of 8") {
            valueId = 8
            cardValue = CardValue.getCardValueById(valueId)

            it("should equal EIGHT") {
                expect(cardValue).to.equal(CardValue.EIGHT)
            }
        }

        on("an id of 9") {
            valueId = 9
            cardValue = CardValue.getCardValueById(valueId)

            it("should equal NINE") {
                expect(cardValue).to.equal(CardValue.NINE)
            }
        }

        on("an id of 10") {
            valueId = 10
            cardValue = CardValue.getCardValueById(valueId)

            it("should equal TEN") {
                expect(cardValue).to.equal(CardValue.TEN)
            }
        }

        on("an id of 11") {
            valueId = 11
            cardValue = CardValue.getCardValueById(valueId)

            it("should equal JACK") {
                expect(cardValue).to.equal(CardValue.JACK)
            }
        }

        on("an id of 12") {
            valueId = 12
            cardValue = CardValue.getCardValueById(valueId)

            it("should equal Queen") {
                expect(cardValue).to.equal(CardValue.QUEEN)
            }
        }

        on("an id of 13") {
            valueId = 13
            cardValue = CardValue.getCardValueById(valueId)

            it("should equal KING") {
                expect(cardValue).to.equal(CardValue.KING)
            }
        }

        on("an id of 14") {
            valueId = 14
            cardValue = CardValue.getCardValueById(valueId)

            it("should equal ACE") {
                expect(cardValue).to.equal(CardValue.ACE)
            }
        }

        on("an id of invalid") {
            valueId = 999
            cardValue = CardValue.getCardValueById(valueId)

            it("should equal default value of two") {
                expect(cardValue).to.equal(CardValue.TWO)
            }
        }
    }
})