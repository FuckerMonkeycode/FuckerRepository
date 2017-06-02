package fuckermonkey.phots.adapter

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by xuxiaowu on 2017/5/25.
 */
class SpacesItemDecoration(space: Int) : RecyclerView.ItemDecoration() {

    var space: Int? = null

    init {
        this.space = space
    }


    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        outRect?.left = space
        outRect?.right = space
        outRect?.bottom = space
        outRect?.top = space

    }
}