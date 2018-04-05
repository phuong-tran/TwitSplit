package zalora.com.twitsplit.utils

import javax.inject.Singleton

@Singleton
class TwitSplitString {
    companion object {
        const val LIMIT_CHARACTERS:Int = 50
        const val WHITE_SPACE: String = " "
    }

    fun getWordArray(str: String): Array<String> {
        return str.trim().split(WHITE_SPACE.toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
    }


    fun isValidMessages(string: String, limit: Int): Boolean {
        if (string.isNotEmpty()) {
            val messages = getWordArray(string)
            for (message in messages) {
                if (message.length > limit) {
                    return false
                }
            }
        } else {
            return false
        }
        return true
    }


    private fun getMessageLine(indicator: String, strings: Array<String>, limit: Int, startAtIndex: Int): MessageLineInfo {
        var result = indicator
        val messageLineInfo = MessageLineInfo()
        var stopAt = 0
        for (i in startAtIndex until strings.size) {
            result = result + strings[i] + WHITE_SPACE
            stopAt = i
            if (result.length - 1 > limit) {
                // Remove Last White Space
                result = result.substring(0, result.lastIndexOf(WHITE_SPACE))
                // Remove Last Word
                result = result.substring(0, result.lastIndexOf(WHITE_SPACE))
                break
            }
        }
        messageLineInfo.content = result.trim()
        messageLineInfo.stopAt = stopAt
        return messageLineInfo
    }


    fun isSingleLine(text: String, limit:Int) :Boolean {
        return text.length <= limit
    }

   private fun generateIndicators(lineNumbers: Int): String {
        val builder = StringBuilder()
        for (i in 1..lineNumbers) {
            builder.append(i).append("/").append(lineNumbers).append(" ")
        }
        return builder.toString()
    }

    private fun generateIndicatorsFromTo(from: Int, to: Int): String {
        val builder = StringBuilder()
        for (i in from..to) {
            builder.append(i).append("/").append(to).append(" ")
        }
        return builder.toString()
    }

    private fun concatStringFromIndexToEnd(words: Array<String>, from: Int): String {
        val stringBuilder = StringBuilder()
        for (i in from until words.size) {
            stringBuilder.append(words[i]).append(" ")
        }
        return stringBuilder.toString()
    }

    private fun estimateRemainingLines(remainingString: String, currentLines: Int, limit: Int): Int {
        val remainingLines = remainingString.length / limit + if (remainingString.length % limit > 0) 1 else 0
        val indicatorString = generateIndicatorsFromTo(currentLines + 1, remainingLines + currentLines)
        val indicatorLines = indicatorString.length / limit
        return remainingLines + indicatorLines
    }

    fun estimateLines(str: String, limit: Int): Int {
        val originalLines = str.length / limit + if (str.length % limit > 0) 1 else 0
        val indicatorString = generateIndicators(originalLines)
        val indicatorLines = indicatorString.length / limit
        return originalLines + indicatorLines
    }

    fun splitMessage(words: Array<String>, estimateLineNumber: Int, limit: Int): List<String> {
        val results = ArrayList<String>()
        var index = 0
        var messageLineInfo = MessageLineInfo()
        for (i in 0 until estimateLineNumber) {
            val indicator = (i + 1).toString() + "/" + estimateLineNumber + WHITE_SPACE
            messageLineInfo = getMessageLine(indicator, words, limit, index)
            index = messageLineInfo.stopAt
            results.add(messageLineInfo.content)
        }
        if (messageLineInfo.stopAt < words.size - 1) {
            val remainString = concatStringFromIndexToEnd(words, messageLineInfo.stopAt).trim()
            val lines = estimateLineNumber + estimateRemainingLines(remainString, estimateLineNumber, limit)
            return splitMessage(words, lines, limit)
        }
        return results
    }
}