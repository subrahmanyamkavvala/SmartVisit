package smartvisit.com.smartvisit.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by subramanyam on 10-12-2015.
 */
public class PreferenceManager {

    String PREFS_NAME  =  "smartvisitor";
    public final  static String SESSION_USER = "session_user";

    public PreferenceManager(){
    }

    public  void save(Context context, String key,String text) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2
        editor.putString(key, text); //3
        editor.commit(); //4
    }
    public String getValue(Context context,String key) {
        SharedPreferences settings;
        String text;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        text = settings.getString(key, null); //2
        return text;
    }
    public void clearSharedPreference(Context context) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.clear();
        editor.commit();
    }
    public void removeValue(Context context,String key) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.remove(key);
        editor.commit();
    }

    public  void saveBoolean(Context context, String key,boolean value) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2
        editor.putBoolean(key, value); //3
        editor.commit(); //4
    }

    public boolean getBoolean(Context context,String key) {
        SharedPreferences settings;
        boolean value;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        value = settings.getBoolean(key, false); //2
        return value;
    }

}
