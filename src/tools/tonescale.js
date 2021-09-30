function createToneScaleOf(tones) {

    const getNoteBy = (index) => tones[index % tones.length]
    const indexOf = (selected) => tones.findIndex(note => note === selected)
    const aggregateNotes = (scale, index) => [...scale, getNoteBy(index)]
    const scale = (offset, step) => [...offset, offset[offset.length - 1] + step]
    const getScaleOffsetBy = (note, mode) => mode.reduce(scale, [indexOf(note)])
    const getNotesWith = (offset) => (result, _, index) => aggregateNotes(result, index + offset)

    return {
        getRangeStartingWith: (note) => tones.reduce(getNotesWith(indexOf(note)), []),
        getScaleOf: (note, mode) => getScaleOffsetBy(note, mode).reduce(aggregateNotes, [])
    }
}

module.exports = createToneScaleOf
