import createAPI from '../axios'

// 通知消息 API
const message = {
    message: {
        list: config => createAPI('/message/message/list', 'post', config),
        read: config => createAPI('/message/message/read', 'post', config),
        unread: config => createAPI('/message/message/unread', 'post', config),
    },
	messageconfig: {
	    list: config => createAPI('/message/messageconfig/list', 'post', config),
	    add: config => createAPI('/message/messageconfig/add', 'post', config),
	    update: config => createAPI('/message/messageconfig/update', 'post', config),
	    delete: config => createAPI('/message/messageconfig/delete', 'post', config),
	},
    operate: {
        list: config => createAPI('/Operate/Operate/list', 'post', config),
        update: config => createAPI('/Operate/Operate/update', 'post', config),
        delete: config => createAPI('/Operate/Operate/delete', 'post', config),
        operatelist: config => createAPI('/Operate/Operate/operatelist', 'post', config),
    }
};

export default message;
