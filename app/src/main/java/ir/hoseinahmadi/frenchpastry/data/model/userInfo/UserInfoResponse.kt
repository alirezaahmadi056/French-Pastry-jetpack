package ir.hoseinahmadi.frenchpastry.data.model.userInfo

data class UserInfoResponse(
    val http_code: Int?=null,
    val message: String?=null,
    val success: Int?=null,
    val user: User ?=null
)