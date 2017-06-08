package fuckermonkey.phots.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup

import fuckermonkey.phots.R
import fuckermonkey.phots.ui.base.BaseActivity
import fuckermonkey.phots.util.ActivityCollector
import fuckermonkey.phots.util.Constants
import fuckermonkey.phots.util.SpUtils
import kotlinx.android.synthetic.main.activity_set_list_column_view.*

class SetListColumnActivity : BaseActivity(), RadioGroup.OnCheckedChangeListener {

    var mOldColumnIndex: Int? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_set_list_column_view
    }

    override fun initActivity() {
        setControlBackView(true)
        mOldColumnIndex = SpUtils.get(Constants.SP_COLUMN, 3)
        val radioButton = column_rg.getChildAt(mOldColumnIndex!! - 1) as RadioButton
        radioButton.isChecked = true
    }

    override fun initView() {
    }

    override fun setViewListener() {
        column_rg.setOnCheckedChangeListener(this)
    }

    override fun onCheckedChanged(rg: RadioGroup?, checkId: Int) {
        when (checkId) {
            R.id.one_column -> {
                SpUtils.put(Constants.SP_COLUMN, 1)
            }
            R.id.two_column -> {
                SpUtils.put(Constants.SP_COLUMN, 2)
            }
            R.id.three_column -> {
                SpUtils.put(Constants.SP_COLUMN, 3)
            }
            R.id.four_column -> {
                SpUtils.put(Constants.SP_COLUMN, 4)
            }
        }

    }

    override fun OnClick(v: View?) {
        val id = v!!.id
        when (id) {
            R.id.act_back_view -> {
                if (mOldColumnIndex == SpUtils.get(Constants.SP_COLUMN, 1)) {
                    finish()
                } else {
                    restart()
                }
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN) {
            if (mOldColumnIndex == SpUtils.get(Constants.SP_COLUMN, 3)) {
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
}
