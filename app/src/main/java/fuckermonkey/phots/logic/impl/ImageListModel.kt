package fuckermonkey.phots.logic.impl

import fuckermonkey.phots.http.ServiceStore
import fuckermonkey.phots.logic.interfaces.IImageListModel
import fuckermonkey.phots.model.ImageListResult
import fuckermonkey.phots.presenter.interfaces.IImageListPresenter
import fuckermonkey.phots.util.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.fastjson.FastJsonConverterFactory
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by xuxiaowu on 2017/5/24.
 */
class ImageListModel(iImagePresenter: IImageListPresenter): IImageListModel {

    var imageListResult: ImageListResult? = null

    override fun getImageByTagAndFlag(tag: String, flags: String, pn: Int, rn: Int) {
        val retrofit = Retrofit.Builder()
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build()
        val serviceStore = retrofit.create(ServiceStore::class.java!!)
        serviceStore.getImageByTagAndFlag(tag, flags, pn, rn)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mCollectArticleObservable)
    }

    private val mCollectArticleObservable = object : Observer<ImageListResult> {

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

        override fun onNext(o: ImageListResult) {
            imageListResult = o
        }
    }
}