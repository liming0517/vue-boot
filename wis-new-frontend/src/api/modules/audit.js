import createAPI from '../axios'

// 通知消息 API
const audit = {
	auditConf: {
	    add: config => createAPI('/ProcessConfig/ProcessConfig/add', 'post', config),
        update: config => createAPI('/ProcessConfig/ProcessConfig/update', 'post', config),
        delete: config => createAPI('/ProcessConfig/ProcessConfig/delete', 'post', config),
        list: config => createAPI('/ProcessConfig/ProcessConfig/list', 'post', config),
	}
};

export default audit;
