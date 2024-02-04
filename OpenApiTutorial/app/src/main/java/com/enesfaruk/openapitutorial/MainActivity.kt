package com.enesfaruk.openapitutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enesfaruk.openapitutorial.models.GetAll200ResponseInner
import com.enesfaruk.openapitutorial.ui.theme.OpenApiTutorialTheme

class MainActivity : ComponentActivity() {
    val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OpenApiTutorialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(dataList = mainViewModel.dataListResponse)
                    mainViewModel.getDataList()
                }
            }
        }
    }
}

@Composable
fun Greeting(dataList: List<GetAll200ResponseInner>) {
    LazyColumn {

        itemsIndexed(items = dataList) { index, item ->
            MovieItem(data = item, index)
        }
    }
}

@Composable
fun MovieItem(data: GetAll200ResponseInner, index: Int) {

    val backgroundColor = colorResource(id = R.color.grey)
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .height(110.dp), shape = RoundedCornerShape(8.dp)
    ) {
        Surface(color = backgroundColor) {

            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            ) {

                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                ) {
                    Text(
                        text = "UserId: " + data.userId.toString(),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Id: " + data.id.toString(),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Title: " + data.title.toString(),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Body: " + data.body.toString(),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OpenApiTutorialTheme {
        val data = GetAll200ResponseInner(
            1,
            100,
            "Test",
            "Lorem Ipsum"
        )

        MovieItem(data = data, 0)
    }
}