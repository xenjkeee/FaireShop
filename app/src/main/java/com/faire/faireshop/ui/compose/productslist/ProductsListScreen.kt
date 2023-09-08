package com.faire.faireshop.ui.compose.productslist

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.icu.text.NumberFormat
import android.icu.util.Currency
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.faire.faireshop.R
import com.faire.faireshop.data.model.PriceInfo
import com.faire.faireshop.data.model.ProductInfo
import com.faire.faireshop.ui.theme.FaireShopTheme

@Composable
fun ProductsListScreen(
    viewModel: ProductsListViewModel = hiltViewModel()
) = ProductsListScreen(
    state = viewModel.state.collectAsState().value,
    handleAction = viewModel::handleAction
)

@Composable
private fun ProductsListScreen(
    state: ProductsListState,
    handleAction: (ProductsListAction) -> Unit
) = Box(Modifier.fillMaxSize()) {
    when (state) {
        ProductsListState.Empty -> Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.empty_content))
            TextButton(onClick = { handleAction(ProductsListAction.Reload) }) {
                Text(stringResource(id = R.string.reload))
            }
        }
        ProductsListState.Loading -> Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.loading))
        }
        ProductsListState.LoadingFailed -> Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.loading_failed))
            TextButton(onClick = { handleAction(ProductsListAction.Reload) }) {
                Text(stringResource(id = R.string.reload))
            }
        }
        is ProductsListState.Success -> LazyColumn() {
            itemsIndexed(state.products) { index, item ->
                ProductItem(product = item)
                if (index < state.products.lastIndex) Divider(Modifier.padding(16.dp))
            }
        }
    }
}

@Preview(group = "Light")
@Preview(group = "Night", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun PreviewProductsListScreen(
    @PreviewParameter(SampleListStateProvider::class) state: ProductsListState
) = FaireShopTheme {
    Surface {
        ProductsListScreen(state) {}
    }
}

private class SampleListStateProvider : PreviewParameterProvider<ProductsListState> {
    override val values = sequenceOf(
        ProductsListState.Empty,
        ProductsListState.LoadingFailed,
        ProductsListState.Loading,
        ProductsListState.Success(
            listOf(
                ProductInfo(
                    productName = "Shooting Arrows Notecards 8\"s",
                    detailsText = "Keep in touch with these fun cards!",
                    productImage = "https://cdn.faire.com/res/hszgttpjt/image/upload/04760a693446356634eb9a41f4612632932d92a945aae3329078e928acb4e71c/1519715814.jpg",
                    wholesalePrice = PriceInfo(
                        price = 5.5,
                        currency = "USD"
                    )
                )
            )
        ),
    )
}

@Composable
private fun ProductItem(
    modifier: Modifier = Modifier,
    product: ProductInfo
) = Row(
    modifier = modifier
        .padding(horizontal = 16.dp, vertical = 12.dp)
        .fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween,

    ) {
    AsyncImage(
        modifier = Modifier.size(60.dp),
        model = product.productImage,
        contentDescription = product.productName,
        error = painterResource(id = R.drawable.ic_launcher_foreground)
    )
    Spacer(modifier = Modifier.width(12.dp))
    Column(
        modifier = Modifier.weight(1f),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = product.productName, style = MaterialTheme.typography.h6)
        product.detailsText?.let {
            Text(text = it, style = MaterialTheme.typography.body2)
        }
    }
    Spacer(modifier = Modifier.width(12.dp))
    Text(product.wholesalePrice.toFormattedAmount())
}

private fun PriceInfo.toFormattedAmount(): String = NumberFormat.getCurrencyInstance().apply {
    currency = runCatching { Currency.getInstance(this@toFormattedAmount.currency) }
        //default currency
        .getOrElse { Currency.getInstance("USD") }
}.format(price)