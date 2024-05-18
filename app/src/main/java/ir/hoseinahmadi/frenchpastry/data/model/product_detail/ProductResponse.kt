package ir.hoseinahmadi.frenchpastry.data.model.product_detail

data class ProductResponse(
    val http_code: Int = 0,
    val message: String = "",
    val pastry: Pastry? = null,
    val success: Int = 0
)