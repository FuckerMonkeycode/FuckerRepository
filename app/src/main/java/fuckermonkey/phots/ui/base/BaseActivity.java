package fuckermonkey.phots.ui.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import fuckermonkey.phots.util.ActivityCollector;

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

        if (getLayoutId() != -1) {
            if (getLayoutId() == 0) {
                throw new IllegalStateException("Layout files can not be empty");
            }
            setContentView(getLayoutId());
        }

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
//        if (!self_control_back && v.getId() == R.id.imgView_act_back) {
//            context.finish();
//        }
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
}
