package fuckermonkey.phots.http

import fuckermonkey.phots.model.ImageListResult
import fuckermonkey.phots.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by xuxiaowu on 2017/5/24.
 */
interface ServiceStore {

    /**
     * 获取图片列表
     * @param tag1 大分类
     * @param tag2 小分类
     * @param pn 页数
     * @param rn 每页返回内容数
     */
    @GET(Constants.BASE_URL)
    abstract fun getImageByTagAndFlag(@Query("tag1") tag: String, @Query("tag2") flags: String, @Query("pn") pn: Int, @Query("rn") rn: Int): Observable<ImageListResult>
}