import createAPI from '../axios'

// 资源申请模板管理 API
const file = {
    template: {
        list: config => createAPI('/file/template/list', 'post', config),
        list1: config => createAPI('/file/template/list1', 'post', config),
        filelist1: config => createAPI('/file/template/filelist1', 'post', config),
        //infolist: config => createAPI('/file/template/infolist', 'post', config),
        save: config => createAPI('/file/template/save', 'post', config)
    },
    file: {
        list: config => createAPI('/file/file/list', 'post', config),
        delete: config => createAPI('/file/file/delete', 'post', config),
        test: config => createAPI('/WiserunCommon/UserMsg', 'post', config)
    }
};

export default file;
