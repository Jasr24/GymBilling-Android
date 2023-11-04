package com.jasrdeveloper.gymbilling.utils

import android.annotation.SuppressLint
import android.util.Base64
import java.math.BigInteger
import java.nio.charset.StandardCharsets
import java.security.Key
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class EncryptHelper {

    companion object {
        const val KEY_CUSTOMER_PASSWORD = ""
        const val IV_CUSTOMER_PASSWORD = ""

        const val KEY_DEVELOPER_AES = ""
        const val IV_DEVELOPER_AES = ""
    }

    enum class CIPHERS {
        CUSTOMER_PASSWORD, DEVELOPER_AES
    }

    @Throws(Exception::class)
    fun encrypt(string: String, ciphers: CIPHERS): String {
        val keyValue = getKey(ciphers)
        val ivValue = getIV(ciphers)
        val algorithm = getAlgorithm(ciphers)
        val padding = getPadding(ciphers)
        val ivParam: IvParameterSpec
        val key: Key
        if (ciphers == CIPHERS.CUSTOMER_PASSWORD) {
            val keyBytes: ByteArray = Base64.decode(
                KEY_CUSTOMER_PASSWORD,
                Base64.DEFAULT
            )
            val ivBytes: ByteArray = Base64.decode(
                IV_CUSTOMER_PASSWORD,
                Base64.DEFAULT
            )
            key = SecretKeySpec(keyBytes, algorithm)
            ivParam = IvParameterSpec(ivBytes)
        } else {
            key = SecretKeySpec(keyValue.toByteArray(), algorithm)
            val ivBytes = ivValue.toByteArray()
            ivParam = IvParameterSpec(ivBytes)
        }
        val cipher = Cipher.getInstance(padding)
        cipher.init(Cipher.ENCRYPT_MODE, key, ivParam)
        val encrypted = cipher.doFinal(string.toByteArray(charset("UTF-8")))
        val finalValue = String(Base64.encode(encrypted, Base64.DEFAULT), charset("UTF-8"))
        return finalValue.replace("\n", "")
    }

    @Throws(Exception::class)
    fun decrypt(messageEncrypt: String, ciphers: CIPHERS): String {
        val keyValue = getKey(ciphers)
        val ivValue = getIV(ciphers)
        val algorithm = getAlgorithm(ciphers)
        val padding = getPadding(ciphers)
        val ivParam: IvParameterSpec
        val key: Key
        if (ciphers == CIPHERS.CUSTOMER_PASSWORD) {
            val keyBytes: ByteArray = Base64.decode(
                KEY_CUSTOMER_PASSWORD,
                Base64.DEFAULT
            )
            val ivBytes: ByteArray = Base64.decode(
                IV_CUSTOMER_PASSWORD,
                Base64.DEFAULT
            )
            key = SecretKeySpec(keyBytes, algorithm)
            ivParam = IvParameterSpec(ivBytes)
        } else {
            key = SecretKeySpec(keyValue.toByteArray(), algorithm)
            val ivBytes = ivValue.toByteArray()
            ivParam = IvParameterSpec(ivBytes)
        }
        val cipher = Cipher.getInstance(padding)
        cipher.init(Cipher.DECRYPT_MODE, key, ivParam)
        val original =
            cipher.doFinal(Base64.decode(messageEncrypt, Base64.DEFAULT))
        return String(original, charset("UTF-8"))
    }

    private fun getKey(ciphers: CIPHERS): String = when (ciphers) {
        CIPHERS.CUSTOMER_PASSWORD -> KEY_CUSTOMER_PASSWORD
        CIPHERS.DEVELOPER_AES -> KEY_DEVELOPER_AES
    }

    private fun getIV(ciphers: CIPHERS): String = when (ciphers) {
        CIPHERS.CUSTOMER_PASSWORD -> IV_CUSTOMER_PASSWORD
        CIPHERS.DEVELOPER_AES -> IV_DEVELOPER_AES
    }

    private fun getAlgorithm(ciphers: CIPHERS): String = when (ciphers) {
        CIPHERS.CUSTOMER_PASSWORD -> "AES"
        CIPHERS.DEVELOPER_AES -> "AES"
    }

    private fun getPadding(ciphers: CIPHERS): String = when (ciphers) {
        CIPHERS.CUSTOMER_PASSWORD -> "AES/CBC/PKCS7Padding"
        CIPHERS.DEVELOPER_AES -> "AES/CBC/PKCS5Padding"
    }

    @SuppressLint("NewApi")
    @Throws(NoSuchAlgorithmException::class)
    fun encryptMD5(text: String): String {
        val md = MessageDigest.getInstance("MD5")
        md.update(StandardCharsets.UTF_8.encode(text))
        val digest = md.digest()
        return String.format("%032x", BigInteger(1, digest))
    }

    fun encryptSHA256(stringValue: String): String {
        return try {
            val md = MessageDigest.getInstance("SHA-256")
            md.update(stringValue.toByteArray())
            val digest = md.digest()
            val finalValue = Base64.encodeToString(digest, Base64.DEFAULT)
            finalValue.trim { it <= ' ' }.replace("[\n]{2,}".toRegex(), "")
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            ""
        }
    }
}

