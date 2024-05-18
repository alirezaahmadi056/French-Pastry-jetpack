package ir.hoseinahmadi.frenchpastry.data.model.product_detail

data class Reply(
    val ID: Int,
    val avatar: String,
    val body: String,
    val date: String,
    val date_i18n: String,
    val name: String,
    val rate: Int,
    val replies: List<ReplyX>
)