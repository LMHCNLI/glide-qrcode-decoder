# Glide QR Code Decoder

## 简介

本库为 [`GlideV4`](https://github.com/bumptech/glide) 提供了一个使用 [`ZXing`](https://github.com/zxing/zxing/) 解码 `QRCode` 的模块，用于直接生成二维码图片。
利用了 `Glide` 本身的缓存优势及内部的  `BitmapPool` 机制，省去手动管理 `Bitmap` 的生成、释放和复用。

## 安装

Step 1. Add the JitPack repository to your build file

```gradle
repositories {
    // other repositories ...
    maven { url 'https://jitpack.io' }
}
```

Step 2. Add the dependency

```gradle
dependencies {
    implementation 'com.github.LMHCNLI:glide-qrcode-decoder:1.0.1'
}
```

Step 3. Register Component

```kotlin
@GlideModule
class MyGlideModule : AppGlideModule() {

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
    
        // other components ...

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

}
```

## 使用

```kotlin
Glide.with(context).load("QRCode content".toQRCode()).into(imageView)
```

或

```kotlin
Glide.with(context).loadQRCode("QRCode content").into(imageView)
```

## Links

本库引用了以下项目

- [Glide](https://github.com/bumptech/glide)
- [ZXing](https://github.com/zxing/zxing/)

## License

```license
MIT License

Copyright (c) 2022 LMHCNLI

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
