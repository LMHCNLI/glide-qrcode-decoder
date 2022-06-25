package me.lmh.qrcode

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import me.lmh.glide.qrcodedecoder.QRCode
import me.lmh.glide.qrcodedecoder.QRCodeBitmapDecoder
import me.lmh.glide.qrcodedecoder.QRCodeModelLoader
import me.lmh.glide.qrcodedecoder.loadQRCode

@GlideModule
class MyGlideModule : AppGlideModule() {

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {

        super.registerComponents(context, glide, registry)

        registry.append(
            Registry.BUCKET_BITMAP,
            QRCode::class.java,
            Bitmap::class.java,
            QRCodeBitmapDecoder(context.resources, context.theme, glide.bitmapPool)
        )

        registry.append(
            QRCode::class.java,
            QRCode::class.java,
            QRCodeModelLoader.Factory()
        )

    }

    companion object {

        @JvmStatic
        @BindingAdapter(value = ["qr_code_content"])
        fun loadQRCode(imageView: ImageView, content: String?) {

            if (TextUtils.isEmpty(content)) {

                GlideApp.with(imageView.context).load(ColorDrawable(Color.WHITE)).into(imageView)

            } else {

                GlideApp.with(imageView.context).loadQRCode(content!!).into(imageView)

            }

        }

    }

}