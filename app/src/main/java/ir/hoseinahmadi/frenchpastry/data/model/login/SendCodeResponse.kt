package ir.hoseinahmadi.frenchpastry.data.model.login

data class SendCodeResponse(
    val expire_at: String="",
    val http_code: Int=0,
    val message: String="",
    val seconds: Int=0,
    val success: Int=0
)