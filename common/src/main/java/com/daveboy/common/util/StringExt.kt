package com.daveboy.common.util

import android.text.Html
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT

fun String.fromHtml()= HtmlCompat.fromHtml(this,FROM_HTML_MODE_COMPACT)