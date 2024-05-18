package ir.hoseinahmadi.frenchpastry.data.model.home

data class Pastry(
    val ID: String="",
    val title: String="",
    val pastries: List<PastryItem> = emptyList(),
)