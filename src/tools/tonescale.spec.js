var jsc = require("jsverify");
const {scales, modes} = require('./constants.js')
const createToneScaleOf = require("./tonescale.js")

describe('tonescale', function () {
    it('should work', () => {
        // forall (f : bool -> bool) (b : bool), f (f (f b)) = f(b).
        var boolFnAppliedThrice =
            jsc.forall("bool -> bool", "bool", function (f, b) {
                return f(f(f(b))) === f(b);
            });

        jsc.assert(boolFnAppliedThrice);
    })
    it('should ', () => {
        const {getScaleOf} = createToneScaleOf(scales.chromatic)
        console.log(getScaleOf('C', modes.major))
    })
});
