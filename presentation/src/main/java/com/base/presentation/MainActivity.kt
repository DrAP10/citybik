package com.base.presentation

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.*
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.base.presentation.theme.BaseTheme
import java.net.InetAddress

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val connectivityManager =
            getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()

        setContent {
            var connectionAvailable by rememberSaveable { mutableStateOf(isInternetAvailable()) }
            LaunchedEffect(key1 = Unit) {
                val networkCallback = object : ConnectivityManager.NetworkCallback() {
                    override fun onAvailable(network: Network) {
                        super.onAvailable(network)
                        connectionAvailable = true
                    }

                    override fun onLost(network: Network) {
                        super.onLost(network)
                        connectionAvailable = false
                    }
                }
                connectivityManager.requestNetwork(networkRequest, networkCallback)
            }

            BaseTheme(connectionAvailable) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BaseApplication {
                        val gmmIntentUri =
                            Uri.parse("google.navigation:q=${it.latitude},${it.longitude}&mode=b")
                        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                        mapIntent.setPackage("com.google.android.apps.maps")
                        try {
                            startActivity(mapIntent)
                        } catch (ex: ActivityNotFoundException) {
                            Toast.makeText(this, "App not found", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}

fun isInternetAvailable(): Boolean =
    try {
        val ipAddress: InetAddress = InetAddress.getByName("google.com")
        !ipAddress.equals("")
    } catch (e: Exception) {
        false
    }

@Composable
fun Greeting(name: String) {
    Text(text = name)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BaseTheme {
        Greeting("Android")
    }
}