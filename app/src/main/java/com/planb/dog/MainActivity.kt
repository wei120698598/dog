/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.planb.dog

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.planb.dog.ui.theme.MyTheme
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme(true) {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@ExperimentalFoundationApi
@Composable
fun MyApp() {
    val context = LocalContext.current
    Surface(color = MaterialTheme.colors.surface) {
        Column() {
            Header()
            LazyVerticalGrid(
                cells = GridCells.Fixed(2),
            ) {
                items(Dogs) {
                    DogItem(context, it)
                }
            }
        }
    }
}

@Composable
fun Header() {
    TopAppBar(
        modifier = Modifier.padding(10.dp),
        backgroundColor = Color.Transparent,
    ) {
        Row(verticalAlignment = Alignment.Bottom) {
            Image(
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
            )
            Text(
                text = "PlanBDog",
                fontSize = 30.sp,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}

@Composable
fun DogItem(context: Context, it: Dog) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .clip(shape = RoundedCornerShape(5.dp, 5.dp))
            .clickable {
                context.startActivity(
                    Intent(
                        context,
                        DogDetailActivity::class.java
                    ).putExtra("dog", it)
                )
            },
        elevation = 10.dp,
        backgroundColor = MaterialTheme.colors.surface,
    ) {
        Column() {
            Image(
                modifier = Modifier
                    .height(250.dp),
                painter = painterResource(id = it.avatar),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Text(it.name, Modifier.padding(5.dp))
        }
    }
}

data class Dog(
    val name: String = "",
    @DrawableRes val avatar: Int = 0,
    val age: Int = 0,
    val sex: Boolean = true,
    val breed: String? = null,
    val story: String? = null,
) : Serializable

@ExperimentalFoundationApi
@Preview("Light Theme", widthDp = 720, heightDp = 1280)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@ExperimentalFoundationApi
@Preview("Dark Theme", widthDp = 720, heightDp = 1280)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
