import com.winterbe.expekt.expect
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class SimpleTest : Spek({
    describe("a sum") {

        it("should equal 10") {
            val sum = 10
            expect(sum).to.equal(10)
        }
    }
})

