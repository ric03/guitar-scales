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

    <table class="neck">
      <tr class="frets" v-for="(string, index) in strings" :key="index">
        <td class="fret" v-for="fret in getFretsFor(string)" :key="fret" >
          <div :class="[{ base: fret === current.base }, 'note']" v-show="isSelected(fret)">
            {{ fret }}
          </div>
        </td>
      </tr>
      <tr class="markings">
        <td class="marking" v-for="fret in frets" :key="fret" >
          <div :class="'fret ' + 'fret_' + fret" ></div>
        </td>
      </tr>
    </table>

    <div class="notes">
      {{ selected }}
    </div>

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
    strings() {
      return [
        this.tonescale.getRangeStartingWith('E'),
        this.tonescale.getRangeStartingWith('B'),
        this.tonescale.getRangeStartingWith('G'),
        this.tonescale.getRangeStartingWith('D'),
        this.tonescale.getRangeStartingWith('A'),
        this.tonescale.getRangeStartingWith('E')
      ]
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

      const result = this.frets.map(fret => string[fret % string.length])

      return result
    }
  },
  data() {
    return {
      tonescale: createToneScaleOf(scales.chromatic),
      current: {
        base: 'C',
        mode: modes.major
      },
      frets: _.keys(new Array(21))
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
      height: 1.5rem;

      .fret {

        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
        font-size: 2rem;

        &.fret_3::before,
        &.fret_5::before,
        &.fret_7::before,
        &.fret_9::before,
        &.fret_15::before,
        &.fret_17::before,
        &.fret_19::before {
          content: '\25CF';
        }

        &.fret_12::before {
          content: '\25CF\25CF';
        }

      }
    }
  }
}

.notes {

  background-color: silver;
  padding: 1rem;
}


</style>