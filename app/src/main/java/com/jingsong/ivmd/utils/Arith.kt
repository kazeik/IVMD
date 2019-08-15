package com.jingsong.patient.utils

import java.math.BigDecimal

/**
 * 由于Java的简单类型不能够精确的对浮点数进行运算，这个工具类提供精
 * 确的浮点数运算，包括加减乘除和四舍五入。
 */
object Arith { //默认除法运算精度
    private val DEF_DIV_SCALE = 10 //这个类不能实例化
    /**
     * 提供精确的加法运算。
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    fun add(v1: Double, v2: Double): Double {
        val b1 = BigDecimal(java.lang.Double.toString(v1))
        val b2 = BigDecimal(java.lang.Double.toString(v2))
        return b1.add(b2).toDouble()
    }

    fun add(v1: String, v2: String): Double {
        val b1 = BigDecimal(v1)
        val b2 = BigDecimal(v2)
        return b1.add(b2).toDouble()
    }

    /**
     * 提供精确的减法运算。
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    fun sub(v1: String, v2: String): Double {
        val b1 = BigDecimal(v1)
        val b2 = BigDecimal(v2)
        return b1.subtract(b2).toDouble()
    }

    fun sub(v1: Double, v2: Double): Double {
        return sub(v1.toString(),v2.toString())
    }

    fun sub(v1: Float, v2: Float): Float {
        val b1 = BigDecimal(v1.toString())
        val b2 = BigDecimal(v2.toString())
        return b1.subtract(b2).toFloat()
    }

    /**
     * 提供精确的乘法运算。
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    fun mul(v1: String, v2: String): Double {
        val b1 = BigDecimal(v1)
        val b2 = BigDecimal(v2)
        return b1.multiply(b2).toDouble()
    }

    fun mul(v1: Float, v2: Float): Float {
        val b1 = BigDecimal(v1.toString())
        val b2 = BigDecimal(v2.toString())
        return b1.multiply(b2).toFloat()
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    @JvmOverloads
    fun div(v1: String, v2: String, scale: Int = DEF_DIV_SCALE): Double {
        if (scale < 0) {
            throw IllegalArgumentException(
                    "The scale must be a positive integer or zero")
        }
        val b1 = BigDecimal(v1)
        val b2 = BigDecimal(v2)
        return b2.divide(b1, scale, BigDecimal.ROUND_HALF_UP).toDouble()
    }

    @JvmOverloads
    fun div(v1: Float, v2: Float, scale: Int = DEF_DIV_SCALE): Float {
        if (scale < 0) {
            throw IllegalArgumentException(
                    "The scale must be a positive integer or zero")
        }
        val b1 = BigDecimal(v1.toString())
        val b2 = BigDecimal(v2.toString())
        return b2.divide(b1, scale, BigDecimal.ROUND_HALF_UP).toFloat()
    }

    /**
     * 提供精确的小数位四舍五入处理。
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    fun round(v: Double, scale: Int): Double {
        if (scale < 0) {
            throw IllegalArgumentException("The scale must be a positive integer or zero")
        }
        val b = BigDecimal(java.lang.Double.toString(v))
        val one = BigDecimal("1")
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).toDouble()
    }

}
