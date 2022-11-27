import {get,post} from "./http";

//判断管理员是否登录成功
export const getLoginStatus = (params) => post(`admin/login/status`,params);

//添加歌手
export const setSinger = (params) => post('singer/add',params);

//查询歌手
export const allSinger = () => get('singer/');

//修改歌手
export const updateSinger = (params) => post('singer/update',params);

//删除歌手
export const delSinger = (params) => post(`singer/delete/`,params);

//查询歌曲
export const songOfSId = (singerId) => get(`song/singer/${singerId}`);

//修改歌曲
export const updateSong = (params) => post('song/update',params);

//删除歌手
export const delSong = (params) => post(`song/delete/`,params);
