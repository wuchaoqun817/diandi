package com.lw.common.utils;

import java.util.Collection;

/**
 * 判断条件。。。
 * @author wuchaoqun
 *
 */
public class JudgeUtils {

    /**
     * 判断集合为空
     * @param object
     * @return
     */
	public static boolean isEmpty(Collection<?> object) {
		if (object == null || object.size() == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断集合不为空
	 * @param object
	 * @return
	 */
	public static boolean isNotEmpty(Collection<?> object) {
		if (object == null || object.size() == 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * 判断数组为空
	 * @param array
	 * @return
	 */
	public static <T> boolean isEmpty(T[] array) {
        if (array == null || array.length == 0) {
            return true;
        }
        return false;
    }
	
	/**
	 * 判断数组不为空
	 * @param array
	 * @return
	 */
	public static <T> boolean isNotEmpty(T[] array) {
        if (array == null || array.length == 0) {
            return false;
        }
        return true;
    }
}
