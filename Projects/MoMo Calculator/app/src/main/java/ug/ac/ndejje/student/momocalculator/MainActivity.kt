package ug.ac.ndejje.student.momocalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ug.ac.ndejje.student.momocalculator.ui.theme.MoMoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoMoAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Scaffold(
                        topBar = { MoMoTopBar() },
                                bottomBar = { MoMoBottomBar() }
                    ) { innerPadding ->
                        MoMoCalcScreen(
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun HoistedAmountInput(
    amount: String,
    onAmountChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false

) {
    Column (modifier = modifier){
        TextField(
            value = amount,
            onValueChange = onAmountChange,
            isError = isError,
            label = { Text(stringResource(R.string.enter_amount)) }
        )
        if (isError) {
            Text(
                text = stringResource(R.string.error_numbers_only),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

fun calculateMoMoCharge(amount: Double): Int {
    return when (amount){
        in 500.0..2500.0 -> 330
        in 2501.0..5000.0 -> 440
        in 5001.0..15000.0 -> 700
        in 15001.0..30000.0 -> 880
        in 30001.0..45000.0 -> 1210
        in 45001.0..60000.0 -> 1500
        in 60001.0..125000.0 -> 1925
        in 125001.0..250000.0 -> 3575
        in 250001.0..500000.0 -> 7000
        in 500001.0..1000000.0 -> 12500
        in 1000001.0..2000000.0 -> 15000
        in 2000001.0..4000000.0 -> 18000
        in 4000001.0..7000000.0 -> 20000
        else -> 0
    }
}
@Composable
fun MoMoCalcScreen(
    modifier: Modifier = Modifier
) {
    var amountInput by remember { mutableStateOf("") }

    val numericAmount = amountInput.toDoubleOrNull()
    val isError = amountInput.isNotEmpty() && numericAmount == null
    val fee = calculateMoMoCharge(numericAmount ?: 0.0)
    val formattedFee = "UGX %,d".format(fee)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.screen_padding)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.app_title),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
        Spacer(
            modifier = Modifier.height(
                dimensionResource(R.dimen.spacing_large)
            )
        )

        HoistedAmountInput(
            amount = amountInput,
            onAmountChange = { amountInput = it },
            isError = isError,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(
            modifier = Modifier.height(
                dimensionResource(R.dimen.spacing_medium)
            )
        )

        Text(
            text = stringResource(R.string.fee_label, formattedFee),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MoMoTopBar() {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.app_title),
                    style = MaterialTheme.typography.headlineMedium
                )
            },
            navigationIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_momo_logo3),
                    contentDescription = "MoMo Logo",
                    modifier = Modifier
                        .padding(start = dimensionResource(R.dimen.spacing_medium))
                        .height(20.dp)
                        .wrapContentWidth(),
                    contentScale = ContentScale.Fit
                )
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )
    }
@Composable
fun MoMoBottomBar() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.primary
    ) {
        Text(
            text = "Powered by Alfred",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MoMoCalcPreview() {
    MaterialTheme {
        Scaffold(
            topBar = { MoMoTopBar() },
            bottomBar = { MoMoBottomBar() }
        ) { innerPadding ->
            MoMoCalcScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}


