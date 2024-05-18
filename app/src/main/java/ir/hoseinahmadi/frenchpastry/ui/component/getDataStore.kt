package ir.hoseinahmadi.frenchpastry.ui.component

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import ir.hoseinahmadi.frenchpastry.util.Constants.API_KEY
import ir.hoseinahmadi.frenchpastry.util.Constants.CHECKED_LOGIN
import ir.hoseinahmadi.frenchpastry.util.Constants.USER_NAME
import ir.hoseinahmadi.frenchpastry.util.Constants.USER_PHONE
import ir.hoseinahmadi.frenchpastry.viewModel.DatStoreViewModel

@Composable
fun AppConfig(
    datStoreViewModel: DatStoreViewModel = hiltViewModel(),
) {
    CHECKED_LOGIN = datStoreViewModel.getUserLogin()
    USER_PHONE = datStoreViewModel.getUserPhone()
    API_KEY = datStoreViewModel.getUserApiKey()
   USER_NAME =datStoreViewModel.getUserName()

}



