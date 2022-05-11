package com.example.wanandroid_k_m_j.ui.main.answer

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import java.util.ArrayList

@Keep
@Parcelize
data class AnswerListEntity(
    var curPage: Long = 0,
    var pageCount: Int = 0,
    var size: Int = 0,
    var total: Int = 0,
    var datas: ArrayList<AnswersEntity> = ArrayList()

) : Parcelable

@Keep
@Parcelize
data class AnswersEntity(
    val apkLink: String,
    val audit: Int,
    val author: String,
    val canEdit: Boolean,
    val chapterId: Int,
    val chapterName: String,
    val collect: Boolean,
    val courseId: Int,
    val desc: String,
    val descMd: String,
    val envelopePic: String,
    val fresh: Boolean,
    val host: String,
    val id: Int,
    val link: String,
    val niceDate: String,
    val niceShareDate: String,
    val origin: String,
    val prefix: String,
    val projectLink: String,
    val publishTime: Long,
    val realSuperChapterId: Int,
    val selfVisible: Int,
    val shareDate: Long,
    val shareUser: String,
    val superChapterId: Int,
    val superChapterName: String,
    val tags: List<Tag>,
    val title: String,
    val type: Int,
    val userId: Int,
    val visible: Int,
    val zan: Int
) : Parcelable

@Keep
@Parcelize
data class Tag(
    val name: String,
    val url: String
) : Parcelable