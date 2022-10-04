package lol.slava.civicstest

data class Question(
    val question: String,
    val answers: String,
    var correct: Boolean
)