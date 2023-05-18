<template>
    <div>
      <h1>User List</h1>
      <span><strong>검색 결과 수: {{ totalData }}</strong></span>
      <table style="margin: auto;">
        <thead>
        <tr>
          <th v-for="(value, key) in items[0]" :key="key">
            {{ key }}
          </th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(item, index) in items" :key="index">
          <td v-for="(value, key) in item" :key="key">
            {{ value }}
          </td>
        </tr>
      </tbody>
      </table>
    </div>
</template>

<script>

export default {
  data() {
    return {
    }
  },
  // props: ['users']
  props: {
    items: {
      type: Array,
      required: true
    },
    totalData: {
      type:Number,
      required: true
    }
  }
};
</script>

<style>
table {
			border: 3px solid;
			margin-left: auto;
			margin-right: auto;
		}

		th {
			border: 2px solid;
		}

		td {
			border: 0.5px groove;
		}
</style>
