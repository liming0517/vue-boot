import createAPI from '../axios'

// 客户管理 API
const custom = {
    info: {
        list: config => createAPI('/custom/info/list', 'post', config),
    },
    restful:{
        auth:config => createAPI('/restfulAuth', 'post', config),
        test:config => createAPI('/restfultest', 'post', config),
    }
};

export default custom;
