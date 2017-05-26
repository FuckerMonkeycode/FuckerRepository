package fuckermonkey.phots.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;


import fuckermonkey.phots.util.SpUtils;

/**
 * IApplication
 * <p>
 * Created by xuxiaowu on 2016/11/4.
 */
public class IApplication extends Application {

    static Resources _resource;
    private static SharedPreferences _sp;

    @Override
    public void onCreate() {
        super.onCreate();

        _resource = getApplicationContext().getResources();
        _sp = getSharedPreferences("photos_sp.pref", Context.MODE_PRIVATE);
        SpUtils.instance().init(_sp);

    }

    public static Resources resources() {
        return _resource;
    }

    public static SharedPreferences getPreferences() {
        return _sp;
    }
}
