import com.winterbe.expekt.expect
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class SimpleTest : Spek({
    describe("a calculator") {

        it("should return the result of adding the first number to the second number") {
            val sum = 10
            expect(sum).to.equal(10)
        }
    }
})

