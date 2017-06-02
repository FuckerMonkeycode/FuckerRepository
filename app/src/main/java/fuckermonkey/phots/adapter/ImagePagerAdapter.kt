package fuckermonkey.phots.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bm.library.PhotoView
import fuckermonkey.phots.R
import fuckermonkey.phots.model.ImageListResult
import fuckermonkey.phots.util.Utils

/**
 * Created by xuxiaowu on 2017/5/27.
 */
class ImagePagerAdapter(dataList: ArrayList<ImageListResult.Data>, imageView: ArrayList<PhotoView>) : PagerAdapter() {

    var dataList: ArrayList<ImageListResult.Data>? = null
    var viewList: ArrayList<PhotoView>? = null

    init {
        this.dataList = dataList
        this.viewList = imageView
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        container!!.addView(viewList!!.get(position))
        Utils.loadImage(viewList!!.get(position), dataList!!.get(position).image_url, R.mipmap.ic_launcher)
        return viewList!!.get(position)
    }

    override fun getCount(): Int {
        return dataList!!.size
    }

    override fun isViewFromObject(view: View?, obj: Any?): Boolean {
        return view == obj
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        container!!.removeView(viewList!!.get(position))
    }
}