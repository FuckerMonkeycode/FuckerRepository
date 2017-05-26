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
     * 获取Token
     * @param access_key 服务器秘钥access_key
     * @param secret_key 服务器秘钥secret_key
     */
    @GET(Constants.BASE_URL)
    abstract fun getImageByTagAndFlag(@Query("tag1") tag: String, @Query("tag2") flags: String, @Query("pn") pn: Int, @Query("rn") rn: Int): Observable<ImageListResult>
}