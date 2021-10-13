val scales: Map<String, Scale> = mapOf(
   "chromatic" to  listOf("C", "C♯", "D", "D♯", "E", "F", "F♯", "G", "G♯", "A", "A♯", "B")
)

typealias Mode = List<Int>
val modes: Map<String, Mode> = mapOf(
    "major" to listOf(2, 2, 1, 2, 2, 2, 1), // ionian
    "majorPentatonic" to listOf(2, 2, 3, 2),
    "minor" to listOf(2, 1, 2, 2, 1, 2, 2), // aeolian
    "minorPentatonic" to listOf(3, 2, 2, 3),
    "minorHarmonic" to listOf(2, 1, 2, 2, 1, 3, 1),
    "bluesHexatonic" to listOf(3, 2, 1, 1, 3),
    "ionian" to listOf(2, 2, 1, 2, 2, 2, 1),
    "dorian" to listOf(2, 1, 2, 2, 2, 1, 2),
    "phrygian" to listOf(1, 2, 2, 2, 1, 2, 2),
    "phrygianDominant" to listOf(1, 3, 1, 2, 1, 2, 2),
    "lydian" to listOf(2, 2, 2, 1, 2, 2, 1),
    "mixolydian" to listOf(2, 2, 1, 2, 2, 1, 2),
    "aeolian" to listOf(2, 1, 2, 2, 1, 2, 2),
    "locrian" to listOf(1, 2, 2, 1, 2, 2, 2),
)

typealias Chord = List<Int>
val chords: Map<String, Chord> = mapOf(
    "major" to listOf(4, 3),
    "minor" to listOf(3, 4),
    "majorSeventh" to listOf(4, 3, 3),
    "minorSeventh" to listOf(3, 4, 3),
    "sus2" to listOf(2, 5),
    "sus4" to listOf(5, 2),
)
