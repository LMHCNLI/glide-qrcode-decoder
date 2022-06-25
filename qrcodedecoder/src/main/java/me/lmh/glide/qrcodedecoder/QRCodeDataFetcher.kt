package me.lmh.glide.qrcodedecoder

import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.data.DataFetcher

class QRCodeDataFetcher(
    private val model: QRCode,
    private val width: Int,
    private val height: Int
) : DataFetcher<QRCode> {
    override fun loadData(
        priority: Priority,
        callback: DataFetcher.DataCallback<in QRCode>
    ) {

        if (model.content.isEmpty()) {

            callback.onLoadFailed(IllegalArgumentException("Found empty contents"))

        } else if (width < 0 || height < 0) {

            callback.onLoadFailed(IllegalArgumentException("Requested dimensions are too small: $width x $height"))

        } else {

            callback.onDataReady(model)

        }

    }

    override fun cleanup() {
    }

    override fun cancel() {
    }

    override fun getDataClass() = QRCode::class.java

    override fun getDataSource() = DataSource.LOCAL

}