package fuckermonkey.phots.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.jcodecraeer.xrecyclerview.XRecyclerView.LoadingListener
import fuckermonkey.phots.R
import fuckermonkey.phots.adapter.ImageListAdapter
import fuckermonkey.phots.adapter.SpacesItemDecoration
import fuckermonkey.phots.model.ImageListResult
import fuckermonkey.phots.presenter.impl.ImageListPresenter
import fuckermonkey.phots.ui.base.BaseFragment
import fuckermonkey.phots.ui.interfaces.IImageListView
import fuckermonkey.phots.util.Constants
import fuckermonkey.phots.util.SpUtils

/**
 * Created by xuxiaowu on 2017/5/24.
 */
class TypeFragment(tag: String, flag: String) : BaseFragment(), IImageListView, LoadingListener {

    val TAG = "TypeFragment"

    var mListView: XRecyclerView? = null
    var mStatusView: View? = null

    var mImageListPresenter: ImageListPresenter? = null
    var mImageListAdapter: ImageListAdapter? = null
    var mStaggeredGridLayoutManager: StaggeredGridLayoutManager? = null
    var mPage = 0
    var mIsRefresh = false
    var mTag: String? = null
    var mFlag: String? = null

    init {
        mTag = tag
        mFlag = flag
    }

    override fun initFragment() {
        mImageListAdapter = ImageListAdapter(getContext())
        val column = SpUtils.get(Constants.SP_COLUMN, 3)
        mStaggeredGridLayoutManager = StaggeredGridLayoutManager(column, StaggeredGridLayoutManager.VERTICAL)
        setupListView()

        mImageListPresenter = ImageListPresenter(this)
        (mImageListPresenter as ImageListPresenter).getImageListToServer(mTag!!, mFlag!!, mPage, 20, false)

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_type_view
    }

    override fun initView() {
        mListView = rootView.findViewById(R.id.list_view) as XRecyclerView
        mStatusView = rootView.findViewById(R.id.status_view)
    }

    override fun setViewListener() {

    }

    override fun OnClick(v: View?) {

    }

    override fun getImageListSucceed(imageListResult: ImageListResult) {

        mStatusView!!.visibility = View.GONE
        imageListResult.data.removeAt(imageListResult.data.size - 1)

        if (mIsRefresh) {
            mImageListAdapter?.removeHeadData(imageListResult.data.size)
            mImageListAdapter?.addAll(0, imageListResult.data)
            mListView?.refreshComplete()
            return
        }

        if (mPage == 0) {
            mImageListAdapter?.setDataList(imageListResult.data)
        } else {
            mImageListAdapter?.addAll(imageListResult.data)
            mListView?.loadMoreComplete()
        }

    }

    override fun getImageListFailed() {
        Toast.makeText(getContext(), R.string.loading_data_error, Toast.LENGTH_SHORT).show()
    }

    override fun onLoadMore() {
        mIsRefresh = false
        mPage += 20 //由于有重复所以跳过20页
        (mImageListPresenter as ImageListPresenter).getImageListToServer(mTag!!, mFlag!!, mPage, 20, false)
    }

    override fun onRefresh() {
        mIsRefresh = true
        (mImageListPresenter as ImageListPresenter).getImageListToServer(mTag!!, mFlag!!, 0, 20, true)
    }

    fun setupListView() {
        mListView?.layoutManager = mStaggeredGridLayoutManager
        mListView?.adapter = mImageListAdapter
        mListView?.setPullRefreshEnabled(true)
        mListView?.setLoadingMoreEnabled(true)
        mListView?.setLoadingListener(this)
        mListView?.addItemDecoration(SpacesItemDecoration(5))

    }

    fun updateData(tag: String, flag: String, dataList: List<ImageListResult.Data>) {
        if (mImageListPresenter == null) return
        mTag = tag
        mFlag = flag
        mImageListAdapter!!.setDataList(dataList)

    }

    fun reloadData(tag: String, flag: String) {
        mTag = tag
        mFlag = flag
        mIsRefresh = false
        mPage = 0
        (mImageListPresenter as ImageListPresenter).getImageListToServer(mTag!!, mFlag!!, mPage, 20, false)
    }

}