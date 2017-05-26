package fuckermonkey.phots.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import fuckermonkey.phots.R
import fuckermonkey.phots.model.ImageListResult
import fuckermonkey.phots.util.ConvertUtils
import fuckermonkey.phots.util.Utils
import java.util.ArrayList

/**
 * Created by xuxiaowu on 2017/5/24.
 */
class ImageListAdapter(context: Context) : BaseListAdapter<ImageListResult.Data>() {

    var context: Context? = null
    var mLayoutInflater: LayoutInflater? = null
    val mHeightList: ArrayList<Int> = ArrayList()

    init {
        mLayoutInflater = LayoutInflater.from(context)
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(mLayoutInflater!!.inflate(R.layout.list_item_view, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val viewHolder = holder as ViewHolder
        val data = mDataList.get(position)
        Utils.loadImage(viewHolder.coverView!!, data.image_url, R.drawable.ic_loading_rotate)

        if (mHeightList.size <= position) {
            val height = 140 + (Math.random() * 50).toInt()
            mHeightList.add(ConvertUtils.dp2px(context, height.toFloat()))
        }
        val layoutParams = viewHolder.coverView!!.layoutParams as RelativeLayout.LayoutParams
        if (position >= mHeightList.size) return
        layoutParams.height = mHeightList.get(position)
        viewHolder.coverView!!.layoutParams = layoutParams

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var coverView: ImageView? = null

        init {
            coverView = itemView.findViewById(R.id.cover_view) as ImageView
        }
    }
}