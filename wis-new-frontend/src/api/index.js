import system from './modules/system';
import message from './modules/message';
import mock from './modules/mock';
import module from './modules/module';
import audit from './modules/audit';
import file from './modules/file';
import newSystem from './modules/newSystem';
import {postRequest} from './modules/httpRequest';
import custom from './modules/custom';

const api = {
    system: system,
    message:message,
    mock: mock,
    audit:audit,
    file:file,
    module: module,
    postRequest: postRequest,
    newSystem:newSystem,
	custom:custom,
};
export default api;
