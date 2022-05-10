package com.example.wanandroid_k_m_j.ui.main.home

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.wanandroid.common.toJsonArray
import com.wanandroid.common.toJsonString
import io.objectbox.annotation.*
import io.objectbox.converter.PropertyConverter
import kotlinx.parcelize.Parcelize
import java.util.ArrayList

/**
 * @author TangRui
 * @description: 首页文章数据
 * @link:
 * @date:2022/3/2 14:48
 */

@Keep
@Parcelize
data class ArticleEntity0(
    var bannerList: ArrayList<BannerData> = ArrayList(),
    var toparticleList: ArrayList<ArticleDataEntity> = ArrayList(),
    var articleList: ArticleEntity = ArticleEntity()
) : Parcelable

@Keep
@Entity
@Parcelize
data class ArticleEntity(
    @Id
    var dbId: Long = 0,
    @Unique(onConflict = ConflictStrategy.REPLACE)
    var curPage: Long = 0,
    var offset: Int = 0,
    var isOver: Boolean = false,
    var pageCount: Int = 0,
    var size: Int = 0,
    var total: Int = 0,
    @SerializedName("datas")
    @Convert(converter = ArticleListConvert::class, dbType = String::class)
    var articleList: ArrayList<ArticleDataEntity> = ArrayList()

) : Parcelable

@Keep
@Parcelize
data class ArticleDataEntity(
    var id: Long = 0,
    var author: String = "",
    var shareUser: String = "",
    var niceDate: String = "",
    var title: String = "",
    var superChapterName: String = "",
    var chapterName: String = "",
    var link: String = "",
    var collect: Boolean = false,
    var fresh: Boolean = false,
    var top: Boolean = false,
    var tags: ArrayList<ArticleTagEntity> = ArrayList()
) : Parcelable {
    override fun toString(): String {
        return this.toJsonString()
    }
}

@Keep
@Parcelize
data class ArticleTagEntity(
    val name: String = "",
    val url: String = ""
) : Parcelable {
    override fun toString(): String {
        return this.toJsonString()
    }
}

@Keep
@Parcelize
data class BannerData(
    val desc: String = "",
    val id: Int,
    val imagePath: String = "",
    val isVisible: Int,
    val order: Int,
    val title: String = "",
    val type: Int,
    val url: String = ""
) : Parcelable {
    override fun toString(): String {
        return this.toJsonString()
    }
}

/**
 * List <==> String
 */
class ArticleListConvert : PropertyConverter<List<ArticleDataEntity>, String> {
    override fun convertToDatabaseValue(entityProperty: List<ArticleDataEntity>?): String {
        return entityProperty.toJsonString()
    }


    override fun convertToEntityProperty(databaseValue: String?): List<ArticleDataEntity>? {
        return databaseValue.toJsonArray()
    }

}