package com.example.projectmobile.data

data class Video(
    val id: String = "",
    val judul: String = "",
    val isi: String = "",
    val video: String = ""
)

// Create dummy
val video = Video(
    "1",
    "Judul Video",
    "Isi Video",
    "https://via.placeholder.com/150"
)
val video2 = Video(
    "2",
    "Judul Video 2",
    "Isi Video 2",
    "https://via.placeholder.com/150"
)
val video3 = Video(
    "3",
    "Judul Video 3",
    "Isi Video 3",
    "https://via.placeholder.com/150"
)
val video4 = Video(
    "4",
    "Judul Video 4",
    "Isi Video 4",
    "https://via.placeholder.com/150"
)
val video5 = Video(
    "5",
    "Judul Video 5",
    "Isi Video 5",
    "https://via.placeholder.com/150"
)

val dummyVideoList = listOf(video, video2, video3, video4, video5)
