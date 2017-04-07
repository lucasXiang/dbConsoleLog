prompt Importing table mt_function_info...
set feedback off
set define off
insert into mt_function_info (ID, FUNCTION_CODE, FUNCTION_VALUE, FUNCTION_URL, STATUS, FUNCTION_TYPE, FUNCTION_ID, PRI, CREATE_DATE, UPDATE_DATE, VERSION, DESCRIPTION)
values (80, '数据查询管理', '数据查询管理', null, 1, '99', null, 80, to_date('24-03-2017 10:59:47', 'dd-mm-yyyy hh24:mi:ss'), null, 0, '查询sql语义管理');

insert into mt_function_info (ID, FUNCTION_CODE, FUNCTION_VALUE, FUNCTION_URL, STATUS, FUNCTION_TYPE, FUNCTION_ID, PRI, CREATE_DATE, UPDATE_DATE, VERSION, DESCRIPTION)
values (81, '查询sql语句的提交', '查询sql语句的提交', '/DBConsole/getConsolePage.htm', 1, '99', 80, 81, to_date('24-03-2017 10:59:47', 'dd-mm-yyyy hh24:mi:ss'), null, 0, '提交查询sql语义');

insert into mt_function_info (ID, FUNCTION_CODE, FUNCTION_VALUE, FUNCTION_URL, STATUS, FUNCTION_TYPE, FUNCTION_ID, PRI, CREATE_DATE, UPDATE_DATE, VERSION, DESCRIPTION)
values (82, '查询sql语句', '查询sql语句', '/DBConsole/getDBQuerySqls.htm', 1, '99', 80, 82, to_date('24-03-2017 10:59:47', 'dd-mm-yyyy hh24:mi:ss'), null, 0, '查询sql语义列表');

insert into mt_function_info (ID, FUNCTION_CODE, FUNCTION_VALUE, FUNCTION_URL, STATUS, FUNCTION_TYPE, FUNCTION_ID, PRI, CREATE_DATE, UPDATE_DATE, VERSION, DESCRIPTION)
values (83, '审核sql语句', '审核sql语句', '/auditConsole/getToAuditQuerySqlList.htm', 1, '99', 80, 83, to_date('24-03-2017 10:59:47', 'dd-mm-yyyy hh24:mi:ss'), null, 0, '审核sql语义列表');

insert into mt_function_info (ID, FUNCTION_CODE, FUNCTION_VALUE, FUNCTION_URL, STATUS, FUNCTION_TYPE, FUNCTION_ID, PRI, CREATE_DATE, UPDATE_DATE, VERSION, DESCRIPTION)
values (88, '用户列表', '用户列表', '/userManager/getUsersList.htm', 1, '99', 86, 88, to_date('07-04-2017 11:28:15', 'dd-mm-yyyy hh24:mi:ss'), null, 0, '用户列表');

insert into mt_function_info (ID, FUNCTION_CODE, FUNCTION_VALUE, FUNCTION_URL, STATUS, FUNCTION_TYPE, FUNCTION_ID, PRI, CREATE_DATE, UPDATE_DATE, VERSION, DESCRIPTION)
values (84, '短信模板管理', '短信模板管理', null, 1, '99', null, 84, to_date('01-04-2017 11:28:35', 'dd-mm-yyyy hh24:mi:ss'), null, 0, '短信模板管理');

insert into mt_function_info (ID, FUNCTION_CODE, FUNCTION_VALUE, FUNCTION_URL, STATUS, FUNCTION_TYPE, FUNCTION_ID, PRI, CREATE_DATE, UPDATE_DATE, VERSION, DESCRIPTION)
values (85, '模板列表', '模板列表', '/smsTempConsole/getSmsTempShowPage.htm', 1, '99', 84, 85, to_date('01-04-2017 11:30:41', 'dd-mm-yyyy hh24:mi:ss'), null, 0, '模板查询');

insert into mt_function_info (ID, FUNCTION_CODE, FUNCTION_VALUE, FUNCTION_URL, STATUS, FUNCTION_TYPE, FUNCTION_ID, PRI, CREATE_DATE, UPDATE_DATE, VERSION, DESCRIPTION)
values (86, '用户管理', '用户管理', null, 1, '99', null, 86, to_date('06-04-2017 13:11:12', 'dd-mm-yyyy hh24:mi:ss'), null, 0, '用户管理');

insert into mt_function_info (ID, FUNCTION_CODE, FUNCTION_VALUE, FUNCTION_URL, STATUS, FUNCTION_TYPE, FUNCTION_ID, PRI, CREATE_DATE, UPDATE_DATE, VERSION, DESCRIPTION)
values (87, '添加用户', '添加用户', '/userManager/getUserInsertedPage.htm', 1, '99', 86, 87, to_date('06-04-2017 13:13:06', 'dd-mm-yyyy hh24:mi:ss'), null, 0, '添加用户');

prompt Done.
