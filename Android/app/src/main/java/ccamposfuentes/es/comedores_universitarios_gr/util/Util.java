package ccamposfuentes.es.comedores_universitarios_gr.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import ccamposfuentes.es.comedores_universitarios_gr.R;

/**
 * Author: Carlos Campos
 * Email: carlos@ccamposfuentes.es
 * Date: 20/9/16
 * Project: Android
 */

public class Util {

    private static String TAG = "util";
    /**
     * Save Course
     * @param c Context
     * @param value Value to save
     */
    public static void saveCourse (Context c, String value) {
        SharedPreferences sharedPref = c.getSharedPreferences(c.getString(R.string.user_preferences), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String s = sharedPref.getString(c.getString(R.string.up_courses), null);

        if (s == null)
            s = value+",";
        else
            s = s+value+",";

        editor.putString(c.getString(R.string.up_courses), s);
        editor.apply();

        Log.v(TAG, "Se ha guardado el plato :"+value);
    }

    public static void removeCourse (Context c, String course) {
        SharedPreferences sharedPref = c.getSharedPreferences(c.getString(R.string.user_preferences), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String s = sharedPref.getString(c.getString(R.string.up_courses), null);

        if (s!=null)
            s = s.replace(course+",", "");

        editor.putString(c.getString(R.string.up_courses), s);
        editor.apply();

        Log.v(TAG, "Se ha eliminado el plato :"+course);
        Log.v(TAG, s);
    }

    /**
     * Return user preference
     * @param c Context
     * @return String value from key
     */
    public static String getFavCourses (Context c) {
        SharedPreferences sharedPref = c.getSharedPreferences(c.getString(R.string.user_preferences), Context.MODE_PRIVATE);
        return sharedPref.getString(c.getString(R.string.up_courses), null);
    }

    /**
     * Return if a course is fav
     * @param c Context
     * @param course Course name
     * @return true or false
     */
    public static boolean isCourseFav(Context c, String course) {
        boolean fav = false;

        if (!course.isEmpty()) {

            course = course.replace(" ","_");

            String courses = getFavCourses(c);
            if (courses != null) {
                if (courses.contains(course))
                    fav = true;
            }
        }

        return fav;
    }

}
