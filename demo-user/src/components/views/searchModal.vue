<template>
    <div class="modal">
        <div class="modal-content">
            <button style="float: right" @click="closeModal">X</button>
            <h2 style="text-align: center">User 검색 조건</h2>
            <div>
                <table>
                    <tr>
                        <th>name</th>
                        <td colspan="2">
                            <input type="text" v-model="userFilter.name" />
                        </td>
                    </tr>
                    <tr>
                        <th>email</th>
                        <td colspan="2">
                            <input type="text" v-model="userFilter.email" />
                        </td>
                    </tr>
                    <tr>
                        <th>password</th>
                        <td colspan="2">
                            <input type="text" v-model="userFilter.password" />
                        </td>
                    </tr>
                    <tr>
                        <th>followers</th>
                        <td>
                            <input type="text" v-model="userFilter.followers" />
                        </td>
                        <td>
                            <select
                                v-model="userFilter.followersCmd"
                                class="selectBox"
                            >
                                <option value="eq">같은</option>
                                <option value="ne">다른</option>
                                <option value="lt">기준보다 작은</option>
                                <option value="le">같거나 기준보다 작은</option>
                                <option value="gt">기준보다 큰</option>
                                <option value="ge">같거나 기준보다 큰</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>following</th>
                        <td>
                            <input type="text" v-model="userFilter.following" />
                        </td>
                        <td>
                            <select
                                v-model="userFilter.followingCmd"
                                class="selectBox"
                            >
                                <option value="eq">같은</option>
                                <option value="ne">다른</option>
                                <option value="lt">기준보다 작은</option>
                                <option value="le">같거나 기준보다 작은</option>
                                <option value="gt">기준보다 큰</option>
                                <option value="ge">같거나 기준보다 큰</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>birthday</th>
                        <td>
                            <input type="text" v-model="userFilter.birthday" />
                        </td>
                        <td>
                            <select
                                v-model="userFilter.birthdayCmd"
                                class="selectBox"
                            >
                                <option value="eq">같은</option>
                                <option value="ne">다른</option>
                                <option value="lt">기준보다 작은</option>
                                <option value="le">같거나 기준보다 작은</option>
                                <option value="gt">기준보다 큰</option>
                                <option value="ge">같거나 기준보다 큰</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>interestsList</th>
                        <td colspan="2" id="interestBox">
                            <input
                                type="checkbox"
                                v-model="userFilter.interestsList"
                                value="food"
                            />food
                            <input
                                type="checkbox"
                                v-model="userFilter.interestsList"
                                value="music"
                            />music
                            <input
                                type="checkbox"
                                v-model="userFilter.interestsList"
                                value="sports"
                            />sports
                            <input
                                type="checkbox"
                                v-model="userFilter.interestsList"
                                value="fashion"
                            />fashion
                            <input
                                type="checkbox"
                                v-model="userFilter.interestsList"
                                value="travel"
                            />travel
                        </td>
                    </tr>
                    <tr>
                        <th>verifiedCmd</th>
                        <td colspan="2" style="text-align: center">
                            <input
                                type="radio"
                                v-model="userFilter.verifiedCmd"
                                value="true"
                            />true
                            <input
                                type="radio"
                                v-model="userFilter.verifiedCmd"
                                value="false"
                            />false
                        </td>
                    </tr>
                    <!-- <tr>
                    <th>followers</th>
                    <td><input type="text" v-model="followers" /></td>
                    <td>
                        <select
                            name="followersCmd"
                            v-model="followersCmd"
                            class="selectBox"
                        ></select>
                    </td>
                </tr>
                <tr>
                    <th>following</th>
                    <td><input type="text" v-model="following" /></td>
                    <td>
                        <select
                            name="followingCmd"
                            v-model="followingCmd"
                            class="selectBox"
                        ></select>
                    </td>
                </tr>
                <tr>
                    <th>birthday</th>
                    <td><input type="text" v-model="birthday" /></td>
                    <td>
                        <select
                            name="birthdayCmd"
                            v-model="birthdayCmd"
                            class="selectBox"
                        ></select>
                    </td>
                </tr> -->
                </table>
            </div>
            <div style="text-align: right">
                <button @click="reset">초기화</button>
                <button @click="initSearch">전체 검색</button>
                <button @click="search">검색</button>
            </div>
        </div>
    </div>
</template>

<script>
/* const interests = ["food", "travel", "fashion", "music", "sports"];
const cmdOption = {
    "": "-",
    eq: "같은",
    ne: "다른",
    lt: "기준보다 작은",
    le: "같어간 기준보다 작은",
    gt: "기준보다 큰",
    ge: "같거나 기준보다 큰",
}; */
import moment from "moment";
const currentDate = moment().format("YYYY-MM-DD");
export default {
    data() {
        return {
            userFilter: {
                name: "",
                email: "",
                password: "",
                followers: 0,
                followersCmd: "eq",
                following: 0,
                followingCmd: "eq",
                birthday: currentDate,
                birthdayCmd: "le",
                interestsList: [],
                verifiedCmd: "",
            },
            defaultUserFilter: {
                name: "",
                email: "",
                password: "",
                followers: 0,
                followersCmd: "eq",
                following: 0,
                followingCmd: "eq",
                birthday: currentDate,
                birthdayCmd: "le",
                interestsList: [],
                verifiedCmd: "",
            },
        };
    },
    methods: {
        search() {
            console.log(this.userFilter);
            console.log(this.userFilter.interestsList);
            this.$emit("get-user", this.userFilter);
            this.$emit("close-modal");
        },
        initSearch() {
            this.$emit("get-user", {});
            this.$emit("close-modal");
        },
        reset() {
            this.userFilter = { ...this.defaultUserFilter };
        },
        closeModal() {
            this.$emit('close-modal');
        }
    },
};
</script>

<style>
.modal {
    position: fixed;
    z-index: 1;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: auto;
    background-color: rgba(0, 0, 0, 0.911);
}

.modal-content {
    background-color: #fefefe;
    margin: 15% auto;
    padding: 20px;
    border: 1px solid #888;
    width: 80%;
}

.close {
    color: #aaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: black;
    text-decoration: none;
    cursor: pointer;
}
</style>