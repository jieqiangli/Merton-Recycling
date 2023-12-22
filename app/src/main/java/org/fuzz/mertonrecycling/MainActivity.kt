package org.fuzz.mertonrecycling

import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import org.fuzz.mertonrecycling.ui.theme.MertonRecyclingTheme

// GET https://myneighbourhood.merton.gov.uk/Wasteservices/WasteServices.aspx?uprn=48053547&Address=9
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MertonRecyclingTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Column {
                        Input("SW19 2NR")
                        Content()
                    }
                }
            }
        }
    }
}

@Composable
fun Input(name: String) {
    var text by remember { mutableStateOf(name) }

    TextField(
        value = name,
        onValueChange = { text = it },
        label = { Text("Postcode") }
    )

}

@Composable
fun Content() {
    val url = "https://myneighbourhood.merton.gov.uk/Wasteservices/WasteServices.aspx?uprn=48053547&Address=9"
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            loadUrl(url)
        }
    }, update = {
        it.loadUrl(url)
    })
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MertonRecyclingTheme {
        Column {
            Input("SW19 2NR")
            Content()
        }
    }
}