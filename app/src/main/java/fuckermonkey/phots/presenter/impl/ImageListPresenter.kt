package fuckermonkey.phots.presenter.impl

import fuckermonkey.phots.logic.impl.ImageListModel
import fuckermonkey.phots.logic.interfaces.IImageListModel
import fuckermonkey.phots.model.ImageListResult
import fuckermonkey.phots.presenter.interfaces.IImageListPresenter
import fuckermonkey.phots.ui.interfaces.IImageListView

/**
 * Created by xuxiaowu on 2017/5/24.
 */
class ImageListPresenter(imageView: IImageListView) : IImageListPresenter {

    var iImageListModel: IImageListModel? = null
    var iImageListView: IImageListView? = null

    init {
        iImageListModel = ImageListModel(this)
        iImageListView = imageView
    }

    override fun getImageListToServer(tag: String, flags: String, pn: Int, rn: Int, isEvictReportCache: Boolean) {
        iImageListModel?.getImageByTagAndFlag(tag, flags, pn, rn, isEvictReportCache)
    }

    override fun getImageListSucceed(imageListResult: ImageListResult) {
        iImageListView?.getImageListSucceed(imageListResult)
    }

    override fun getImageListFailed() {
        iImageListView?.getImageListFailed()
    }
}