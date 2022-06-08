@file:JvmName("ContextKt")

package com.example.wanandroid_k_m_j.exts

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.annotation.*
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import com.example.wanandroid_k_m_j.WanAndroidApp
import com.google.android.material.color.MaterialColors
import java.io.InputStream
import kotlin.properties.ReadOnlyProperty

val globalContext: Context get() = WanAndroidApp.get()

inline fun <reified T : Any> Context.requireSystemService(): T {
    return checkNotNull(applicationContext.getSystemService())
}

inline fun <reified T : Any> Context.systemService(): ReadOnlyProperty<Context, T> {
    return ReadOnlyProperty { _, _ -> requireSystemService() }
}

fun Context.launchActivity(intent: Intent) {
    intent.runCatching(this::startActivity).onFailure(Throwable::printStackTrace)
}

fun Context.startService(intent: Intent, foreground: Boolean) {
    if (foreground) {
        ContextCompat.startForegroundService(this, intent)
    } else {
        this.startService(intent)
    }
}

fun Context.checkPermissions(vararg permissions: String): Boolean {
    for (permission in permissions) {
        if (ContextCompat.checkSelfPermission(
                this,
                permission
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return false
        }
    }
    return true
}


fun Context.assets(fileName: String): InputStream {
    return assets.open(fileName)
}

fun <T : Drawable> Context.requireDrawable(@DrawableRes drawableId: Int, @Px size: Int = 0): T {
    return requireDrawable(drawableId, size, size)
}

@Suppress("UNCHECKED_CAST")
fun <T : Drawable> Context.requireDrawable(
    @DrawableRes drawableId: Int,
    @Px width: Int = 0,
    @Px height: Int = 0,
): T {
    val drawable = AppCompatResources.getDrawable(this, drawableId)!!
    var right = width
    if (right <= 0) {
        right = drawable.intrinsicWidth
    }
    var bottom = height
    if (bottom <= 0) {
        bottom = drawable.intrinsicHeight
    }
    drawable.setBounds(0, 0, right, bottom)
    return checkNotNull(drawable as T)
}

@ColorInt
fun Context.color(@ColorRes colorId: Int): Int = ContextCompat.getColor(this, colorId)

@ColorInt
fun Context.color(@AttrRes attrId: Int, @ColorInt default: Int): Int {
    return MaterialColors.getColor(this, attrId, default)
}

fun Context.tel(tel: String) {
    var market = Intent(Intent.ACTION_DIAL)
        .setData(Uri.parse("tel:$tel"))
        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    market = Intent.createChooser(market, null)
    ContextCompat.startActivity(this, market, null)
}

fun Context.market(packageName: String) {
    var market = Intent(Intent.ACTION_VIEW)
        .setData(Uri.parse("market://details?id=$packageName"))
        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    market = Intent.createChooser(market, null)
    ContextCompat.startActivity(this, market, null)
}

fun Context.copy(text: String) {
    val clipboardManager = this.requireSystemService<ClipboardManager>()
    clipboardManager.setPrimaryClip(ClipData.newPlainText("text", text))
}

fun Context.copy(@StringRes textId: Int) {
    this.copy(this.getString(textId))
}
