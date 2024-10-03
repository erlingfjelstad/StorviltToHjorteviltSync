package org.example.models

enum class Art(id: Int) {
    Elg(1),
    Hjort(2);

    companion object {
        fun getById(id: Int?): Art {
            return when (id) {
                1 -> Elg
                2 -> Hjort
                else -> throw IllegalArgumentException("Something went wrong")
            }
        }
    }
}