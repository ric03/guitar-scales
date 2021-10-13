typealias Scale = List<String>

fun Scale.getRangeStartingWith(note: String): List<String> {
    val slicePosition = this.indexOf(note)

    if (slicePosition == -1)
        throw IllegalArgumentException("$note is not part of $this")
    else
        return this.slice(slicePosition..this.lastIndex) + this.slice(0.until(slicePosition))
}
