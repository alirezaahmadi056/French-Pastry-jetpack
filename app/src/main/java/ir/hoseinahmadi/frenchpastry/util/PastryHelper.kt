package ir.hoseinahmadi.frenchpastry.util

import java.text.DecimalFormat

object PastryHelper {

    fun pastryByLocate(englishStr: String): String {
        var result = ""
        var fa = '۰'
        for (ch in englishStr) {
            fa = ch
            when (ch) {
                '0' -> fa = '۰'
                '1' -> fa = '۱'
                '2' -> fa = '۲'
                '3' -> fa = '۳'
                '4' -> fa = '۴'
                '5' -> fa = '۵'
                '6' -> fa = '۶'
                '7' -> fa = '۷'
                '8' -> fa = '۸'
                '9' -> fa = '۹'
            }
            result = "${result}$fa"
        }
        return result
    }

    fun pastryByLocateAndSeparator(price: String): String {
        val priceWithoutCommas = price.replace(",", "")
        // اول عدد را به صورت اعداد انگلیسی جداسازی کن
        val formattedPrice = pastryBySeparator(priceWithoutCommas)
        // سپس اعداد جدا شده را به فارسی تبدیل کن
        return pastryByLocate(formattedPrice)
    }

    fun pastryBySeparator(price: String): String {
        val priceFormat = DecimalFormat("###,###")
        return priceFormat.format(Integer.valueOf(price))
    }


    fun applyDiscount(price: Long, discountPercent: Int): Long {
        return if (discountPercent > 0) {
            val discountAmount = (price * discountPercent) / 100
            price - discountAmount
        } else {
            price
        }
    }
    fun gregorianToJalali(gy: Int, gm: Int, gd: Int): String {
        val gDaysInMonth: IntArray = intArrayOf(0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334)
        val gy2: Int = if (gm > 2) (gy + 1) else gy
        var gTotalDays: Int = 355666 + (365 * gy) + ((gy2 + 3) / 4) - ((gy2 + 99) / 100) + ((gy2 + 399) / 400) + gd + gDaysInMonth[gm - 1]
        var jy: Int = -1595 + (33 * (gTotalDays / 12053))
        gTotalDays %= 12053
        jy += 4 * (gTotalDays / 1461)
        gTotalDays %= 1461
        if (gTotalDays > 365) {
            jy += ((gTotalDays - 1) / 365)
            gTotalDays = (gTotalDays - 1) % 365
        }
        val jm: Int
        val jd: Int
        if (gTotalDays < 186) {
            jm = 1 + (gTotalDays / 31)
            jd = 1 + (gTotalDays % 31)
        } else {
            jm = 7 + ((gTotalDays - 186) / 30)
            jd = 1 + ((gTotalDays - 186) % 30)
        }
        return "$jy/$jm/$jd"
    }



}

