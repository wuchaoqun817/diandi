package com.lw.common.generator;

import java.math.BigDecimal;
import java.sql.Types;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.JavaTypeResolver;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

/**
 * 类型转换
 * 
 * @description
 * @author wuchaoqun
 * @date 2019年1月28日
 */
public class GeneratorTypeResolver extends JavaTypeResolverDefaultImpl implements JavaTypeResolver {

	@Override
	public FullyQualifiedJavaType calculateJavaType(IntrospectedColumn introspectedColumn) {
		FullyQualifiedJavaType answer = null;
		JdbcTypeInformation jdbcTypeInformation = typeMap.get(introspectedColumn.getJdbcType());
		if (jdbcTypeInformation != null) {
			switch (introspectedColumn.getJdbcType()) {
			case Types.NUMERIC:
				if (introspectedColumn.getScale() > 0) {// 如果包含小数点则转换成float
					answer = new FullyQualifiedJavaType(Float.class.getName());
				} else {
					if (introspectedColumn.getLength() > 18 || forceBigDecimals) {
						answer = new FullyQualifiedJavaType(BigDecimal.class.getName());
					} else if (introspectedColumn.getLength() > 9) {
						answer = new FullyQualifiedJavaType(Long.class.getName());
					} else if (introspectedColumn.getLength() > 4) {
						answer = new FullyQualifiedJavaType(Integer.class.getName());
					} else if (introspectedColumn.getLength() > 2) {
						answer = new FullyQualifiedJavaType(Short.class.getName());
					} else {
						answer = new FullyQualifiedJavaType(Byte.class.getName());
					}
				}
				break;
			case Types.BIT:
				if (introspectedColumn.getActualColumnName().startsWith("is_")) {
					String javaProperty = introspectedColumn.getJavaProperty();
					javaProperty = javaProperty.replaceFirst("is", "");
					char firstCharacter = javaProperty.toLowerCase().charAt(0);
					javaProperty = firstCharacter + javaProperty.substring(1,javaProperty.length());
					introspectedColumn.setJavaProperty(javaProperty);
					answer = new FullyQualifiedJavaType(boolean.class.getName());
				} else {
					answer = jdbcTypeInformation.getFullyQualifiedJavaType();
				}
				break;
			default:
				answer = jdbcTypeInformation.getFullyQualifiedJavaType();
			}

//            answer = overrideDefaultType(introspectedColumn, answer);
		}
//        Types.ARRAY
		return answer;
	}
}
