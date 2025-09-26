package com.example.hotrobay.userdata

import com.example.hotrobay.R

data class Nationality(
    val vn: String,
    val en: String,
    val image: Int // can be URL or drawable resource name
)

val nationalities = listOf(
    Nationality("Việt Nam", "Vietnamese", R.drawable.vietnamese),
    Nationality("Mỹ", "American", R.drawable.american),
    Nationality("Anh", "British", R.drawable.british),
    Nationality("Pháp", "French", R.drawable.french),
    Nationality("Đức", "German", R.drawable.german),
    Nationality("Nhật Bản", "Japanese", R.drawable.japanese),
    Nationality("Hàn Quốc", "Korean", R.drawable.korean),
    Nationality("Trung Quốc", "Chinese", R.drawable.chinese),
    Nationality("Nga", "Russian", R.drawable.russian),
    Nationality("Úc", "Australian", R.drawable.australian),
    Nationality("Canada", "Canadian", R.drawable.canadian),
    Nationality("Ý", "Italian", R.drawable.italian),
    Nationality("Tây Ban Nha", "Spanish", R.drawable.spanish),
    Nationality("Thái Lan", "Thai", R.drawable.thai),
    Nationality("Lào", "Laotian", R.drawable.laotian),
    Nationality("Campuchia", "Cambodian", R.drawable.cambodian),
    Nationality("Philippines", "Filipino", R.drawable.filipino),
    Nationality("Malaysia", "Malaysian", R.drawable.malaysian),
    Nationality("Singapore", "Singaporean", R.drawable.singaporean),
    Nationality("Indonesia", "Indonesian", R.drawable.indonesian)
)

data class Gender (
    val vn: String,
    val en: String,
    val image: Int
)

val genders = listOf(
    Gender("Nam","Male", R.drawable.man_icon),
    Gender("Nữ","Female",R.drawable.female_icon)
)