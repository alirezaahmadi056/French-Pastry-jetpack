package ir.hoseinahmadi.frenchpastry.data.model.login

data class VerifyCodeResponse(
    val api: String="",
    val http_code: Int=0,
    val message: String="",
    val success: Int=0,
    val user: User=User()
)