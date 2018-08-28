import org.junit.Assert.*
import org.junit.Test
import java.util.*

class CaesarKtTest {
    private val random = Random()

    @Test
    fun shiftLowerByOne() {
        assertEquals('b', shift('a', 1))
    }

    @Test
    fun shiftUpperByOne() {
        assertEquals('B', shift('A', 1))
    }

    @Test
    fun shiftPunctuationByOne() {
        assertEquals('!', shift('!', 1))
        assertEquals(' ', shift(' ', 1))
    }

    @Test
    fun shiftGreater26(){
        assertEquals('b', shift('a', 677))
    }

    @Test(expected = IllegalArgumentException::class)
    fun encipherNegativeNThrowsException() {
        encipher("abc", -1)
    }

    @Test
    fun encipherByOne() {
        assertEquals("yzab", encipher("xyza", 1))
        assertEquals("A B", encipher("Z A", 1))
    }

    @Test
    fun encipherWithPunctuation() {
        assertEquals("Bzdrzq bhogdq? H oqdedq Bzdrzq rzkzc.", encipher("Caesar cipher? I prefer Caesar salad.", 25))
    }

    @Test
    fun decipherHelper(){
        assertEquals("a", decipherHelper("a")[0])
        assertEquals("n", decipherHelper("a")[13])
        assertEquals("z", decipherHelper("a")[25])
    }

    @Test
    fun decipher1() {
        assertEquals("Caesar cipher? I prefer Caesar salad.", decipher("Bzdrzq bhogdq? H oqdedq Bzdrzq rzkzc."))
    }

    @Test
    fun decipher2() {
        assertEquals("I like bacon in the mornings", decipher(encipher("I like bacon in the mornings", random.nextInt(100))))
    }

    @Test
    fun decipher3() {
        assertEquals("Will this test work?", decipher(encipher("Will this test work?",random.nextInt(100))))
    }

    @Test
    fun decipher4(){
        assertEquals("chutney is my inspiration", decipher(encipher("chutney is my inspiration", random.nextInt(100))))
    }

    @Test
    fun decipher5(){
        assertEquals("This is a sentence. This may also be a sentence.", decipher(encipher("This is a sentence. This may also be a sentence.", random.nextInt(1000))))
    }

    @Test
    fun decipherAllCaps() {
        assertEquals("ONE TWO THREE, TESTING, TESTING.", decipher(encipher("ONE TWO THREE, TESTING, TESTING.", random.nextInt(1000))))
    }
}