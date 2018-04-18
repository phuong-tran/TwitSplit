package zalora.com.twitsplit.utils

import android.R
import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
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


        fun isTablet(context: Context): Boolean {
            try {
                // Compute screen size
                val dm = context.getResources().getDisplayMetrics()
                val screenWidth = dm.widthPixels / dm.xdpi
                val screenHeight = dm.heightPixels / dm.ydpi
                val size = Math.sqrt(Math.pow(screenWidth.toDouble(), 2.0) + Math.pow(screenHeight.toDouble(), 2.0))
                // Tablet devices should have a screen size greater than 6 inches
                return size >= 6
            } catch (t: Throwable) {
                return false
            }
        }
    }




}