package com.lw.common.generator;


/**
 * mybatis生成的基本操作，常量类
 * @author wuchaoqun
 * @date 2018年12月28日
 * @description
 */
public interface GeneratorConstants {

	/**根据id删除**/
	String METHOD_DEL_BY_KEY = "deleteByPrimaryKey";
	
	/**插入（包含空值）**/
	String METHOD_INSERT = "insert";
	
	/**插入（不包含空值）**/
	String METHOD_INSERT_SELECTIVE = "insertSelective";
	
	/**根据主键查询**/
	String METHOD_SELECT_BY_KEY = "selectByPrimaryKey";
	
	/**根据主键更新不为空的值**/
	String METHOD_UPDATE_BY_KEY_SELECTIVE = "updateByPrimaryKeySelective";
	
	/**根据主键更新（包含空值）**/
	String METHOD_UPDATE_BY_KEY = "updateByPrimaryKey";
}
