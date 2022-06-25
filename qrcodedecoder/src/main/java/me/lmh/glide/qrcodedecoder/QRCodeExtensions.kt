@file:Suppress("unused")

package me.lmh.glide.qrcodedecoder

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager

fun String.toQRCode() = QRCode(this)

fun Any.toQRCode() = QRCode(toString())

fun RequestManager.loadQRCode(content: String) = load(content.toQRCode())

fun ImageView.load(qrCode: QRCode) = Glide.with(context).load(qrCode).into(this)

fun ImageView.loadQRCode(content: String) = load(content.toQRCode())
