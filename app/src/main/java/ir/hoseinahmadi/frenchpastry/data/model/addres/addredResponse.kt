package ir.hoseinahmadi.frenchpastry.data.model.addres

data class addredResponse(
    val addresses: List<Addresse> ?= emptyList(),
    val http_code: Int=0,
    val message: String="",
    val success: Int=0
)