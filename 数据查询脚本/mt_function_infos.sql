prompt Importing table mt_function_info...
set feedback off
set define off
insert into mt_function_info (ID, FUNCTION_CODE, FUNCTION_VALUE, FUNCTION_URL, STATUS, FUNCTION_TYPE, FUNCTION_ID, PRI, CREATE_DATE, UPDATE_DATE, VERSION, DESCRIPTION)
values (80, '���ݲ�ѯ����', '���ݲ�ѯ����', null, 1, '99', null, 80, to_date('24-03-2017 10:59:47', 'dd-mm-yyyy hh24:mi:ss'), null, 0, '��ѯsql�������');

insert into mt_function_info (ID, FUNCTION_CODE, FUNCTION_VALUE, FUNCTION_URL, STATUS, FUNCTION_TYPE, FUNCTION_ID, PRI, CREATE_DATE, UPDATE_DATE, VERSION, DESCRIPTION)
values (81, '��ѯsql�����ύ', '��ѯsql�����ύ', '/DBConsole/getConsolePage.htm', 1, '99', 80, 81, to_date('24-03-2017 10:59:47', 'dd-mm-yyyy hh24:mi:ss'), null, 0, '�ύ��ѯsql����');

insert into mt_function_info (ID, FUNCTION_CODE, FUNCTION_VALUE, FUNCTION_URL, STATUS, FUNCTION_TYPE, FUNCTION_ID, PRI, CREATE_DATE, UPDATE_DATE, VERSION, DESCRIPTION)
values (82, '��ѯsql���', '��ѯsql���', '/DBConsole/getDBQuerySqls.htm', 1, '99', 80, 82, to_date('24-03-2017 10:59:47', 'dd-mm-yyyy hh24:mi:ss'), null, 0, '��ѯsql�����б�');

insert into mt_function_info (ID, FUNCTION_CODE, FUNCTION_VALUE, FUNCTION_URL, STATUS, FUNCTION_TYPE, FUNCTION_ID, PRI, CREATE_DATE, UPDATE_DATE, VERSION, DESCRIPTION)
values (83, '���sql���', '���sql���', '/auditConsole/getToAuditQuerySqlList.htm', 1, '99', 80, 83, to_date('24-03-2017 10:59:47', 'dd-mm-yyyy hh24:mi:ss'), null, 0, '���sql�����б�');

insert into mt_function_info (ID, FUNCTION_CODE, FUNCTION_VALUE, FUNCTION_URL, STATUS, FUNCTION_TYPE, FUNCTION_ID, PRI, CREATE_DATE, UPDATE_DATE, VERSION, DESCRIPTION)
values (88, '�û��б�', '�û��б�', '/userManager/getUsersList.htm', 1, '99', 86, 88, to_date('07-04-2017 11:28:15', 'dd-mm-yyyy hh24:mi:ss'), null, 0, '�û��б�');

insert into mt_function_info (ID, FUNCTION_CODE, FUNCTION_VALUE, FUNCTION_URL, STATUS, FUNCTION_TYPE, FUNCTION_ID, PRI, CREATE_DATE, UPDATE_DATE, VERSION, DESCRIPTION)
values (84, '����ģ�����', '����ģ�����', null, 1, '99', null, 84, to_date('01-04-2017 11:28:35', 'dd-mm-yyyy hh24:mi:ss'), null, 0, '����ģ�����');

insert into mt_function_info (ID, FUNCTION_CODE, FUNCTION_VALUE, FUNCTION_URL, STATUS, FUNCTION_TYPE, FUNCTION_ID, PRI, CREATE_DATE, UPDATE_DATE, VERSION, DESCRIPTION)
values (85, 'ģ���б�', 'ģ���б�', '/smsTempConsole/getSmsTempShowPage.htm', 1, '99', 84, 85, to_date('01-04-2017 11:30:41', 'dd-mm-yyyy hh24:mi:ss'), null, 0, 'ģ���ѯ');

insert into mt_function_info (ID, FUNCTION_CODE, FUNCTION_VALUE, FUNCTION_URL, STATUS, FUNCTION_TYPE, FUNCTION_ID, PRI, CREATE_DATE, UPDATE_DATE, VERSION, DESCRIPTION)
values (86, '�û�����', '�û�����', null, 1, '99', null, 86, to_date('06-04-2017 13:11:12', 'dd-mm-yyyy hh24:mi:ss'), null, 0, '�û�����');

insert into mt_function_info (ID, FUNCTION_CODE, FUNCTION_VALUE, FUNCTION_URL, STATUS, FUNCTION_TYPE, FUNCTION_ID, PRI, CREATE_DATE, UPDATE_DATE, VERSION, DESCRIPTION)
values (87, '����û�', '����û�', '/userManager/getUserInsertedPage.htm', 1, '99', 86, 87, to_date('06-04-2017 13:13:06', 'dd-mm-yyyy hh24:mi:ss'), null, 0, '����û�');

prompt Done.
