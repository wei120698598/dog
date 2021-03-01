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

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.planb.dog.ui.theme.MyTheme

class DogDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme(true) {
                Dog()
            }
        }
    }
}

// Start building your app here!
@Composable
fun Dog() {
    val dogDetailActivity = LocalContext.current as DogDetailActivity
    val dog = dogDetailActivity.intent.getSerializableExtra("dog") as Dog
    Surface(color = MaterialTheme.colors.surface) {
        Column(modifier = Modifier.fillMaxHeight()) {
            Header(dog)
            LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                item {
                    Age(dog)
                }
                item {
                    Sex(dog)
                }
                item {
                    Story(dog)
                }
            }
        }
    }
}

@Composable
private fun Story(dog: Dog) {
    dog.story?.let {
        Card(elevation = 10.dp, modifier = Modifier.padding(15.dp)) {
            Text(it, fontSize = 15.sp, modifier = Modifier.padding(10.dp))
        }
    }
}

@Composable
private fun Sex(dog: Dog) {
    Card(
        elevation = 10.dp,
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp, top = 15.dp)
            .fillMaxWidth()
    ) {
        Text(
            "你的狗狗是个 '${if (dog.sex) "小公主" else "淘气鬼"}'",
            fontSize = 15.sp,
            modifier = Modifier.padding(10.dp)
        )
    }
}

@Composable
private fun Age(dog: Dog) {
    Card(
        elevation = 10.dp,
        modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 15.dp).fillMaxWidth()
    ) {
        Text("狗狗今年 ${dog.age} 岁了", fontSize = 15.sp, modifier = Modifier.padding(10.dp))
    }
}

@Composable
private fun Header(dog: Dog) {
    Box(contentAlignment = Alignment.BottomStart) {
        Image(
            painter = painterResource(id = dog.avatar),
            contentScale = ContentScale.FillWidth,
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 300.dp)
        )
        Column(modifier = Modifier.padding(15.dp)) {
            Text(dog.name, fontSize = 30.sp)
            Text("PlanBDog", fontSize = 15.sp)
        }
    }
}

@Preview("Dark Theme", widthDp = 720, heightDp = 1280)
@Composable
fun DarkPreview1() {
    MyTheme(darkTheme = true) {
        Dog()
    }
}
