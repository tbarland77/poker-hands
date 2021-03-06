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
    var player1Hand : Array<Card> = emptyArray()
    var player2Hand : Array<Card> = emptyArray()
    beforeGroup {
        player1Hand  =  arrayOf(
                Card(Suit.HEARTS, CardValue.KING),
                Card(Suit.DIAMONDS, CardValue.QUEEN),
                Card(Suit.HEARTS, CardValue.JACK),
                Card(Suit.CLUBS, CardValue.TEN),
                Card(Suit.HEARTS, CardValue.SEVEN)
        )
        player2Hand = player1Hand.clone()

    }
    describe("Game decider compare hands") {
        on("player1 has higher rank") {
            every { player1.hand.rank } returns 9
            every { player2.hand.rank } returns 7
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner to true for player1") {
                expect(player1.isWinner).to.equal(true)
            }
        }
        on("player2 has higher rank") {
            every { player1.hand.rank } returns 1
            every { player2.hand.rank } returns 7
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner to true for player2") {
                expect(player2.isWinner).to.equal(true)
            }
        }
    }
    describe("hand high card tie") {
            player1Hand =  arrayOf(
            Card(Suit.HEARTS, CardValue.KING),
            Card(Suit.DIAMONDS, CardValue.QUEEN),
            Card(Suit.HEARTS, CardValue.JACK),
            Card(Suit.CLUBS, CardValue.TEN),
            Card(Suit.HEARTS, CardValue.SEVEN)
        )
        player2Hand = player1Hand.clone()
        on("player1 has higher high card") {
            player2Hand[4] =   Card(Suit.HEARTS, CardValue.FOUR)
            every { player1.hand.rank } returns 1
            every { player1.winningCard } returns CardValue.SEVEN
            every { player2.hand.rank } returns 1
            every { player1.hand.pokerHand } returns player1Hand
            every { player2.hand.pokerHand } returns player2Hand
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner to true for player1") {
                expect(player1.isWinner).to.equal(true)
                expect(player1.winningCard.numericValue).to.equal(7)
            }
        }
        on("player2 has higher high card") {
            player2Hand[4] =  Card(Suit.HEARTS, CardValue.EIGHT)
            every { player1.hand.rank } returns 1
            every { player2.hand.rank } returns 1
            every { player1.hand.pokerHand } returns player1Hand
            every { player2.winningCard } returns CardValue.EIGHT
            every { player2.hand.pokerHand } returns player2Hand
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner to true for player2") {
                expect(player2.isWinner).to.equal(true)
                expect(player2.winningCard.numericValue).to.equal(8)
            }
        }
        on("equal hands") {
            player2Hand[4] = player1Hand[4]
            every { player1.hand.rank } returns 1
            every { player2.hand.rank } returns 1
            every { player1.hand.pokerHand } returns player1Hand
            every { player2.hand.pokerHand } returns player2Hand
            GameDecider.compareHandRanks(player1, player2)
            it("should result in a tie") {
                expect(player1.isWinner).to.equal(false)
                expect(player1.isWinner).to.equal(false)
            }
        }
    }
    describe("pair card tie") {

        beforeEachTest {
            player1Hand =  arrayOf(
                    Card(Suit.HEARTS, CardValue.KING),
                    Card(Suit.DIAMONDS, CardValue.JACK),
                    Card(Suit.HEARTS, CardValue.JACK),
                    Card(Suit.CLUBS, CardValue.FIVE),
                    Card(Suit.HEARTS, CardValue.THREE)
            )
            player2Hand =  arrayOf(
                    Card(Suit.HEARTS, CardValue.QUEEN),
                    Card(Suit.DIAMONDS, CardValue.TEN),
                    Card(Suit.HEARTS, CardValue.TEN),
                    Card(Suit.CLUBS, CardValue.FOUR),
                    Card(Suit.HEARTS, CardValue.THREE)
            )
        }
        on("player1 has higher pair") {
            every { player1.hand.rank } returns 2
            every { player1.winningCard } returns CardValue.JACK
            every { player2.hand.rank } returns 2
            every { player1.hand.pokerHand } returns player1Hand
            every { player2.hand.pokerHand } returns player2Hand
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner to true for player1") {
                expect(player1.isWinner).to.equal(true)
                expect(player1.winningCard.numericValue).to.equal(11)
            }
        }
        on("player2 has higher pair") {
            player2Hand[1] =  Card(Suit.HEARTS, CardValue.KING)
            player2Hand[2] =  Card(Suit.DIAMONDS, CardValue.KING)
            every { player1.hand.rank } returns 2
            every { player2.hand.rank } returns 2
            every { player1.hand.pokerHand } returns player1Hand
            every { player2.winningCard } returns CardValue.KING
            every { player2.hand.pokerHand } returns player2Hand
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner to true for player2") {
                expect(player2.isWinner).to.equal(true)
                expect(player2.winningCard.numericValue).to.equal(13)
            }
        }
        on("equal pairs with player 1 having a higher side card") {
            player2Hand[1] =  player1Hand[1]
            player2Hand[2] =  player1Hand[2]
            every { player1.hand.rank } returns 2
            every { player2.hand.rank } returns 2
            every { player1.hand.pokerHand } returns player1Hand
            every { player2.hand.pokerHand } returns player2Hand
            every { player1.winningCard } returns CardValue.KING
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner true for player1") {
                expect(player1.isWinner).to.equal(true)
                expect(player1.winningCard.numericValue).to.equal(13)
            }
        }
        on("equal pairs with player 2 having a higher side card") {
            player1Hand[0] = Card(Suit.HEARTS, CardValue.QUEEN)
            player2Hand[0] =  Card(Suit.HEARTS, CardValue.KING)
            player2Hand[1] =  player1Hand[1]
            player2Hand[2] =  player1Hand[2]
            every { player1.hand.rank } returns 2
            every { player2.hand.rank } returns 2
            every { player1.hand.pokerHand } returns player1Hand
            every { player2.hand.pokerHand } returns player2Hand
            every { player2.winningCard } returns CardValue.KING
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner true for player2") {
                expect(player2.isWinner).to.equal(true)
                expect(player2.winningCard.numericValue).to.equal(13)
            }
        }
        on("equal pairs with both players having the same hand") {
            for (i in 0..4) {
                player2Hand[i] = player1Hand[i]
            }
            every { player1.hand.rank } returns 2
            every { player2.hand.rank } returns 2
            every { player1.hand.pokerHand } returns player1Hand
            every { player2.hand.pokerHand } returns player2Hand
            GameDecider.compareHandRanks(player1, player2)
            it("should result in a tie") {
                expect(player1.isWinner).to.equal(false)
                expect(player2.isWinner).to.equal(false)
            }
        }
    }
    describe("two pair card tie") {

        beforeEachTest {
            player1Hand  =  arrayOf(
                    Card(Suit.HEARTS, CardValue.KING),
                    Card(Suit.DIAMONDS, CardValue.TEN),
                    Card(Suit.HEARTS, CardValue.TEN),
                    Card(Suit.CLUBS, CardValue.NINE),
                    Card(Suit.HEARTS, CardValue.NINE)
            )
            player2Hand =  arrayOf(
                    Card(Suit.HEARTS, CardValue.QUEEN),
                    Card(Suit.DIAMONDS, CardValue.SEVEN),
                    Card(Suit.HEARTS, CardValue.SEVEN),
                    Card(Suit.CLUBS, CardValue.FOUR),
                    Card(Suit.HEARTS, CardValue.FOUR)
            )
        }
        on("player1 has higher first pair") {
            every { player1.hand.rank } returns 3
            every { player1.winningCard } returns CardValue.TEN
            every { player2.hand.rank } returns 3
            every { player1.hand.pokerHand } returns player1Hand
            every { player2.hand.pokerHand } returns player2Hand
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner to true for player1") {
                expect(player1.isWinner).to.equal(true)
                expect(player1.winningCard.numericValue).to.equal(10)
            }
        }
        on("player2 has higher first pair") {
            player2Hand[1] =  Card(Suit.HEARTS, CardValue.KING)
            player2Hand[2] =  Card(Suit.DIAMONDS, CardValue.KING)
            every { player1.hand.rank } returns 3
            every { player2.hand.rank } returns 3
            every { player1.hand.pokerHand } returns player1Hand
            every { player2.winningCard } returns CardValue.KING
            every { player2.hand.pokerHand } returns player2Hand
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner to true for player2") {
                expect(player2.isWinner).to.equal(true)
                expect(player2.winningCard.numericValue).to.equal(13)
            }
        }
        on("equal first pairs with player 1 having a higher second pair") {
            player2Hand[1] =  player1Hand[1]
            player2Hand[2] =  player1Hand[2]
            every { player1.hand.rank } returns 3
            every { player2.hand.rank } returns 3
            every { player1.hand.pokerHand } returns player1Hand
            every { player2.hand.pokerHand } returns player2Hand
            every { player1.winningCard } returns CardValue.NINE
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner true for player1") {
                expect(player1.isWinner).to.equal(true)
                expect(player1.winningCard.numericValue).to.equal(9)
            }
        }
        on("equal first pairs with player 2 having a higher second pair") {
            player2Hand[1] =  player1Hand[1]
            player2Hand[2] =  player1Hand[2]
            player2Hand[3] =  Card(Suit.HEARTS, CardValue.FIVE)
            player2Hand[4] =  Card(Suit.DIAMONDS, CardValue.FIVE)
            player1Hand[3] =  Card(Suit.HEARTS, CardValue.THREE)
            player1Hand[4] =  Card(Suit.DIAMONDS, CardValue.THREE)
            every { player1.hand.rank } returns 3
            every { player2.hand.rank } returns 3
            every { player1.hand.pokerHand } returns player1Hand
            every { player2.hand.pokerHand } returns player2Hand
            every { player2.winningCard } returns CardValue.TEN
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner true for player2") {
                expect(player2.isWinner).to.equal(true)
                expect(player2.winningCard.numericValue).to.equal(10)
            }
        }
        on("equal pairs with player 1 having a higher side card") {
            for (i in 1..4) {
                player2Hand[i] = player1Hand[i]
            }
            every { player1.hand.rank } returns 3
            every { player2.hand.rank } returns 3
            every { player1.hand.pokerHand } returns player1Hand
            every { player2.hand.pokerHand } returns player2Hand
            every { player1.winningCard } returns CardValue.KING
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner true for player1") {
                expect(player1.isWinner).to.equal(true)
                expect(player1.winningCard.numericValue).to.equal(13)
            }
        }
        on("equal pairs with player 2 having a higher side card") {
            for (i in 1..4) {
                player2Hand[i] = player1Hand[i]
            }
            player2Hand[0] = Card(Suit.HEARTS, CardValue.KING)
            player1Hand[0] = Card(Suit.HEARTS, CardValue.QUEEN)
            every { player1.hand.rank } returns 3
            every { player2.hand.rank } returns 3
            every { player1.hand.pokerHand } returns player1Hand
            every { player2.hand.pokerHand } returns player2Hand
            every { player2.winningCard } returns CardValue.KING
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner true for player2") {
                expect(player2.isWinner).to.equal(true)
                expect(player2.winningCard.numericValue).to.equal(13)
            }
        }
        on("equal two pairs with both players having the same hand") {
            for (i in 0..4) {
                player2Hand[i] = player1Hand[i]
            }
            every { player1.hand.rank } returns 3
            every { player2.hand.rank } returns 3
            every { player1.hand.pokerHand } returns player1Hand
            every { player2.hand.pokerHand } returns player2Hand
            GameDecider.compareHandRanks(player1, player2)
            it("should result in a tie") {
                expect(player1.isWinner).to.equal(false)
                expect(player2.isWinner).to.equal(false)
            }
        }
    }
    describe("three of a kind tie") {

        beforeEachTest {
            player1Hand =  arrayOf(
                    Card(Suit.HEARTS, CardValue.TEN),
                    Card(Suit.DIAMONDS, CardValue.TEN),
                    Card(Suit.HEARTS, CardValue.TEN),
                    Card(Suit.CLUBS, CardValue.SEVEN),
                    Card(Suit.HEARTS, CardValue.SIX)
            )
            player2Hand =  arrayOf(
                    Card(Suit.HEARTS, CardValue.NINE),
                    Card(Suit.DIAMONDS, CardValue.NINE),
                    Card(Suit.HEARTS, CardValue.NINE),
                    Card(Suit.CLUBS, CardValue.EIGHT),
                    Card(Suit.HEARTS, CardValue.SEVEN)
            )
        }
        on("player1 has higher three of a kind") {
            every { player1.hand.rank } returns 4
            every { player2.hand.rank } returns 4
            every { player1.winningCard } returns CardValue.TEN
            every { player1.hand.pokerHand } returns player1Hand
            every { player2.hand.pokerHand } returns player2Hand
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner to true for player1") {
                expect(player1.isWinner).to.equal(true)
                expect(player1.winningCard.numericValue).to.equal(10)
            }
        }
        on("player2 has higher three of a kind") {
            player2Hand[0] =  Card(Suit.HEARTS, CardValue.KING)
            player2Hand[1] =  Card(Suit.HEARTS, CardValue.KING)
            player2Hand[2] =  Card(Suit.DIAMONDS, CardValue.KING)
            every { player1.hand.rank } returns 4
            every { player2.hand.rank } returns 4
            every { player1.hand.pokerHand } returns player1Hand
            every { player2.winningCard } returns CardValue.KING
            every { player2.hand.pokerHand } returns player2Hand
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner to true for player2") {
                expect(player2.isWinner).to.equal(true)
                expect(player2.winningCard.numericValue).to.equal(13)
            }
        }
        on("equal three of a kinds with player 1 having a higher side card") {
            for (i in 0..2) {
                player2Hand[i] = player1Hand[i]
            }
            player1Hand[3] = Card(Suit.DIAMONDS, CardValue.NINE)
            every { player1.hand.rank } returns 4
            every { player2.hand.rank } returns 4
            every { player1.hand.pokerHand } returns player1Hand
            every { player2.hand.pokerHand } returns player2Hand
            every { player1.winningCard } returns CardValue.NINE
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner true for player1") {
                expect(player1.isWinner).to.equal(true)
                expect(player1.winningCard.numericValue).to.equal(9)
            }
        }
        on("equal three of a kinds with player 2 having a higher side card") {
            for (i in 0..2) {
                player2Hand[i] = player1Hand[i]
            }
            player2Hand[3] = Card(Suit.DIAMONDS, CardValue.JACK)
            every { player1.hand.rank } returns 4
            every { player2.hand.rank } returns 4
            every { player1.hand.pokerHand } returns player1Hand
            every { player2.hand.pokerHand } returns player2Hand
            every { player2.winningCard } returns CardValue.EIGHT
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner true for player2") {
                expect(player2.isWinner).to.equal(true)
                expect(player2.winningCard.numericValue).to.equal(8)
            }
        }
        on("equal three of a kinds with both players having the same hand") {
            for (i in 0..4) {
                player2Hand[i] = player1Hand[i]
            }
            every { player1.hand.rank } returns 4
            every { player2.hand.rank } returns 4
            every { player1.hand.pokerHand } returns player1Hand
            every { player2.hand.pokerHand } returns player2Hand
            GameDecider.compareHandRanks(player1, player2)
            it("should result in a tie") {
                expect(player1.isWinner).to.equal(false)
                expect(player2.isWinner).to.equal(false)
            }
        }
    }
    describe("straight tie") {
        beforeEachTest {
            player1Hand =  arrayOf(
                    Card(Suit.HEARTS, CardValue.ACE),
                    Card(Suit.DIAMONDS, CardValue.KING),
                    Card(Suit.HEARTS, CardValue.QUEEN),
                    Card(Suit.CLUBS, CardValue.JACK),
                    Card(Suit.HEARTS, CardValue.TEN)
            )
            player2Hand =  arrayOf(
                    Card(Suit.HEARTS, CardValue.KING),
                    Card(Suit.DIAMONDS, CardValue.QUEEN),
                    Card(Suit.HEARTS, CardValue.JACK),
                    Card(Suit.CLUBS, CardValue.TEN),
                    Card(Suit.HEARTS, CardValue.NINE)
            )
        }
        on("player1 has high ace") {
            every { player1.hand.rank } returns 5
            every { player2.hand.rank } returns 5
            every { player1.winningCard } returns CardValue.ACE
            every { player1.hand.pokerHand } returns player1Hand
            every { player2.hand.pokerHand } returns player2Hand
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner to true for player1") {
                expect(player1.isWinner).to.equal(true)
                expect(player1.winningCard.numericValue).to.equal(14)
            }
        }
        on("player2 has high ace") {
            every { player1.hand.rank } returns 5
            every { player2.hand.rank } returns 5
            every { player2.winningCard } returns CardValue.ACE
            every { player1.hand.pokerHand } returns player2Hand
            every { player2.hand.pokerHand } returns player1Hand
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner to true for player2") {
                expect(player2.isWinner).to.equal(true)
                expect(player2.winningCard.numericValue).to.equal(14)
            }
        }
        on("both players have high ace") {
            every { player1.hand.rank } returns 5
            every { player2.hand.rank } returns 5
            every { player2.winningCard } returns CardValue.ACE
            every { player1.hand.pokerHand } returns player1Hand
            every { player2.hand.pokerHand } returns player1Hand
            GameDecider.compareHandRanks(player1, player2)
            it("should result in a tie") {
                expect(player1.isWinner).to.equal(false)
                expect(player2.isWinner).to.equal(false)
            }
        }
        on("player1 has higher straight") {
            player1Hand =  arrayOf(
                    Card(Suit.HEARTS, CardValue.KING),
                    Card(Suit.DIAMONDS, CardValue.QUEEN),
                    Card(Suit.HEARTS, CardValue.JACK),
                    Card(Suit.CLUBS, CardValue.TEN),
                    Card(Suit.HEARTS, CardValue.NINE)
            )
            player2Hand =  arrayOf(
                    Card(Suit.HEARTS, CardValue.TEN),
                    Card(Suit.DIAMONDS, CardValue.NINE),
                    Card(Suit.HEARTS, CardValue.EIGHT),
                    Card(Suit.CLUBS, CardValue.SEVEN),
                    Card(Suit.HEARTS, CardValue.SIX)
            )
            every { player1.hand.rank } returns 5
            every { player2.hand.rank } returns 5
            every { player1.winningCard } returns CardValue.KING
            every { player1.hand.pokerHand } returns player1Hand
            every { player2.hand.pokerHand } returns player2Hand
            GameDecider.compareHandRanks(player1, player2)
            it("should set player1 is winner to true") {
                expect(player1.isWinner).to.equal(true)
                expect(player1.winningCard.numericValue).to.equal(13)
            }
        }
        on("player1 has higher straight") {
            player1Hand =  arrayOf(
                    Card(Suit.HEARTS, CardValue.KING),
                    Card(Suit.DIAMONDS, CardValue.QUEEN),
                    Card(Suit.HEARTS, CardValue.JACK),
                    Card(Suit.CLUBS, CardValue.TEN),
                    Card(Suit.HEARTS, CardValue.NINE)
            )
            player2Hand =  arrayOf(
                    Card(Suit.HEARTS, CardValue.TEN),
                    Card(Suit.DIAMONDS, CardValue.NINE),
                    Card(Suit.HEARTS, CardValue.EIGHT),
                    Card(Suit.CLUBS, CardValue.SEVEN),
                    Card(Suit.HEARTS, CardValue.SIX)
            )
            every { player1.hand.rank } returns 5
            every { player2.hand.rank } returns 5
            every { player2.winningCard } returns CardValue.KING
            every { player1.hand.pokerHand } returns player2Hand
            every { player2.hand.pokerHand } returns player1Hand
            GameDecider.compareHandRanks(player1, player2)
            it("should set player1 is winner to true") {
                expect(player2.isWinner).to.equal(true)
                expect(player2.winningCard.numericValue).to.equal(13)
            }
        }
    }
    describe("full house") {
        beforeEachTest {
            player1Hand =  arrayOf(
                    Card(Suit.HEARTS, CardValue.KING),
                    Card(Suit.DIAMONDS, CardValue.KING),
                    Card(Suit.HEARTS, CardValue.KING),
                    Card(Suit.CLUBS, CardValue.JACK),
                    Card(Suit.HEARTS, CardValue.JACK)
            )
            player2Hand =  arrayOf(
                    Card(Suit.HEARTS, CardValue.QUEEN),
                    Card(Suit.DIAMONDS, CardValue.QUEEN),
                    Card(Suit.HEARTS, CardValue.QUEEN),
                    Card(Suit.CLUBS, CardValue.TEN),
                    Card(Suit.HEARTS, CardValue.TEN)
            )
        }
        on("player1 has higher full house three of a kind") {
            every { player1.hand.rank } returns 7
            every { player2.hand.rank } returns 7
            every { player1.winningCard } returns CardValue.KING
            every { player1.hand.pokerHand } returns player1Hand
            every { player2.hand.pokerHand } returns player2Hand
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner to true for player1") {
                expect(player1.isWinner).to.equal(true)
                expect(player1.winningCard.numericValue).to.equal(13)
            }
        }
        on("player2 has higher full house three of a kind") {
            every { player1.hand.rank } returns 7
            every { player2.hand.rank } returns 7
            every { player2.winningCard } returns CardValue.KING
            every { player1.hand.pokerHand } returns player2Hand
            every { player2.hand.pokerHand } returns player1Hand
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner to true for player2") {
                expect(player2.isWinner).to.equal(true)
                expect(player2.winningCard.numericValue).to.equal(13)
            }
        }
        on("player1 has higher full house pair") {
            player1Hand =  arrayOf(
                    Card(Suit.HEARTS, CardValue.KING),
                    Card(Suit.DIAMONDS, CardValue.KING),
                    Card(Suit.HEARTS, CardValue.KING),
                    Card(Suit.CLUBS, CardValue.TEN),
                    Card(Suit.HEARTS, CardValue.TEN)
            )
            player2Hand =  arrayOf(
                    Card(Suit.HEARTS, CardValue.KING),
                    Card(Suit.DIAMONDS, CardValue.KING),
                    Card(Suit.HEARTS, CardValue.KING),
                    Card(Suit.CLUBS, CardValue.SEVEN),
                    Card(Suit.HEARTS, CardValue.SEVEN)
            )
            every { player1.hand.rank } returns 7
            every { player2.hand.rank } returns 7
            every { player1.winningCard } returns CardValue.TEN
            every { player1.hand.pokerHand } returns player1Hand
            every { player2.hand.pokerHand } returns player2Hand
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner to true for player1") {
                expect(player1.isWinner).to.equal(true)
                expect(player1.winningCard.numericValue).to.equal(10)
            }
        }
        on("player2 has higher full house pair") {
            player1Hand =  arrayOf(
                    Card(Suit.HEARTS, CardValue.KING),
                    Card(Suit.DIAMONDS, CardValue.KING),
                    Card(Suit.HEARTS, CardValue.KING),
                    Card(Suit.CLUBS, CardValue.TEN),
                    Card(Suit.HEARTS, CardValue.TEN)
            )
            player2Hand =  arrayOf(
                    Card(Suit.HEARTS, CardValue.KING),
                    Card(Suit.DIAMONDS, CardValue.KING),
                    Card(Suit.HEARTS, CardValue.KING),
                    Card(Suit.CLUBS, CardValue.SEVEN),
                    Card(Suit.HEARTS, CardValue.SEVEN)
            )
            every { player1.hand.rank } returns 7
            every { player2.hand.rank } returns 7
            every { player2.winningCard } returns CardValue.TEN
            every { player1.hand.pokerHand } returns player2Hand
            every { player2.hand.pokerHand } returns player1Hand
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner to true for player2") {
                expect(player2.isWinner).to.equal(true)
                expect(player2.winningCard.numericValue).to.equal(10)
            }
        }
        on("full house tie with both players having the same hand") {
            player1Hand =  arrayOf(
                    Card(Suit.HEARTS, CardValue.KING),
                    Card(Suit.DIAMONDS, CardValue.KING),
                    Card(Suit.HEARTS, CardValue.KING),
                    Card(Suit.CLUBS, CardValue.TEN),
                    Card(Suit.HEARTS, CardValue.TEN)
            )
            every { player1.hand.rank } returns 7
            every { player2.hand.rank } returns 7
            every { player2.winningCard } returns CardValue.TEN
            every { player1.hand.pokerHand } returns player1Hand
            every { player2.hand.pokerHand } returns player1Hand
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner to false for both players") {
                expect(player1.isWinner).to.equal(false)
                expect(player2.isWinner).to.equal(false)
            }
        }
    }


    describe("four of a kind") {
        beforeEachTest {
            player1Hand =  arrayOf(
                    Card(Suit.HEARTS, CardValue.KING),
                    Card(Suit.DIAMONDS, CardValue.KING),
                    Card(Suit.HEARTS, CardValue.KING),
                    Card(Suit.CLUBS, CardValue.KING),
                    Card(Suit.HEARTS, CardValue.JACK)
            )
            player2Hand =  arrayOf(
                    Card(Suit.HEARTS, CardValue.QUEEN),
                    Card(Suit.DIAMONDS, CardValue.QUEEN),
                    Card(Suit.HEARTS, CardValue.QUEEN),
                    Card(Suit.CLUBS, CardValue.QUEEN),
                    Card(Suit.HEARTS, CardValue.TEN)
            )
        }
        on("player1 has higher four of a kind") {
            every { player1.hand.rank } returns 8
            every { player2.hand.rank } returns 8
            every { player1.winningCard } returns CardValue.KING
            every { player1.hand.pokerHand } returns player1Hand
            every { player2.hand.pokerHand } returns player2Hand
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner to true for player1") {
                expect(player1.isWinner).to.equal(true)
                expect(player1.winningCard.numericValue).to.equal(13)
            }
        }
        on("player2 has higher four of a kind") {
            every { player1.hand.rank } returns 8
            every { player2.hand.rank } returns 8
            every { player2.winningCard } returns CardValue.KING
            every { player1.hand.pokerHand } returns player2Hand
            every { player2.hand.pokerHand } returns player1Hand
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner to true for player2") {
                expect(player2.isWinner).to.equal(true)
                expect(player2.winningCard.numericValue).to.equal(13)
            }
        }
        on("player1 has higher four of a kind side card") {
            player1Hand =  arrayOf(
                    Card(Suit.HEARTS, CardValue.KING),
                    Card(Suit.DIAMONDS, CardValue.KING),
                    Card(Suit.HEARTS, CardValue.KING),
                    Card(Suit.CLUBS, CardValue.KING),
                    Card(Suit.HEARTS, CardValue.TEN)
            )
            player2Hand =  arrayOf(
                    Card(Suit.HEARTS, CardValue.KING),
                    Card(Suit.DIAMONDS, CardValue.KING),
                    Card(Suit.HEARTS, CardValue.KING),
                    Card(Suit.CLUBS, CardValue.KING),
                    Card(Suit.HEARTS, CardValue.SEVEN)
            )
            every { player1.hand.rank } returns 8
            every { player2.hand.rank } returns 8
            every { player1.winningCard } returns CardValue.TEN
            every { player1.hand.pokerHand } returns player1Hand
            every { player2.hand.pokerHand } returns player2Hand
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner to true for player1") {
                expect(player1.isWinner).to.equal(true)
                expect(player1.winningCard.numericValue).to.equal(10)
            }
        }
        on("player2 has higher four of a kind side card") {
            player1Hand =  arrayOf(
                    Card(Suit.HEARTS, CardValue.KING),
                    Card(Suit.DIAMONDS, CardValue.KING),
                    Card(Suit.HEARTS, CardValue.KING),
                    Card(Suit.CLUBS, CardValue.KING),
                    Card(Suit.HEARTS, CardValue.TEN)
            )
            player2Hand =  arrayOf(
                    Card(Suit.HEARTS, CardValue.KING),
                    Card(Suit.DIAMONDS, CardValue.KING),
                    Card(Suit.HEARTS, CardValue.KING),
                    Card(Suit.CLUBS, CardValue.KING),
                    Card(Suit.HEARTS, CardValue.SEVEN)
            )
            every { player1.hand.rank } returns 8
            every { player2.hand.rank } returns 8
            every { player2.winningCard } returns CardValue.TEN
            every { player1.hand.pokerHand } returns player2Hand
            every { player2.hand.pokerHand } returns player1Hand
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner to true for player2") {
                expect(player2.isWinner).to.equal(true)
                expect(player2.winningCard.numericValue).to.equal(10)
            }
        }
        on("both players have the same four of a kind hand") {
            every { player1.hand.rank } returns 8
            every { player2.hand.rank } returns 8
            every { player1.winningCard } returns CardValue.TEN
            every { player1.hand.pokerHand } returns player1Hand
            every { player2.hand.pokerHand } returns player1Hand
            GameDecider.compareHandRanks(player1, player2)
            it("should set is winner to false for both players") {
                expect(player1.isWinner).to.equal(false)
                expect(player2.isWinner).to.equal(false)
            }
        }
    }
})