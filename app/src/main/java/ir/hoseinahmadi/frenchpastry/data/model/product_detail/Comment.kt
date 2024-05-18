package ir.hoseinahmadi.frenchpastry.data.model.product_detail

data class Comment(
    val ID: Int,
    val avatar: String,
    val body: String,
    val date: String,
    val date_i18n: String,
    val name: String,
    val rate: Int,
    val replies: List<Reply>
)