package fuckermonkey.phots.ui.activity

import android.support.v4.view.ViewPager
import android.widget.ImageView
import android.widget.TextView
import com.bm.library.PhotoView

import fuckermonkey.phots.R
import fuckermonkey.phots.adapter.ImagePagerAdapter
import fuckermonkey.phots.model.ImageListResult
import fuckermonkey.phots.ui.base.BaseActivity
import fuckermonkey.phots.util.Constants
import android.support.v4.view.ViewCompat.setFitsSystemWindows
import android.view.ViewGroup
import android.view.WindowManager
import fuckermonkey.phots.util.Utils


class BrowsImagePageActivity : BaseActivity(),ViewPager.OnPageChangeListener {

    var mViewPager: ViewPager? = null
    var mIndexView: TextView? = null
    var mImageList: ArrayList<ImageListResult.Data>? = null
    var mSelectPosition: Int? = null

    override fun getLayoutId(): Int {
        Utils.setTransparentStatusBar(this)
        return R.layout.activity_brows_image_page_view
    }

    override fun initActivity() {
        getIntentData()
        setupViewPager()
        setIndex()
    }

    override fun initView() {
        mViewPager = findViewById(R.id.pager_view) as ViewPager
        mIndexView = findViewById(R.id.index_view) as TextView
    }

    override fun setViewListener() {
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        val index = "" + (position + 1) + "/" + mImageList!!.size
        mIndexView!!.text = index
    }

    fun setupViewPager() {
        val viewList = ArrayList<PhotoView>()
        for (i in mImageList!!.iterator()) {
            val photoView = PhotoView(this)
            photoView.setImageResource(R.mipmap.ic_launcher)
            photoView.enable()
            photoView.scaleType = ImageView.ScaleType.FIT_CENTER
            viewList.add(photoView)
        }
        val imageListAdapter = ImagePagerAdapter(mImageList!!, viewList)
        mViewPager!!.adapter = imageListAdapter
        mViewPager!!.currentItem = mSelectPosition!!
        mViewPager!!.addOnPageChangeListener(this)
    }

    fun getIntentData() {
        mImageList = intent.getParcelableArrayListExtra(Constants.EXTRA_DATA)
        mSelectPosition = intent.getIntExtra(Constants.EXTRA_POSITION, 0)
    }

    fun setIndex() {
        mIndexView!!.text = "" + (mSelectPosition!! + 1) + "/" + mImageList!!.size
    }
}
