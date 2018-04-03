package zalora.com.twitsplit.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import java.text.SimpleDateFormat
import java.util.*


class Utils {
    companion object {
        val df: SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")

        fun convertDateToString(date : Date):String  =  df.format(date)
        fun hideKeyboard(context: Context) {
            val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
        }

        fun showKeyBoard(context: Context) {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        }
    }

}