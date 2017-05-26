package fuckermonkey.phots.ui.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/**
 * 自定义BaseFragment，支持View预加载，首次展现时数据加载
 * <p/>
 * Created by Will on 16/3/24.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    protected View rootView;//容器View
    protected FragmentActivity context;
    public LayoutInflater mInflater;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getActivity();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;
        if (getLayoutId() == 0) {
            throw new NullPointerException("Layout files can not be empty");
        }
        rootView = inflater.inflate(getLayoutId(), container, false);

        initView();
        setViewListener();
        initFragment();

        return rootView;
    }

    /**
     * 初始化Fragment
     */
    protected abstract void initFragment();

    /**
     * 获取布局文件Id
     *
     * @return 布局ID--R.id.main
     */
    protected abstract int getLayoutId();

    /**
     * 初始化View
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
    protected abstract void OnClick(View v);

    /**
     * 正常OnResume,和当前可见状态无关
     */
    protected void normalOnResume() {
        //normal resume
    }

    /**
     * 针对ViewPage中Fragment可见的onResume
     */
    protected void onUserVisibleHintResume() {
    }

    /**
     * 针对ViewPage中Fragment可见的onPause
     */
    protected void onUserVisibleHintPause() {
    }

    /**
     * 针对通过fragmentManager的add,show,hide中可见的onResume
     */
    protected void onIsVisibleResume() {
    }

    /**
     * 针对通过fragmentManager的add,show,hide中可见的onPause
     */
    protected void onIsVisiblePause() {
    }

    @Override
    public void onResume() {
        super.onResume();

        normalOnResume();

        if (isVisible()) {
            onIsVisibleResume();
        }
        if (getUserVisibleHint()) {
            onUserVisibleHintResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if (isVisible()) {
            onIsVisiblePause();
        }
        if (getUserVisibleHint()) {
            onUserVisibleHintPause();
        }
    }

    @Override
    public void onClick(View v) {
        OnClick(v);
    }
}
