<script>
import axios from "axios";

export default {
    data() {
        return {
            result: [],
        };
    },
    methods: {
      //axios 호출만 담당하는 애니까 url같이 넘겨받게끔
        async post(url, body) {
            console.log("getUsers 메서드");
            return axios.post(url, body)
              .then((response) => {
                return response.data
              })
              .catch((error) => {
                console.log(error);
                throw error;
              })
        },
        modifyUser() {},
        deleteUser() {},
    },
};

/* function post(url, body) {
    return axios.post(url, body)
        .then((response) => {
          return response.data
        })
        .catch((error) => {
            throw error;
        });
} */
</script>