// 루트 컨텍스트 경로
const protocol = "http://"
const domain = '10.10.18.90';
const port = '39080';
const contextPath = "/demo";

const url = protocol + domain + ':' + port + contextPath;
const getUsersUrl = url + `/user/get`;
const modifyUserUrl = url + `/user/update`;
const deleteUserUrl = url + `/user/delete`;

const path = {
  getUsersUrl,
  modifyUserUrl,
  deleteUserUrl,
}

export default path
