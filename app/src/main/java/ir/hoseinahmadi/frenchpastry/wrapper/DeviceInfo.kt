package ir.hoseinahmadi.frenchpastry.wrapper

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.provider.Settings
import ir.hoseinahmadi.frenchpastry.util.Constants
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.math.BigInteger
import java.security.MessageDigest

class DeviceInfo {

    companion object {

        private const val PRIVATE_KEY = "NKFewSfScCRrbxzULrSivWAXq2yvGd"

        private var deviceId: String? = null
        private var publicKey: String? = null
        private var publicKeyWithout: String? = null

        @SuppressLint("HardwareIds")
        fun getDeviceID(context: Context): String {

            if (deviceId == null)
                deviceId = Settings.Secure.getString(
                    context.contentResolver,
                    Settings.Secure.ANDROID_ID
                )

            return deviceId ?: ""

        }

        fun getPublicKey(context: Context): String {

            val apiKey = Constants.API_KEY

            val input = PRIVATE_KEY + getDeviceID(context) + apiKey
            val md = MessageDigest.getInstance("MD5")

                publicKey = BigInteger(
                    1,
                    md.digest(input.toByteArray())
                ).toString(16).padStart(32, '0')

            return publicKey ?: ""

        }

        fun getPublicKeyWithoutApi(context: Context): String {

            val input = PRIVATE_KEY + getDeviceID(context)
            val md = MessageDigest.getInstance("MD5")

            if (publicKeyWithout == null)
                publicKeyWithout = BigInteger(
                    1,
                    md.digest(input.toByteArray())
                ).toString(16).padStart(32, '0')

            return publicKeyWithout ?: ""

        }

    }

    val getDeviceName = "${Build.BRAND} | ${Build.MODEL}"

    val getAndroidVersion = "API Level : ${Build.VERSION.SDK_INT} | V : ${Build.VERSION.RELEASE}"


}