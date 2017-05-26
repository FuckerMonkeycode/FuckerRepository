package fuckermonkey.phots.ui.interfaces

import fuckermonkey.phots.model.ImageListResult

/**
 * Created by xuxiaowu on 2017/5/24.
 */
interface IImageListView {

    abstract fun getImageListSucceed(imageListResult: ImageListResult)
    abstract fun getImageListFailed()
}