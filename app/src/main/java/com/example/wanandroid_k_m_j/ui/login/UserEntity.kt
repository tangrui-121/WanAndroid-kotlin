package com.example.wanandroid_k_m_j.ui.login

import android.os.Parcelable
import androidx.annotation.Keep
import com.wanandroid.common.toJsonString
import io.objectbox.annotation.ConflictStrategy
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.annotation.Unique
import kotlinx.parcelize.Parcelize

/**
 * @author TangRui
 * @description:
 * @date:2022/2/23 17:05
 */
@Keep
@Entity
@Parcelize
data class UserEntity(
    @Id
    var dbId: Long = 0,
    @Unique(onConflict = ConflictStrategy.REPLACE)
    val username: String? = ""
) : Parcelable {
    override fun toString(): String {
        return this.toJsonString()
    }
}