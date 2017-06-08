package fuckermonkey.phots.ui.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import fuckermonkey.phots.R;
import fuckermonkey.phots.util.ActivityCollector;
import fuckermonkey.phots.util.Constants;
import fuckermonkey.phots.util.SpUtils;

/**
 * BaseViewActivity基础Activity
 * <p/>
 * Created by Will on 16/9/19.
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    protected AppCompatActivity context;//上下文
    protected ImageView backView;//Activity返回
    protected TextView titleView;//Activity的title
    protected TextView rightView;//Activity右边文字按钮
    protected boolean self_control_back = false;//是否自主控制返回按钮事件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        context = this;
        ActivityCollector.add(this);

        setAppTheme();

        if (getLayoutId() != -1) {
            if (getLayoutId() == 0) {
                throw new IllegalStateException("Layout files can not be empty");
            }
            setContentView(getLayoutId());
        }

        backView = (ImageView) findViewById(R.id.act_back_view);
        if (backView != null) backView.setOnClickListener(this);

        initView();
        setViewListener();
        initActivity();
    }

    /**
     * 获取布局文件
     *
     * @return -1:不设置布局,0:布局文件不合法抛出异常
     */
    protected abstract int getLayoutId();

    /**
     * 初始化Activity相关参数和变量,和view无关
     */
    protected abstract void initActivity();

//    /**
//     * 获取title名称
//     * title为null时不设置返回和title,title为空时,只设置返回监听
//     *
//     * @return Activity Title文本
//     */
//    protected abstract String getTitleName();

    /**
     * 初始化View控件
     */
    protected abstract void initView();

    /**
     * 设置View控件的事件监听
     */
    protected abstract void setViewListener();

    /**
     * 点击事件
     *
     * @param v 点击对应的View
     */
    protected void OnClick(View v) {
    }

    @Override
    public void onClick(View v) {
        if (!self_control_back && v.getId() == R.id.act_back_view) {
            context.finish();
        }
        OnClick(v);
    }

    @Override
    protected void onDestroy() {
        ActivityCollector.remove(this);
        super.onDestroy();
        System.gc();
    }

    public void setControlBackView(boolean b) {
        self_control_back = b;
    }

    private void setAppTheme() {
        int themeId = SpUtils.get(Constants.SP_THEME, 0);
        int theme = R.style.PinkAppTheme;
        switch (themeId) {
            case Constants.THEME_PINK:
                theme = R.style.PinkAppTheme;
                break;
            case Constants.THEME_PURPLE:
                theme = R.style.PurpleAppTheme;
                break;
            case Constants.THEME_BLUE:
                theme = R.style.BlueAppTheme;
                break;
            case Constants.THEME_YELLOW:
                theme = R.style.YellowAppTheme;
                break;
            case Constants.THEME_GREEN:
                theme = R.style.GreenAppTheme;
                break;
            case Constants.THEME_ORANGE:
                theme = R.style.OrangeAppTheme;
                break;
            case Constants.THEME_RED:
                theme = R.style.RedAppTheme;
                break;
            case Constants.THEME_GREY:
                theme = R.style.GreyAppTheme;
                break;
        }
        setTheme(theme);
    }
}
