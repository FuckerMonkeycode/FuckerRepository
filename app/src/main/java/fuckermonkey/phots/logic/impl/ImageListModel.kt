package fuckermonkey.phots.logic.impl

import fuckermonkey.phots.http.CacheProviders
import fuckermonkey.phots.http.ServiceStore
import fuckermonkey.phots.logic.interfaces.IImageListModel
import fuckermonkey.phots.model.ImageListResult
import fuckermonkey.phots.presenter.interfaces.IImageListPresenter
import fuckermonkey.phots.util.Constants
import fuckermonkey.phots.util.Utils
import io.rx_cache.DynamicKeyGroup
import io.rx_cache.EvictDynamicKey
import io.rx_cache.Reply
import io.rx_cache.internal.RxCache
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.fastjson.FastJsonConverterFactory
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by xuxiaowu on 2017/5/24.
 */
class ImageListModel(iImagePresenter: IImageListPresenter) : IImageListModel {

    var imageListResult: ImageListResult? = null

    override fun getImageByTagAndFlag(tag: String, flags: String, pn: Int, rn: Int, isEvictReportCache: Boolean) {
        val retrofit = Retrofit.Builder()
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build()

        val serviceStore = retrofit.create(ServiceStore::class.java)
        val observable = serviceStore.getImageByTagAndFlag(tag, flags, pn, rn)

        val cacheProviders = RxCache.Builder()
                .persistence(Utils.getRetrofitCacheFile())
                .using<CacheProviders>(CacheProviders::class.java!!)
        cacheProviders.getImageByTagAndFlag(observable, DynamicKeyGroup(flags, pn), EvictDynamicKey(isEvictReportCache))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mGetDayReportObservable)
    }

    private val mGetDayReportObservable = object : Observer<Reply<ImageListResult>> {

        override fun onCompleted() {
            if (imageListResult == null) {
                iImagePresenter.getImageListFailed()
            } else {
                iImagePresenter.getImageListSucceed(imageListResult as ImageListResult)
            }
        }

        override fun onError(e: Throwable) {
            iImagePresenter.getImageListFailed()
        }

        override fun onNext(o: Reply<ImageListResult>) {
            imageListResult = o.data
        }
    }
}