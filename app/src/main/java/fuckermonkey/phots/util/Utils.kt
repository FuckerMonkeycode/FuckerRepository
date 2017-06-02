package fuckermonkey.phots.util

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import fuckermonkey.phots.R
import fuckermonkey.phots.app.AppContext
import java.io.File

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

    /**
     * 获取Retrofit缓存文件
     */
    @JvmStatic fun getRetrofitCacheFile(): File {
        val cachePath = AppContext.instance().cacheDir.path
        val cacheDirectory = File(cachePath)
        return cacheDirectory
    }

    @JvmStatic fun setTransparentStatusBar(act: Activity) {
        val window = act.getWindow()
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        window.setStatusBarColor(Color.TRANSPARENT)
    }
}