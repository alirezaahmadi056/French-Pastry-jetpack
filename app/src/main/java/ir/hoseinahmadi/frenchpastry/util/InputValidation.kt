package ir.hoseinahmadi.frenchpastry.util

import androidx.core.text.isDigitsOnly

object InputValidation {


    fun isValidPhoneNumber(input: String): Boolean {
        return input.isNotEmpty()
                && input.isNotBlank()
                && input.isDigitsOnly()
                && input.startsWith("09")
                && input.length == 11
    }




}