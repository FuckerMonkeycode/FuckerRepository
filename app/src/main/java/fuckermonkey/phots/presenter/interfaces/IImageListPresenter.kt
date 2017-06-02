package fuckermonkey.phots.presenter.interfaces

import fuckermonkey.phots.model.ImageListResult

/**
 * Created by xuxiaowu on 2017/5/24.
 */
interface IImageListPresenter {

    abstract fun getImageListToServer(tag: String, flags: String, pn: Int, rn: Int, isEvictReportCache: Boolean)
    abstract fun getImageListSucceed(imageListResult: ImageListResult)
    abstract fun getImageListFailed()
}