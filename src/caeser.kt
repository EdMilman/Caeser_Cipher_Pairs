/**
 * Uses the Caesar cipher to encrypt a string by a given non-negative integer amount.
 * @param s The string to be encrypted
 * @param n The integer shift amount. Must not be negative
 * @throws IllegalArgumentException
 * @return The encrypted string
 */
fun encipher(s: String, n: Int): String {
    if(n < 0) {
        throw IllegalArgumentException("n must not be negative.")
    }
    return when(s.length){
        1 -> shift(s[0], n).toString()
        else -> shift(s[0], n) + encipher(s.substring(1), n)
    }
}

/**
 * Helper function for encipher. Encrypts one character by a given non-negative integer amount.
 * @param c The character to be encrypted
 * @param n The integer shift amount. Must not be negative
 * @return The encrypted character
 */
fun shift(c: Char, n: Int) : Char {
    val UPPERCASE = 'A'.toInt()
    val LOWERCASE = 'a'.toInt()
    val ALPHA_LENGTH = 26
    return when {
        c.isUpperCase() -> ((((c.toInt() - UPPERCASE) + n) % ALPHA_LENGTH) + UPPERCASE).toChar()
        c.isLowerCase() -> ((((c.toInt() - LOWERCASE) + n) % ALPHA_LENGTH) + LOWERCASE).toChar()
        else -> c
    }
}

/**
 * Deciphers a string encrypted using the Caesar cipher.
 * @param s The string to decipher
 * @return The deciphered string
 */
fun decipher(s: String): String {
    val scoreMap = mutableMapOf<Double, String>()
    decipherHelper(s).forEach { scoreMap[getScore(it.toLowerCase())] = it }
    return scoreMap[scoreMap.keys.max()] as String
}

/**
 * Helper function for decipher. Assigns a score to a given string
 * based on the frequency of letters in the English language.
 * Frequency data from: https://en.wikipedia.org/wiki/Letter_frequency
 * @param s The string to assign a score to
 * @return The frequency score for the given string
 */
fun getScore(s: String): Double {
    val frequencyMap = mapOf('a' to 8.167,
            'b' to 1.492,
            'c' to 2.782,
            'd' to 4.253,
            'e' to 12.702,
            'f' to 2.228,
            'g' to 2.015,
            'h' to 6.094,
            'i' to 6.966,
            'j' to 0.153,
            'k' to 0.772,
            'l' to 4.025,
            'm' to 2.406,
            'n' to 6.749,
            'o' to 7.507,
            'p' to 1.929,
            'q' to 0.095,
            'r' to 5.987,
            's' to 6.327,
            't' to 9.056,
            'u' to 2.758,
            'v' to 0.978,
            'w' to 2.360,
            'x' to 0.150,
            'y' to 1.974,
            'z' to 0.074)
    var result = 0.0
    s.forEach { result += frequencyMap[it]?: 0.0 }
    return result
}

/**
 * Helper function for decipher. Returns all possible permutations of a given string
 * @param s the encrypted string
 * @return A list of all permutations of the string
 */
fun decipherHelper(s: String): List<String>{
    val ALPHA_LENGTH = 26
    val list = ArrayList<String>()
    for(i in 0 until ALPHA_LENGTH){
        list.add(encipher(s, i))
    }
    return list
}