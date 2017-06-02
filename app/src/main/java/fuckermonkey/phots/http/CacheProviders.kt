package fuckermonkey.phots.http

import fuckermonkey.phots.model.ImageListResult
import io.rx_cache.DynamicKeyGroup
import io.rx_cache.EvictDynamicKey
import io.rx_cache.LifeCache
import io.rx_cache.Reply
import rx.Observable
import java.util.concurrent.TimeUnit

/**
 * Created by xuxiaowu on 2017/5/27.
 */
interface CacheProviders {

    /**
     * 获取图片列表
     * 缓存60分钟
     * @param oRepos          Observable
     * @param dynamicKeyGroup 缓存唯一标识的特定的键
     * @param evictDynamicKey 是否驱逐缓存
     */
    @LifeCache(duration = 60, timeUnit = TimeUnit.MINUTES)
    abstract fun getImageByTagAndFlag(oRepos: Observable<ImageListResult>, dynamicKeyGroup: DynamicKeyGroup, evictDynamicKey: EvictDynamicKey): Observable<Reply<ImageListResult>>
}