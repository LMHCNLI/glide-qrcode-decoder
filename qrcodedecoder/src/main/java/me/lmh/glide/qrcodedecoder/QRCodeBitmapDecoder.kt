package me.lmh.glide.qrcodedecoder

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.set
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.ResourceDecoder
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapResource
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import kotlin.math.abs

class QRCodeBitmapDecoder(
    private val resource: Resources,
    private val theme: Resources.Theme,
    private val bitmapPool: BitmapPool
) : ResourceDecoder<QRCode, Bitmap> {

    private val writer = QRCodeWriter()

    override fun handles(source: QRCode, options: Options): Boolean {

        return source.content.isNotEmpty()

    }

    override fun decode(
        source: QRCode,
        width: Int,
        height: Int,
        options: Options
    ): Resource<Bitmap>? {

        val matrix = writer.encode(
            source.content,
            BarcodeFormat.QR_CODE,
            width,
            height,
            source.hints
        )

        val bitmap = bitmapPool.get(width, height, Bitmap.Config.ARGB_8888)

        for (x in 0 until width) {

            for (y in 0 until height) {

                bitmap[x, y] = if (matrix[x, y]) source.foreground else source.background

            }

        }

        createLogoBitmap(source.logoDrawableId, width, height)?.let { logoBitmap ->

            val canvas = Canvas(bitmap)

            canvas.density = logoBitmap.density

            val paint = Paint()

            val top = abs(height - logoBitmap.height) / 2F

            val left = abs(width - logoBitmap.width) / 2F

            canvas.drawBitmap(logoBitmap, left, top, paint)

            canvas.save()

            canvas.restore()

            logoBitmap.recycle()

        }

        return BitmapResource.obtain(bitmap, bitmapPool)

    }

    private fun createLogoBitmap(@DrawableRes logoResId: Int, width: Int, height: Int): Bitmap? {

        if (logoResId == -1) return null

        val logoDrawable = ResourcesCompat.getDrawable(resource, logoResId, theme) ?: return null

        val targetWidth = width / 7.5F

        val targetHeight = height / 7.5F

        return if (logoDrawable.intrinsicWidth > targetWidth
            || logoDrawable.intrinsicHeight > targetHeight
        ) logoDrawable.toBitmap(
            targetWidth.toInt(),
            targetHeight.toInt()
        ) else logoDrawable.toBitmap()

    }

}
