<template>
  <div id="app">

    <div class="selection">
      <select name="notes" v-model="currentNote">
        <option v-for="(note, index) in availableNotes" :key="index" :value="note">{{ note }}</option>
      </select>
      <select name="types" @change="onChangeType($event)">
        <option v-for="(type, index) in availableTypes" :key="index" :value="type">{{ type }}</option>
      </select>    
    </div>

    <table class="neck">
      <tr class="frets" v-for="(row, index) in rows" :key="index">
        <td class="fret" v-for="(note, index) in row" :key="index" >
          <div :class="[{ base: note === currentNote }, 'note']" v-show="isSelected(note)">
            {{ note }}
          </div>
        </td>
        <td class="fret">
          <div class="note" v-show="isSelected(row[0])">
            {{ row[0] }}
          </div>
        </td>        
      </tr>
      <tr class="markings">
        <td class="marking" v-for="(_, index) in rows[0]" :key="index" >
          <div :class="'fret ' + 'fret_' + index" ></div>
        </td>
        <td class="marking">
          <div class="fret fret_12" ></div>
        </td>        
      </tr>
    </table>

    <hr>

    <div>
      {{ selected }}
    </div>
  </div>
</template>

<script>
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
      return this.tonescale.getScaleOf(this.currentNote, this.currentMode)
    },
    rows() {
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

      this.currentMode = types[type][mode]
    }
  },
  data() {
    return {
      tonescale: createToneScaleOf(scales.chromatic),
      currentNote: 'C',
      currentMode: modes.major
    }
  }
}
</script>

<style lang="less">

body {
  font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
}

.selection {

  margin: auto;
  text-align: center;

  * {
    border: 0;
  }

  margin-bottom: 2rem;

}

.neck {

  border-collapse: collapse;

  .frets {

    .fret {
      padding: 0;
      border: 1px solid black;
      width: 5rem;
      height: 1.5rem;
      position: relative;
      text-align: center;
      
      &:first-child {
        width: 2rem;
        background-color: black;

        color: white;
      }

      .note {
        height: 100%;
        line-height: 1.3rem;
      }

      .note.base {
        border: 2px dashed black;
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
        &.fret_9::before {
          content: '\25CF';
        }

        &.fret_12::before {
          content: '\25CF\25CF';
        }

      }
    }
  }
}

</style>