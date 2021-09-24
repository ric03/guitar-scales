import {suite} from 'uvu';
import * as fc from 'fast-check';
import {scales} from './constants.js';
import createToneScaleOf from './tonescale.js';

import * as assert from 'uvu/assert';

const notes = fc.stringOf(fc.constantFrom('C', 'D'), {minLength: 1, maxLength: 1});

const getRangeStartingWith = suite('getRangeStartingWith');

getRangeStartingWith('result always starts with the given note', () => {
    const {getRangeStartingWith} = createToneScaleOf(scales.chromatic);

    fc.assert(fc.property(
        notes,
        note => {
            const [firstNote] = getRangeStartingWith(note);
            assert.is(firstNote, note);
        }));
});

getRangeStartingWith('fc length is always 12 for chromatic scale', () => {
    const {getRangeStartingWith} = createToneScaleOf(scales.chromatic);

    fc.assert(fc.property(
        notes,
        note => {

            const range = getRangeStartingWith(note);
            assert.is(range.length, 8);
            // return range.length === 8;
        }));
});

// getRangeStartingWith('length is always 12 for chromatic scale', () => {
//     const {getRangeStartingWith} = createToneScaleOf(scales.chromatic);
//
//     const range = getRangeStartingWith('C');
//     assert.is(range.length, 8);
// });

getRangeStartingWith.run();
