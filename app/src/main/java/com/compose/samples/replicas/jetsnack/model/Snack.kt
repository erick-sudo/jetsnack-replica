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

val snacks = listOf(
    Snack(
        id = 1L,
        name = "Cupcake",
        tagline = "A tag line",
        imageUrl = "http://10.0.2.2:5000/snacks/pGM4sjt_BdQ",
        price = 299
    ),
    Snack(
        id = 2L,
        name = "Donut",
        tagline = "A tag line",
        imageUrl = "http://10.0.2.2:5000/snacks/Yc5sL-ejk6U",
        price = 299
    ),
    Snack(
        id = 3L,
        name = "Eclair",
        tagline = "A tag line",
        imageUrl = "http://10.0.2.2:5000/snacks/-LojFX9NfPY",
        price = 299
    ),
    Snack(
        id = 4L,
        name = "Froyo",
        tagline = "A tag line",
        imageUrl = "http://10.0.2.2:5000/snacks/3U2V5WqK1PQ",
        price = 299
    ),
    Snack(
        id = 5L,
        name = "Gingerbread",
        tagline = "A tag line",
        imageUrl = "http://10.0.2.2:5000/snacks/Y4YR9OjdIMk",
        price = 499
    ),
    Snack(
        id = 6L,
        name = "Honeycomb",
        tagline = "A tag line",
        imageUrl = "http://10.0.2.2:5000/snacks/bELvIg_KZGU",
        price = 299
    ),
    Snack(
        id = 7L,
        name = "Ice Cream Sandwich",
        tagline = "A tag line",
        imageUrl = "http://10.0.2.2:5000/snacks/YgYJsFDd4AU",
        price = 1299
    ),
    Snack(
        id = 8L,
        name = "Jellybean",
        tagline = "A tag line",
        imageUrl = "http://10.0.2.2:5000/snacks/0u_vbeOkMpk",
        price = 299
    ),
    Snack(
        id = 9L,
        name = "KitKat",
        tagline = "A tag line",
        imageUrl = "http://10.0.2.2:5000/snacks/yb16pT5F_jE",
        price = 549
    ),
    Snack(
        id = 10L,
        name = "Lollipop",
        tagline = "A tag line",
        imageUrl = "http://10.0.2.2:5000/snacks/AHF_ZktTL6Q",
        price = 299
    ),
    Snack(
        id = 11L,
        name = "Marshmallow",
        tagline = "A tag line",
        imageUrl = "http://10.0.2.2:5000/snacks/rqFm0IgMVYY",
        price = 299
    ),
    Snack(
        id = 12L,
        name = "Nougat",
        tagline = "A tag line",
        imageUrl = "http://10.0.2.2:5000/snacks/qRE_OpbVPR8",
        price = 299
    ),
    Snack(
        id = 13L,
        name = "Oreo",
        tagline = "A tag line",
        imageUrl = "http://10.0.2.2:5000/snacks/33fWPnyN6tU",
        price = 299
    ),
    Snack(
        id = 14L,
        name = "Pie",
        tagline = "A tag line",
        imageUrl = "http://10.0.2.2:5000/snacks/aX_ljOOyWJY",
        price = 299
    ),
    Snack(
        id = 15L,
        name = "Chips",
        imageUrl = "http://10.0.2.2:5000/snacks/UsSdMZ78Q3E",
        price = 299
    ),
    Snack(
        id = 16L,
        name = "Pretzels",
        imageUrl = "http://10.0.2.2:5000/snacks/7meCnGCJ5Ms",
        price = 299
    ),
    Snack(
        id = 17L,
        name = "Smoothies",
        imageUrl = "http://10.0.2.2:5000/snacks/m741tj4Cz7M",
        price = 299
    ),
    Snack(
        id = 18L,
        name = "Popcorn",
        imageUrl = "http://10.0.2.2:5000/snacks/iuwMdNq0-s4",
        price = 299
    ),
    Snack(
        id = 19L,
        name = "Almonds",
        imageUrl = "http://10.0.2.2:5000/snacks/qgWWQU1SzqM",
        price = 299
    ),
    Snack(
        id = 20L,
        name = "Cheese",
        imageUrl = "http://10.0.2.2:5000/snacks/9MzCd76xLGk",
        price = 299
    ),
    Snack(
        id = 21L,
        name = "Apples",
        tagline = "A tag line",
        imageUrl = "http://10.0.2.2:5000/snacks/1d9xXWMtQzQ",
        price = 299
    ),
    Snack(
        id = 22L,
        name = "Apple sauce",
        tagline = "A tag line",
        imageUrl = "http://10.0.2.2:5000/snacks/wZxpOw84QTU",
        price = 299
    ),
    Snack(
        id = 23L,
        name = "Apple chips",
        tagline = "A tag line",
        imageUrl = "http://10.0.2.2:5000/snacks/okzeRxm_GPo",
        price = 299
    ),
    Snack(
        id = 24L,
        name = "Apple juice",
        tagline = "A tag line",
        imageUrl = "http://10.0.2.2:5000/snacks/l7imGdupuhU",
        price = 299
    ),
    Snack(
        id = 25L,
        name = "Apple pie",
        tagline = "A tag line",
        imageUrl = "http://10.0.2.2:5000/snacks/bkXzABDt08Q",
        price = 299
    ),
    Snack(
        id = 26L,
        name = "Grapes",
        tagline = "A tag line",
        imageUrl = "http://10.0.2.2:5000/snacks/y2MeW00BdBo",
        price = 299
    ),
    Snack(
        id = 27L,
        name = "Kiwi",
        tagline = "A tag line",
        imageUrl = "http://10.0.2.2:5000/snacks/1oMGgHn-M8k",
        price = 299
    ),
    Snack(
        id = 28L,
        name = "Mango",
        tagline = "A tag line",
        imageUrl = "http://10.0.2.2:5000/snacks/TIGDsyy0TK4",
        price = 299
    )
)