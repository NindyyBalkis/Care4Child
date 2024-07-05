package com.example.projectmobile.data

data class Artikel(
    val id: String = "",
    val judul: String = "",
    val isi: String = "",
    val gambar: String = ""
)

// Create dummy
val artikel = Artikel(
    "1",
    "Judul Artikel",
    "Isi Artikel",
    "https://via.placeholder.com/150"
)
val artikel2 = Artikel(
    "2",
    "Judul Artikel 2",
    "Isi Artikel 2",
    "https://via.placeholder.com/150"
)
val artikel3 = Artikel(
    "3",
    "Judul Artikel 3",
    "Isi Artikel 3",
    "https://via.placeholder.com/150"
)
val artikel4 = Artikel(
    "4",
    "Judul Artikel 4",
    "Isi Artikel 4",
    "https://via.placeholder.com/150"
)
val artikel5 = Artikel(
    "5",
    "Judul Artikel 5",
    "Isi Artikel 5",
    "https://via.placeholder.com/150"
)

val dummyArtikelList = listOf(artikel, artikel2, artikel3, artikel4, artikel5)
