package me.lmh.glide.qrcodedecoder

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import java.nio.charset.StandardCharsets
import java.util.*

class QRCode(val content: String) {

    @ColorInt
    var foreground: Int = Color.BLACK

    @ColorInt
    var background: Int = Color.TRANSPARENT

    @DrawableRes
    var logoDrawableId: Int = -1

    val hints = Hashtable<EncodeHintType, Any>()

    init {

        hints[EncodeHintType.CHARACTER_SET] = StandardCharsets.UTF_8

        hints[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.H

        hints[EncodeHintType.MARGIN] = 0

    }

    override fun toString(): String {

        return "QRCode(content='$content', foreground=$foreground, background=$background, logoDrawableId=$logoDrawableId, hints=$hints)"

    }

    override fun hashCode(): Int {

        var result = content.hashCode()

        result = 31 * result + foreground

        result = 31 * result + background

        result = 31 * result + logoDrawableId

        result = 31 * result + hints.hashCode()

        return result

    }

    override fun equals(other: Any?): Boolean {

        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as QRCode

        if (content != other.content) return false
        if (foreground != other.foreground) return false
        if (background != other.background) return false
        if (logoDrawableId != other.logoDrawableId) return false
        if (hints != other.hints) return false

        return true

    }

}
