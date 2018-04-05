package zalora.com.twitsplit

import org.junit.Test

import org.junit.Assert.*
import zalora.com.twitsplit.utils.TwitSplitString


class TwitSplitStringTest {
    companion object {

        val twitSplitString = TwitSplitString()

        const val TEST_STRING1 = "I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself."


        const val TEST_STRING2 = "Kotlin is a programming language introduced by JetBrains, the" +
                " official designer of the most intelligent Java IDE, named Intellij IDEA. This is a strongly statically" +
                " typed language that runs on JVM. In 2017, Google announced Kotlin is an official language for android development." +
                " Kotlin is an open source programming language that combines object-oriented" +
                " programming and functional features into a unique platform." +
                " The content is divided into various chapters that contain related topics with simple and useful examples."
    }


    @Test
    fun splitMessage1() {

        val expectedMessageNumber = 2

        val estimate = twitSplitString.estimateLines(TEST_STRING1, TwitSplitString.LIMIT_CHARACTERS)

        var results = twitSplitString.splitMessage(twitSplitString.getWordArray(TEST_STRING1), estimate, TwitSplitString.LIMIT_CHARACTERS)

        val expectedResults = listOf("1/2 I can't believe Tweeter now supports chunking",
                                    "2/2 my messages, so I don't have to do it myself.")

        val areEqual = results.containsAll(expectedResults) && results.size == expectedMessageNumber

        assertEquals(true, areEqual)

    }


    @Test
    fun splitMessage2() {

        val expectedMessageNumber = 13

        val estimate = twitSplitString.estimateLines(TEST_STRING2, TwitSplitString.LIMIT_CHARACTERS)

        var results = twitSplitString.splitMessage(twitSplitString.getWordArray(TEST_STRING2), estimate, TwitSplitString.LIMIT_CHARACTERS)

        val expectedResults = listOf(
                "1/13 Kotlin is a programming language introduced",
                "2/13 by JetBrains, the official designer of the",
                "3/13 most intelligent Java IDE, named Intellij",
                "4/13 IDEA. This is a strongly statically typed",
                "5/13 language that runs on JVM. In 2017, Google",
                "6/13 announced Kotlin is an official language for",
                "7/13 android development. Kotlin is an open source",
                "8/13 programming language that combines",
                "9/13 object-oriented programming and functional",
                "10/13 features into a unique platform. The content",
                "11/13 is divided into various chapters that",
                "12/13 contain related topics with simple and",
                "13/13 useful examples.")


        val areEqual = results.containsAll(expectedResults) && results.size == expectedMessageNumber

        assertEquals(true, areEqual)

    }
}
