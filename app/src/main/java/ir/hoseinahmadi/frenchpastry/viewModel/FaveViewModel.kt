package ir.hoseinahmadi.frenchpastry.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.hoseinahmadi.frenchpastry.data.db.entites.FaveEntities
import ir.hoseinahmadi.frenchpastry.repository.FaveRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FaveViewModel @Inject constructor(
    private val repository: FaveRepository
) : ViewModel() {

    val allData: Flow<List<FaveEntities>> = repository.allData

    fun addFaveItem(item: FaveEntities) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFaveItem(item)
        }
    }

    fun removeFaveItem(item: FaveEntities) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeFaveItem(item)
        }
    }

    fun isHasBookmark(itemId: Int): Flow<Boolean> = repository.isHasBookmark(itemId)

}