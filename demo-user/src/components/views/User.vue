<template>
    <div>
        <search v-if="isOpenModal" @get-user="getUser" @close-modal="closeModal()"></search>
        <span @click="openModal">Search</span>
        <Table :items="showData" :total-data="pagingInfo.totalData"></Table>
        <Pagination
            :paging-info="pagingInfo"
            @change-page="changePage"
        ></Pagination>
    </div>
</template>

<script>
import Axios from "../mixin/Axios.vue";
import Search from "./searchModal.vue";
import Table from "../Table.vue";
import Pagination from "../Pagination.vue";
import path from "@/url.js";
const perDataSize = 10

export default {
    name: "UserList",
    mixins: [Axios],
    data() {
        return {
            users: [],
            body: {},
            showUsers: [],
            responseData: null,
            isOpenModal: false,
            // currentData: '',
            pagingInfo: {
                currentPage: 1,
                perDataSize: perDataSize,
                perPageSize: 10,
                totalData: 0,
                totalPage: 0,
                first: 0,
                last: perDataSize
            },
        };
    },
    created() {
        this.getUser({});
    },
    methods: {
        async getUser(body) {
            this.users = await this.post(path.getUsersUrl, body);
            this.pagingInfo.totalData = this.users.length;
            this.pagingInfo.totalPage = Math.ceil(this.pagingInfo.totalData / this.pagingInfo.perDataSize);
        },
        changePage(page) {
            this.pagingInfo.currentPage = page;
        },
        openModal() {
            this.isOpenModal = true;
        },
        closeModal() {
            this.isOpenModal = false;
        }

    },
    components: {
        Table,
        Pagination,
        Search
    },
    computed: {
        // 현재 페이지 데이터
        showData() {
            const first = (this.pagingInfo.currentPage - 1) * this.pagingInfo.perDataSize;
            const last = first + this.pagingInfo.perDataSize;
            return this.users.slice(first, last);   // 인덱스 범위를 넘어가면 무시되기 때문에 그냥 진행
        },
    },
};
</script>

<style>
</style>
