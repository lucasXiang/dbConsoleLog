prompt Importing table dbconsole_datasource...
set feedback off
set define off
insert into dbconsole_datasource (DBCONSOLE_DATASOURCE_ID, DBURL, DRIVER_CLASS, USERNAME, PWD, DBTYPE, SID, TS)
values ('1', 'jdbc:oracle:thin:@172.22.20.102:1521:mpaydb', 'oracle.jdbc.driver.OracleDriver', 'US3_MOTOPAY', '111111', '0', 'mpaydb', '2017-03-22 15:49:15');

insert into dbconsole_datasource (DBCONSOLE_DATASOURCE_ID, DBURL, DRIVER_CLASS, USERNAME, PWD, DBTYPE, SID, TS)
values ('2', 'jdbc:oracle:thin:@172.22.20.103:1521:unsmpos', 'oracle.jdbc.driver.OracleDriver', 'mpos', '111111', '0', 'unsmpos', '2017-03-22 15:50:42');

prompt Done.
