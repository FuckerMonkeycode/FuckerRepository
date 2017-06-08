package fuckermonkey.phots.ui.activity

import android.content.Intent
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.widget.RelativeLayout

import fuckermonkey.phots.R
import fuckermonkey.phots.ui.base.BaseActivity
import fuckermonkey.phots.util.ActivityCollector
import fuckermonkey.phots.util.Constants
import fuckermonkey.phots.util.SpUtils
import fuckermonkey.phots.view.PressableImageView

class SetThemeActivity : BaseActivity() {

    var mThemeItemContainView: RelativeLayout? = null
    var mSelectedView: PressableImageView? = null
    var mOldThemeIndex: Int? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_set_theme_view
    }

    override fun initActivity() {
        setControlBackView(true)
        val index = SpUtils.get(Constants.SP_THEME, 0)
        mSelectedView = mThemeItemContainView!!.getChildAt(index) as PressableImageView
        mSelectedView!!.setImageResource(R.drawable.confirm)
        mOldThemeIndex = SpUtils.get(Constants.SP_THEME, 0)
    }

    override fun initView() {
        mThemeItemContainView = findViewById(R.id.theme_item_contain_view) as RelativeLayout
    }

    override fun setViewListener() {
        findViewById(R.id.pink_view).setOnClickListener(this)
        findViewById(R.id.purple_view).setOnClickListener(this)
        findViewById(R.id.blue_view).setOnClickListener(this)
        findViewById(R.id.yellow_view).setOnClickListener(this)
        findViewById(R.id.green_view).setOnClickListener(this)
        findViewById(R.id.orange_view).setOnClickListener(this)
        findViewById(R.id.red_view).setOnClickListener(this)
        findViewById(R.id.grey_view).setOnClickListener(this)
        findViewById(R.id.act_back_view).setOnClickListener(this)
    }

    override fun OnClick(v: View?) {
        val id = v!!.id
        var themeIndex: Int? = null
        when (id) {
            R.id.pink_view -> {
                themeIndex = Constants.THEME_PINK
            }
            R.id.purple_view -> {
                themeIndex = Constants.THEME_PURPLE
            }
            R.id.blue_view -> {
                themeIndex = Constants.THEME_BLUE
            }
            R.id.yellow_view -> {
                themeIndex = Constants.THEME_YELLOW
            }
            R.id.green_view -> {
                themeIndex = Constants.THEME_GREEN
            }
            R.id.orange_view -> {
                themeIndex = Constants.THEME_ORANGE
            }
            R.id.red_view -> {
                themeIndex = Constants.THEME_RED
            }
            R.id.grey_view -> {
                themeIndex = Constants.THEME_GREY
            }
            R.id.act_back_view -> {
                if (mOldThemeIndex == SpUtils.get(Constants.SP_THEME, 0)) {
                    finish()
                    return
                } else {
                    restart()
                    return
                }
            }
        }
        SpUtils.put(Constants.SP_THEME, themeIndex)
        mSelectedView!!.setImageResource(R.color.transparent)
        mSelectedView = v as PressableImageView
        val selectedView = mThemeItemContainView!!.getChildAt(themeIndex!!) as PressableImageView
        selectedView.setImageResource(R.drawable.confirm)

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN) {
            if (mOldThemeIndex == SpUtils.get(Constants.SP_THEME, 0)) {
                finish()
                return true
            } else {
                restart()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    fun restart() {
        ActivityCollector.finishAll()
        val intent = Intent()
        intent.setClass(this, MainTabActivity::class.java)
        startActivity(intent)
    }

    val ss = View.OnTouchListener{
        view: View, motionEvent: MotionEvent ->
        print(motionEvent.action)
        true
    }

}
