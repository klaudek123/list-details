package com.example.labapplication

data class Trail(
    val id: Int,
    val title: String,
    val description: String,
    val difficulty: String,
    val idImage: Int
)


val trails = listOf(
    Trail(
        1,
        "Szlak Orlej Perci",
        "Najbardziej widowiskowy szlak w polskich Tatrach, prowadzący granią od Zawratu do Kościeliskiej Przełęczy.",
        "hard",
        R.drawable.orla_perc_tatry
    ),
    Trail(
        2,
        "Szlak na Rysy",
        "Najwyższy szczyt w Polsce, oferujący niesamowite widoki. Szlak wymaga pewnej wprawy w górach.",
        "hard",
        R.drawable.rysy
    ),
    Trail(
        3,
        "Szlak na Kasprowy Wierch",
        "Popularny szlak prowadzący na jeden z najbardziej rozpoznawalnych szczytów w Tatrach.",
        "easy",
        R.drawable.kasprowy_wierch
    ),
    Trail(
        4,
        "Szlak do Morskiego Oka",
        "Powszechnie znany i często uczęszczany szlak, prowadzący do jednego z najpiękniejszych jezior w Tatrach.",
        "easy",
        R.drawable.morskie_oko
    ),
    Trail(
        5,
        "Szlak na Giewont",
        "Ikoniczny szczyt o charakterystycznej sylwetce. Szlak wymaga pewnej sprawności fizycznej i wspinaczkowej.",
        "hard",
        R.drawable.giewont
    )
)