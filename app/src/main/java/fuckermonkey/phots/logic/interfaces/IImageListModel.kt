package fuckermonkey.phots.logic.interfaces

import retrofit2.http.Query

/**
 * Created by xuxiaowu on 2017/5/24.
 */
interface IImageListModel {

    abstract fun getImageByTagAndFlag(tag: String, flags: String, pn: Int, rn: Int, isEvictReportCache: Boolean)
}