package com.example.projectmobile.data

data class Modul(
    val id: String = "",
    val judul: String = "",
    val isi: String = "",
    val gambar: String = ""
)

// Create dummy
val modul = Modul(
    "1",
    "Judul Modul",
    "Isi Modul",
    "https://via.placeholder.com/150"
)
val modul2 = Modul(
    "2",
    "Judul Modul 2",
    "Isi Modul 2",
    "https://via.placeholder.com/150"
)
val modul3 = Modul(
    "3",
    "Judul Modul 3",
    "Isi Modul 3",
    "https://via.placeholder.com/150"
)
val modul4 = Modul(
    "4",
    "Judul Modul 4",
    "Isi Modul 4",
    "https://via.placeholder.com/150"
)
val modul5 = Modul(
    "5",
    "Judul Modul 5",
    "Isi Modul 5",
    "https://via.placeholder.com/150"
)

val dummyModulList = listOf(modul, modul2, modul3, modul4, modul5)
