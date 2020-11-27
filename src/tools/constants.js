const scales = {
    chromatic:  [ 'C', 'C♯/D♭', 'D', 'D♯/E♭', 'E', 'F', 'F♯/G♭', 'G', 'G♯/A♭', 'A', 'A♯/B♭', 'B' ]
}

const modes = {
    major:      [ 2, 2, 1, 2, 2, 2, 1 ], // ionian
    minor:      [ 2, 1, 2, 2, 1, 2, 2 ], // aeolian
    ionian:     [ 2, 2, 1, 2, 2, 2, 1 ],
    dorian:     [ 2, 1, 2, 2, 2, 1, 2 ],
    phrygian:   [ 1, 2, 2, 2, 1, 2, 2 ],
    lydian:     [ 2, 2, 2, 1, 2, 2, 1 ],
    mixolydian: [ 2, 2, 1, 2, 2, 1, 2 ],
    aeolian:    [ 2, 1, 2, 2, 1, 2, 2 ],
    locrian:    [ 1, 2, 2, 1, 2, 2, 2 ],
}

const chords = {
    major:        [ 4, 3 ],
    minor:        [ 3, 4 ],
    majorSeventh: [ 4, 3, 3 ],
    minorSeventh: [ 3, 4, 3 ]
}

export { 
    scales,
    modes,
    chords
}