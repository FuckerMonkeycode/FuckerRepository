package fuckermonkey.phots.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import fuckermonkey.phots.R
import fuckermonkey.phots.app.AppContext

/**
 * Created by xuxiaowu on 2017/5/25.
 */
object Utils {

    @JvmStatic fun loadImage(imageView: ImageView, url: String, errorImage: Int) {
        val context: Context
        if (Thread.currentThread().name == "main") {
            context = imageView.context
        } else {
            context = AppContext.instance()
        }
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(errorImage)
//                .placeholder(R.drawable.ic_menu_gallery)
                .into(imageView)
    }
}