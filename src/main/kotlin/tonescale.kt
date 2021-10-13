val tones = arrayOf("a", "b")

// THIS DOES NOT WORK.

fun getNoteBy(index: Int) = tones[index % tones.size]
fun indexOf(note: String) = tones.indexOf(note)
fun aggregateNotes(scale: List<String>, idx: Int) = scale + getNoteBy(idx)
fun scale(offset: List<String>, step: String) = offset + offset.last() + step
//fun getScaleOffsetBy(note: String, mode: List<List<String>>) =  mode.fold(indexOf(note)) {offset, step -> offset + offset.last() + step}
