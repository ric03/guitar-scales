import net.jqwik.api.Arbitraries
import net.jqwik.api.Arbitrary
import net.jqwik.api.ForAll
import net.jqwik.api.Property
import net.jqwik.api.Provide
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.assertThrows

internal class ScaleKtTest {

    private val defaultChromaticScale = scales.get("chromatic")!!

    @Provide
    fun possibleScaleValues(): Arbitrary<String> =
        Arbitraries.of("C", "C♯", "D", "D♯", "E", "F", "F♯", "G", "G♯", "A", "A♯", "B")

    @Provide
    fun impossibleScaleValues(): Arbitrary<String> =
        Arbitraries.strings()
            .excludeChars('C', 'D', 'E', 'F', 'G', 'A', 'B')

    @Property
    fun theListSizeShouldBeConstant(@ForAll("possibleScaleValues") arbitraryString: String) {
        val arbitraryScale = defaultChromaticScale.getRangeStartingWith(arbitraryString)
        assertEquals(defaultChromaticScale.size, arbitraryScale.size)
    }

    @Property
    fun theListShouldOnlyContainUniqueStrings(@ForAll("possibleScaleValues") arbitraryString: String) {
        val arbitraryScale = defaultChromaticScale.getRangeStartingWith(arbitraryString)
        assertEquals(arbitraryScale.size, arbitraryScale.toSet().size)
    }

    @Property
    fun theListShouldAlwaysStartWithTheArbitrary(@ForAll("possibleScaleValues") arbitraryString: String) {
        assertEquals(arbitraryString, defaultChromaticScale.getRangeStartingWith(arbitraryString).first())
    }

    /**
     * abcdefg + abcdefg = abcdefgabcdefg
     *                     --cdefgab-----
     *
     * efgabcd + efgabcd = efgabcdefgabcd
     *                     -----cdefgab--
     */
    @Property
    fun theListShouldBeOrdered(@ForAll("possibleScaleValues") arbitraryString: String) {
        val arbitraryScaleAsString = defaultChromaticScale.getRangeStartingWith(arbitraryString).reduce(String::plus)
        val defaultScaleAsString = defaultChromaticScale.reduce(String::plus)
        assertTrue((arbitraryScaleAsString + arbitraryScaleAsString).contains(defaultScaleAsString))
    }

    @Property
    fun shouldThrowIllegalArgumentExceptionOnInvalidNotes(@ForAll("impossibleScaleValues") arbitraryString: String) {
        assertThrows<IllegalArgumentException> { defaultChromaticScale.getRangeStartingWith(arbitraryString) }
    }
}