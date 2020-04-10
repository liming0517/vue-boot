import createAPI from '../axios'

const newSystem = {
    orderInfo: {
        search: config => createAPI('/listBuiOrderInfo.do', 'post', config),
        deleteRow: config => createAPI('/delBuiOrderInfo.do', 'post', config),
        updateRow: config => createAPI('/updateBuiOrderInfo.do', 'post', config)
    },
    resource: {
        search: config => createAPI('/listTopMenu.do', 'post', config),
        deleteRow: config => createAPI('/delRRole.do', 'post', config),
        addRow: config => createAPI('/addRRole.do', 'post', config)
    }
};

export default newSystem;
