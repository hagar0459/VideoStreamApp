package tobeapps.intigral.Core;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;


/**
 * Created by HP on 6/12/2018.
 */
public class SharedPreferencesManager {

    private static final String TAG = "PreferencesUtils";
    private static SharedPreferencesManager instance;
    protected Context context;
    private SharedPreferencesManager preferences;

    public static synchronized SharedPreferencesManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreferencesManager();
        }
        instance.context = context;
        return instance;
    }

    public void removeSharedPreference(String preferenceName) {
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = appSharedPrefs.edit();
        editor.remove(preferenceName);
        editor.commit();
    }//end removeSharedPreference

    public String getSharedPreference(String preferenceName) {
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        String preferenceValue = appSharedPrefs.getString(preferenceName, "");
        return preferenceValue;
    }

    public void saveToSharedPreferences(String preferenceName, String preferenceValue) {
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString(preferenceName, preferenceValue);
        prefsEditor.commit();
        Log.i(TAG, "Preference: " + preferenceName + " with value " + preferenceValue + " was successfully saved to preferences");
    }

    public boolean getBoolean(String entry) {
        return getBoolean(entry, false);
    }

    public boolean getBoolean(int entryResId) {
        return getBoolean(context.getString(entryResId), false);
    }

    public boolean getBoolean(int entryResId, boolean defaultValue) {
        return getBoolean(context.getString(entryResId), defaultValue);
    }

    public boolean getBoolean(String entry, boolean defaultValue) {
        return getSharedPreferences().getBoolean(getEncryptedName(entry), defaultValue);
    }

    public void setBoolean(int entryResId, boolean value) {
        setBoolean(context.getString(entryResId), value);
    }

    public void setBoolean(String entry, boolean value) {
        SharedPreferences sharedPreferences = getSharedPreferences();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(getEncryptedName(entry), value);
        editor.commit();
    }

    public void setObject(String entry, String value) {
        SharedPreferences sharedPreferences = getSharedPreferences();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getEncryptedName(entry), value);
        editor.commit();
    }

    public String getObject(String entry) {
        return getObject(entry, "");
    }

    public String getObject(String entry, String defaultValue) {
        return getSharedPreferences().getString(getEncryptedName(entry), defaultValue);
    }


    public String getString(int entryResId) {
        return getString(context.getString(entryResId), null);
    }

    public String getString(String entry) {
        return getString(entry, null);
    }

    public String getString(int entryResId, String defaultValue) {
        return getString(context.getString(entryResId), defaultValue);
    }

    public String getString(String entry, String defaultValue) {
        String value = getSecurePreferences().getString(getEncryptedName(entry));
        if (value != null) {
            return value;
        } else {
            return defaultValue;
        }
    }

    public void setString(int entryResId, String value) {
        setString(context.getString(entryResId), value);
    }

    public void setString(String entry, String value) {
        getSecurePreferences().put(getEncryptedName(entry), value);


    }

    public void setStringValue(String entry, String value) {
        SharedPreferences sharedPreferences = getSharedPreferences();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(entry, value);

    }

    public String getStringValue(String entry, String value) {
        return getSharedPreferences().getString(entry, value);
    }

    public String geStringDeep(String entry, String defaultValue) {
        String value = getSecurePreferences().getString(entry);
        if (value != null) {
            return value;
        } else {
            return defaultValue;
        }
    }

    public void removeString(int entryResId) {
        removeString(context.getString(entryResId));
    }

    public void removeString(String entry) {
        getSecurePreferences().removeValue(getEncryptedName(entry));
    }

    private String getEncryptedName(String name) {
        return new StringBuffer(Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID)
                + context.getApplicationContext().getPackageName() + name).toString();
    }

    private SecurePreferences getSecurePreferences() {
        return new SecurePreferences(context, getEncryptedName(context.getApplicationContext().getPackageName()),
                getEncryptedName(Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID)), true);
    }

    private SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public SharedPreferencesManager getPreferences() {
        return preferences;
    }
}

