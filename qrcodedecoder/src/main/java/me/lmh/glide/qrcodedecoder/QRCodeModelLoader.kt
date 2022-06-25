package me.lmh.glide.qrcodedecoder

import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import com.bumptech.glide.signature.ObjectKey

class QRCodeModelLoader : ModelLoader<QRCode, QRCode> {

    override fun buildLoadData(
        model: QRCode,
        width: Int,
        height: Int,
        options: Options
    ): ModelLoader.LoadData<QRCode> {

        return ModelLoader.LoadData(ObjectKey(model), QRCodeDataFetcher(model, width, height))

    }

    override fun handles(model: QRCode): Boolean {

        return true

    }

    class Factory : ModelLoaderFactory<QRCode, QRCode> {

        override fun build(multiFactory: MultiModelLoaderFactory): ModelLoader<QRCode, QRCode> {

            return QRCodeModelLoader()

        }

        override fun teardown() {}

    }

}