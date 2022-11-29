import {get,post} from "./http";

//判断管理员是否登录成功
export const getLoginStatus = (params) => post(`admin/login/status`,params);

//查询歌手
export const allSinger = () => get('singer/');

//查询歌曲
export const songOfSId = (singerId) => get(`song/singer/${singerId}`);

//查询歌单
export const allSongList = () => get(`songList/`);


