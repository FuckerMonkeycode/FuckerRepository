package fuckermonkey.phots.util;

import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * SharedPreferences 相关工具类
 * <p>
 * Created by Will on 16/9/30.
 */
public class SpUtils {

    private static SpUtils spUtils;
    public static SharedPreferences sp;
    public static SharedPreferences.Editor editor;

    public static SpUtils instance() {
        if (spUtils == null) {
            synchronized (SpUtils.class) {
                if (spUtils == null) {
                    spUtils = new SpUtils();
                }
            }
        }
        return spUtils;
    }

    public void init(SharedPreferences sharedPreferences) {
        sp = sharedPreferences;
        editor = sp.edit();
    }

    /**
     * SharedPreferences 存入职
     *
     * @param key   key值
     * @param value 存入值
     */
    public static void put(String key, Object value) {
        if (TextUtils.isEmpty(key) || value == null) return;
        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        }
        editor.apply();
    }

    public static int get(String key, int def) {
        return sp.getInt(key, def);
    }

    public static String get(String key, String def) {
        return sp.getString(key, def);
    }

    public static Boolean get(String key, Boolean def) {
        return sp.getBoolean(key, def);
    }

    public static long get(String key, long def) {
        return sp.getLong(key, def);
    }

    public static float get(String key, float def) {
        return sp.getFloat(key, def);
    }

    /**
     * 清空本地SharedPreferences缓存数据
     */
    public static void clearLocalSharedPreferences() {

    }
}
