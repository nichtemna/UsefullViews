package com.nichtemna.views.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.inputmethod.InputMethodManager;
import com.nichtemna.Views.R;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: temnanich
 * Date: icon_samples/20/13
 * Time: icon_paper_yellow:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class Utils {
    private static final long MILLS_IN_EIGHTIN_YEARS = 568036800000l;


    /**
     *
     * @param i
     */

    public static boolean isEnding (int i) {
        int lastDigit = i%10;
        int lastTwoDigits = i%100;
        if (lastDigit == 2 || lastDigit ==3 || lastDigit==4) {
            if (lastTwoDigits == 12 || lastTwoDigits == 13 || lastTwoDigits == 14) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }


    /**
     * Check if entered phone number match regular expression
     *
     * @param s       - user entered number
     * @param pattern - regular expression
     */
    public static boolean isMatch(String s, String pattern) {
        try {
            Pattern patt = Pattern.compile(pattern);
            Matcher matcher = patt.matcher(s);
            return matcher.matches();
        } catch (RuntimeException e) {
            return false;
        }
    }

    /**
     * Checks whether there's connection to Internet.
     *
     * @return true if there's connection or false otherwise
     */
    public static boolean isOnline(Context context) {
        boolean result = false;
        if (context != null) {
            final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo netInfo = cm.getActiveNetworkInfo();
            result = (netInfo != null && netInfo.isConnected());
        }
        return result;
    }


    /**
     * Hides the soft keyboard.
     */
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
    }

    /**
     * Shows the soft keyboard.
     */
    public static void showKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    /**
     * Comparing two dates, true if end will be after start
     */
    public static boolean compareTwoDates(Calendar start, Calendar end) {
        boolean result = false;
        if (end.getTimeInMillis() > start.getTimeInMillis() + 1000) {
            result = true;
        }
        return result;
    }



    /**
     * Converts string from the server to readable state for user
     */
    public static String convertToUserReadableGender(Context context, String gender) {
        String string;
        if (gender == null || gender.equals(Extras.MALE)) {
            string = context.getResources().getString(R.string.male_capital);
        } else {
            string = context.getResources().getString(R.string.female_capital);
        }
        return string;
    }


    /**
     * Checks if user older than 18 years
     */
    public static boolean isMatchAge(long birthdayInMills) {
        boolean isMatch = false;
        Calendar calendar = Calendar.getInstance();
        long currentTime = calendar.getTimeInMillis();
        if (currentTime - birthdayInMills >= MILLS_IN_EIGHTIN_YEARS) {
            isMatch = true;
        }
        return isMatch;
    }
}
