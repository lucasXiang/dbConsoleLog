package com.tech.member.constant;

public class PubConstant {
	/**
	 * 运行成功
	 */
	public static final String RUN_SUCCESS="0";
	/**
	 * 运行失败
	 */
	public static final String RUN_FAILURE="1";
	/**
	 * 业务异常
	 */
	public static final String RUN_BUSINESS_ERROR="2";
	
	public static final boolean IS_TRUE=true;
	public static final boolean IS_FALSE=false;
	/**
	 * 审核页面的flag标签
	 */
	public static final String AUDIT_PAGE_YES="0";
	public static final String AUDIT_PAGE_NO="1";
	/**
	 * 审核状态
	 */
	public static final String AUDIT_STATUS_INIT="1";
	public static final String AUDIT_STATUS_PASSED="0";
	public static final String AUDIT_STATUS_NOTPASSED="2";
	public static final String AUDIT_STATUS_EXCUTED="3";
	
	/**
	 * oracle数据库及其驱动类
	 */
	public static final String DB_TYPE_ORACLE="0";
	public static final String DB_DRIVER_CLASS_ORACLE="oracle.jdbc.driver.OracleDriver";
	/**
	 * mysql数据库及其驱动类
	 */
	public static final String DB_TYPE_MYSQL="1";
	public static final String DB_DRIVER_CLASS_MYSQL="com.mysql.jdbc.Driver";
	/**
	 * sql执行的最大输出量
	 */
	public static final String SQL_EXCUTED_MAX_SIZE="sqlExcutedMaxSize";
	/**
	 * 分页的初始化设置
	 */
	public static final Integer PAGE_PARAMS_INIT_STARTROW=0;
	public static final Integer PAGE_PARAMS_INIT_PAGESIZE=10;
	public static final Integer PAGE_PARAMS_INIT_PAGENUM=1;
	/**
	 * 是否修改页面
	 */
	public static final String IF_MODIFY_YES="0";
	public static final String IF_MODIFY_NO="1";
}
