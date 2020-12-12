<template>
  <div id="app">

    <h1>Guitar scales</h1>

    <div class="selection">
      <select name="notes" v-model="current.base">
        <option v-for="(note, index) in availableNotes" :key="index" :value="note">{{ note }}</option>
      </select>
      <select name="types" @change="onChangeType($event)">
        <option v-for="(type, index) in availableTypes" :key="index" :value="type">{{ type }}</option>
      </select>    
    </div>

    <div class="notes">
      <div v-for="(note, index) in selected" :key="index" class="note">{{ note }}</div>
    </div>

    <table class="neck">
      <tr class="frets" v-for="(string, index) in neck.strings" :key="index">
        <td class="fret" v-for="(fret, index) in string" :key="index" >
          <div :class="[{ base: fret === current.base }, 'note']" v-show="isSelected(fret)">
            {{ fret }}
          </div>
        </td>
      </tr>
      <tr class="markings">
        <td class="marking" v-for="(marking, index) in neck.markings" :key="index" >
          <div :class="[marking, 'fret']" ></div>
        </td>
      </tr>
    </table>

    <footer>
      <a href="https://github.com/SMoni/guitar-scales" target="_blank">Source code</a>
    </footer>
  </div>
</template>

<script>
import 'normalize.css'
import _ from 'lodash'
import createToneScaleOf from '@/tools/tonescale.js'
import { modes, chords, scales } from '@/tools/constants.js'

const types = {
  modes,
  chords
}

export default {
  name: 'App',
  computed: {
    availableNotes() {
      return this.tonescale.getRangeStartingWith('C')
    },
    availableTypes() {
      return [
        ..._.keys(modes) .map(entry => `modes.${entry}`),
        ..._.keys(chords).map(entry => `chords.${entry}`),
      ]
    },
    selected() {
      return this.tonescale.getScaleOf(this.current.base, this.current.mode)
    },
    neck() {
      return {
        strings: [
          this.getFretsFor(this.tonescale.getRangeStartingWith('E')),
          this.getFretsFor(this.tonescale.getRangeStartingWith('B')),
          this.getFretsFor(this.tonescale.getRangeStartingWith('G')),
          this.getFretsFor(this.tonescale.getRangeStartingWith('D')),
          this.getFretsFor(this.tonescale.getRangeStartingWith('A')),
          this.getFretsFor(this.tonescale.getRangeStartingWith('E'))
        ],
        markings: this.frets.map(fret => {

          if(this.isSingle(fret))
            return 'single'

          if(this.isDouble(fret))
            return 'double'
          
          return ''
        })
      } 
    }
  },
  methods: {
    isSelected: function(note) {
      return this.selected.includes(note)
    },
    onChangeType: function(event) {
      
      const [ type, mode ] = event.target.value.split('.')

      this.current.mode = types[type][mode]
    },
    getFretsFor: function(string) {
      return this.frets.map(fret => string[fret % string.length])
    },
    isSingle: function(fret) {
      return this.markings.single.includes(fret)      
    },
    isDouble: function(fret) {
      return this.markings.double.includes(fret)      
    }    
  },
  data() {
    return {
      tonescale: createToneScaleOf(scales.chromatic),
      current: {
        base: 'C',
        mode: modes.major
      },
      frets: _.keys(new Array(21)),
      markings: {
        single: [ '3', '5', '7', '9', '15', '17', '19' ],
        double: [ '12' ]
      }
    }
  }
}
</script>

<style lang="less">

body {
  font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
  margin: 2rem;
}

h1 {
  text-align: center;
}

footer {
  position: fixed;
  bottom: 1rem;
  width: 100%;
  text-align: center;
}

.selection {

  text-align: center;

  * {
    border: 0;
  }

  margin-bottom: 2rem;

}

.notes {

  width: 100%;
  display: inline-flex;
  justify-content: center;
  margin-bottom: 2rem;

  .note {
    text-align: center;
    background-color: silver;
    padding: .8rem;
    border: 1px solid grey;
    width: 4rem;
  }
}

.neck {

  border-collapse: collapse;
  margin-bottom: 1rem;

  .frets {

    .fret {
      padding: 0;
      border: 1px solid black;
      width: 5rem;
      height: 2rem;
      position: relative;
      text-align: center;
      
      &:first-child {
        width: 2rem;
        background-color: black;

        color: white;
      }

      .note {
        box-sizing: border-box;
        height: 100%;
        line-height: 2rem;
      }

      .note.base {
        border: .2rem dashed red;
        font-weight: bold;
      }

    }
  }

  .markings {

    .marking {
      position: relative;
      height: 2rem;

      .fret {
        line-height: 2rem;
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
        font-size: 1.8rem;

        &.single::before {
          content: '\25CF';
        }

        &.double::before {
          content: '\25CF\25CF';
        }

      }
    }
  }
}

</style>