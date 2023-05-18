<template>
  <div>
    <ul>
    <li><a @click="prevPage()" v-if="pagingInfo.currentPage > pagingInfo.perPageSize">prev</a></li>
    <li v-for="page in pages" :key="page" @click="changePage(page)">
      <a v-if="page==pagingInfo.currentPage" class="selected">{{ page }}</a>
      <a v-else>{{ page }}</a>
    </li>
    <li><a @click="nextPage()" v-if="pageLast < pagingInfo.totalPage">next</a></li>
  </ul>
  </div>
</template>

<script>
export default {
  props: {
    /*  pagingInfo: {
                currentPage: 1,
                perDataSize: perDataSize,
                perPageSize: 10,
                totalData: 0,
                totalPage: 0,
                first: 0,
                last: perDataSize
        }, */
    pagingInfo: {
      type: Object,
      required: true
    }
  },
  computed: {
    pageGroup() {
      return Math.ceil(this.pagingInfo.currentPage / this.pagingInfo.perDataSize);
    },
    pageFirst() {
      return (this.pageGroup - 1) * this.pagingInfo.perDataSize + 1;
    },
    pageLast() {
      return Math.min(this.pageFirst + this.pagingInfo.perPageSize - 1, this.pagingInfo.totalPage);
    },
    pages () {
      return Array.from({length: this.pageLast - this.pageFirst + 1}, (_,index) => this.pageFirst + index);
    }
  },
  methods: {
    prevPage() {
      const prevPageGroup = this.pageFirst - 1;
      this.changePage(prevPageGroup);
    },
    nextPage() {
      const nextPageGroup = this.pageLast + 1;
      this.changePage(nextPageGroup);
    },
    changePage(page) {
      this.$emit('change-page', page);
    },
    
  }
}
</script>

<style scoped>
ul {
			text-align: center;
			display: inline-block;
			padding-left: 0;
		}

		ul li {
			text-align: center;
			float: left;
			list-style: none;
		}

		ul li a {
			font-size: 20px;
			padding: 10px 10px;
			text-decoration-line: none;
		}

		ul li a.selected {
			background-color: tomato;
		}
</style>
