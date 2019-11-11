package com.lw.common.constant;


import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

/**
 * 通用constant
 *
 * @author wuchaoqun
 * @date 2018年12月25日
 * @description
 */
public interface Constants {

	/**
	 * @author wuchaoqun
	 * @date 2019年1月7日
	 * @description 通用
	 */
	interface Common {
		/** 通用有效/无效/删除状态 **/
		int STATUS_VALID = 1;
		int STATUS_INVALID = 0;
		int STATUS_DELETE = -1;

		/** 通用升序/降序状态 **/
		int STATUS_UP = 1;
		int STATUS_DOWN = 0;

		/**开启状态**/
		int STATUS_OPEN = 1;
		/**关闭状态**/
		int STATUS_CLOSED = -1;

		/**正确状态**/
		int STATUS_TRUE = 1;

		/**错误状态**/
		int STATUS_FALSE = 1;

		/**
		 * 默认的分页信息
		 */
		int DEFAULT_PAGE_NUM = 1;
		int DEFAULT_PAGE_SIZE = 10;
		int MAX_PAGE_SIZE = 50;
	}

	/**
	 * @author wuchaoqun
	 * @date 2019年1月7日
	 * @description 缓存相关
	 */
	interface Cache {

		/**cookie**/
		String COOKIE_CODE = "uasCookie";

		/**spring-session**/
		String SPRING_SESSION = "spring:session:";

		/**redis前缀**/
		String REDIS_DICTIONARY_PREX = "dictionary:item_id:";

		/**数据字典-例句练习 每组数量**/
		String E_SENTENCE_EXCERCISE_NUM = "E_SENTENCE_EXCERCISE_NUM";
		/**数据字典-效果检测达标分数**/
		String E_EXAM_SCORE_PASS = "E_EXAM_SCORE_PASS";
		String SPRING_USER = ":user:";


	}


}
