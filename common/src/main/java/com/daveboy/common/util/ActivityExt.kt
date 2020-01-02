package com.daveboy.common.util

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * 跳转
 */
inline fun <reified TARGET : Activity> FragmentActivity.startActivityExt(
    vararg params: Pair<String, Any>
) = startActivity(Intent(this, TARGET::class.java).putExtras(*params))

/**
 * 获取Intent参数
 */
fun <T> extraAct(extraName: String): ActivityExtras<T?> = ActivityExtras(extraName, null)

fun <T> extraAct(extraName: String, defaultValue: T): ActivityExtras<T> =
    ActivityExtras(extraName, defaultValue)



/**
 * 获取Intent参数，Activity
 * 示例：
 *  <p>
 *
 *      private var str: String? by extraAct("String")
 *      private var str1 by extraAct("String1", "123")
 *      private var int1 by extraAct("Int1", 123)
 *
 *      Log.e("TestActivity", "str---------$str") // get
 *      str = "str" // set
 *      Log.e("TestActivity", "str1---------$str1") // get
 *      str1 = "str1" // set
 *      Log.e("TestActivity", "int1---------$int1") // get
 *      int1 = 1000 // set
 * </p>
 *
 * @author Jowan
 * Created on 2019/8/15.
 */
class ActivityExtras<T>(private val extraName: String, private val defaultValue: T) :
    ReadWriteProperty<Activity, T> {

    /**
     * getExtras字段对应的值
     */
    private var extra: T? = null

    override fun getValue(thisRef: Activity, property: KProperty<*>): T {
        // 如果extra不为空则返回extra
        // 如果extra是空的，则判断intent的参数的值，如果值不为空，则将值赋予extra，并且返回
        // 如果intent参数的值也为空，则返回defaultValue，并且将值赋予extra
        return extra ?: thisRef.intent?.get<T>(extraName)?.also { extra = it }
        ?: defaultValue.also { extra = it }
    }

    override fun setValue(thisRef: Activity, property: KProperty<*>, value: T) {
        extra = value
    }
}

