package com.compose.samples.replicas.jetsnack.model

import androidx.compose.runtime.Immutable

@Immutable
data class Snack(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val price: Long,
    val tagline: String = "",
    val tags: Set<String> = emptySet()
)

/**
 * Static data
 */
const val EVN = "Dev"
val CDN_SERVER_URL
    get() = if(EVN == "Prod") "http://192.168.1.102:5000" else "http://10.0.2.2:5000"

val snacks = listOf(
    Snack(
        id = 1L,
        name = "Cupcake",
        tagline = "A tag line",
        imageUrl = "$CDN_SERVER_URL/snacks/pGM4sjt_BdQ",
        price = 299
    ),
    Snack(
        id = 2L,
        name = "Donut",
        tagline = "A tag line",
        imageUrl = "$CDN_SERVER_URL/snacks/Yc5sL-ejk6U",
        price = 299
    ),
    Snack(
        id = 3L,
        name = "Eclair",
        tagline = "A tag line",
        imageUrl = "$CDN_SERVER_URL/snacks/-LojFX9NfPY",
        price = 299
    ),
    Snack(
        id = 4L,
        name = "Froyo",
        tagline = "A tag line",
        imageUrl = "$CDN_SERVER_URL/snacks/3U2V5WqK1PQ",
        price = 299
    ),
    Snack(
        id = 5L,
        name = "Gingerbread",
        tagline = "A tag line",
        imageUrl = "$CDN_SERVER_URL/snacks/Y4YR9OjdIMk",
        price = 499
    ),
    Snack(
        id = 6L,
        name = "Honeycomb",
        tagline = "A tag line",
        imageUrl = "$CDN_SERVER_URL/snacks/bELvIg_KZGU",
        price = 299
    ),
    Snack(
        id = 7L,
        name = "Ice Cream Sandwich",
        tagline = "A tag line",
        imageUrl = "$CDN_SERVER_URL/snacks/YgYJsFDd4AU",
        price = 1299
    ),
    Snack(
        id = 8L,
        name = "Jellybean",
        tagline = "A tag line",
        imageUrl = "$CDN_SERVER_URL/snacks/0u_vbeOkMpk",
        price = 299
    ),
    Snack(
        id = 9L,
        name = "KitKat",
        tagline = "A tag line",
        imageUrl = "$CDN_SERVER_URL/snacks/yb16pT5F_jE",
        price = 549
    ),
    Snack(
        id = 10L,
        name = "Lollipop",
        tagline = "A tag line",
        imageUrl = "$CDN_SERVER_URL/snacks/AHF_ZktTL6Q",
        price = 299
    ),
    Snack(
        id = 11L,
        name = "Marshmallow",
        tagline = "A tag line",
        imageUrl = "$CDN_SERVER_URL/snacks/rqFm0IgMVYY",
        price = 299
    ),
    Snack(
        id = 12L,
        name = "Nougat",
        tagline = "A tag line",
        imageUrl = "$CDN_SERVER_URL/snacks/qRE_OpbVPR8",
        price = 299
    ),
    Snack(
        id = 13L,
        name = "Oreo",
        tagline = "A tag line",
        imageUrl = "$CDN_SERVER_URL/snacks/33fWPnyN6tU",
        price = 299
    ),
    Snack(
        id = 14L,
        name = "Pie",
        tagline = "A tag line",
        imageUrl = "$CDN_SERVER_URL/snacks/aX_ljOOyWJY",
        price = 299
    ),
    Snack(
        id = 15L,
        name = "Chips",
        imageUrl = "$CDN_SERVER_URL/snacks/UsSdMZ78Q3E",
        price = 299
    ),
    Snack(
        id = 16L,
        name = "Pretzels",
        imageUrl = "$CDN_SERVER_URL/snacks/7meCnGCJ5Ms",
        price = 299
    ),
    Snack(
        id = 17L,
        name = "Smoothies",
        imageUrl = "$CDN_SERVER_URL/snacks/m741tj4Cz7M",
        price = 299
    ),
    Snack(
        id = 18L,
        name = "Popcorn",
        imageUrl = "$CDN_SERVER_URL/snacks/iuwMdNq0-s4",
        price = 299
    ),
    Snack(
        id = 19L,
        name = "Almonds",
        imageUrl = "$CDN_SERVER_URL/snacks/qgWWQU1SzqM",
        price = 299
    ),
    Snack(
        id = 20L,
        name = "Cheese",
        imageUrl = "$CDN_SERVER_URL/snacks/9MzCd76xLGk",
        price = 299
    ),
    Snack(
        id = 21L,
        name = "Apples",
        tagline = "A tag line",
        imageUrl = "$CDN_SERVER_URL/snacks/1d9xXWMtQzQ",
        price = 299
    ),
    Snack(
        id = 22L,
        name = "Apple sauce",
        tagline = "A tag line",
        imageUrl = "$CDN_SERVER_URL/snacks/wZxpOw84QTU",
        price = 299
    ),
    Snack(
        id = 23L,
        name = "Apple chips",
        tagline = "A tag line",
        imageUrl = "$CDN_SERVER_URL/snacks/okzeRxm_GPo",
        price = 299
    ),
    Snack(
        id = 24L,
        name = "Apple juice",
        tagline = "A tag line",
        imageUrl = "$CDN_SERVER_URL/snacks/l7imGdupuhU",
        price = 299
    ),
    Snack(
        id = 25L,
        name = "Apple pie",
        tagline = "A tag line",
        imageUrl = "$CDN_SERVER_URL/snacks/bkXzABDt08Q",
        price = 299
    ),
    Snack(
        id = 26L,
        name = "Grapes",
        tagline = "A tag line",
        imageUrl = "$CDN_SERVER_URL/snacks/y2MeW00BdBo",
        price = 299
    ),
    Snack(
        id = 27L,
        name = "Kiwi",
        tagline = "A tag line",
        imageUrl = "$CDN_SERVER_URL/snacks/1oMGgHn-M8k",
        price = 299
    ),
    Snack(
        id = 28L,
        name = "Mango",
        tagline = "A tag line",
        imageUrl = "$CDN_SERVER_URL/snacks/TIGDsyy0TK4",
        price = 299
    )
)