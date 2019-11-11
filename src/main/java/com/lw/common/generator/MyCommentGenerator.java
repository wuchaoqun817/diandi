package com.lw.common.generator;

import static org.mybatis.generator.internal.util.StringUtility.isTrue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.JavaElement;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.MergeConstants;
import org.mybatis.generator.config.PropertyRegistry;

import com.lw.common.utils.StringUtils;


/**
 * mybatis 生cheng类
 * @author wuchaoqun
 * @date 2018年12月26日
 * @description
 */
public class MyCommentGenerator implements CommentGenerator{
    private Properties properties;
    private Properties systemPro;
    private boolean suppressDate;
    private boolean suppressAllComments;
    private String currentDateStr;

    public MyCommentGenerator() {
        super();
        properties = new Properties();
        systemPro = System.getProperties();
        suppressDate = false;
        suppressAllComments = false;
        currentDateStr = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
    }

    public void addJavaFileComment(CompilationUnit compilationUnit) {
        return;
    }

    /**
     * Adds a suitable comment to warn users that the element was generated, and
     * when it was generated.
     */
    public void addComment(XmlElement xmlElement) {
        return;
    }

    public void addRootComment(XmlElement rootElement) {
        return;
    }

    public void addConfigurationProperties(Properties properties) {
        this.properties.putAll(properties);

        suppressDate = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_DATE));

        suppressAllComments = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));
    }

    /**
     * This method adds the custom javadoc tag for. You may do nothing if you do
     * not wish to include the Javadoc tag - however, if you do not include the
     * Javadoc tag then the Java merge capability of the eclipse plugin will
     * break.
     * 
     * @param javaElement
     *            the java element
     */
    protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
        javaElement.addJavaDocLine(" *");
        StringBuilder sb = new StringBuilder();
        sb.append(" * ");
        sb.append(MergeConstants.NEW_ELEMENT_TAG);
        if (markAsDoNotDelete) {
            sb.append(" do_not_delete_during_merge");
        }
        String s = getDateString();
        if (s != null) {
            sb.append(' ');
            sb.append(s);
        }
        javaElement.addJavaDocLine(sb.toString());
    }

    /**
     * This method returns a formated date string to include in the Javadoc tag
     * and XML comments. You may return null if you do not want the date in
     * these documentation elements.
     * 
     * @return a string representing the current timestamp, or null
     */
    protected String getDateString() {
        String result = null;
        if (!suppressDate) {
            result = currentDateStr;
        }
        return result;
    }

    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }
        if(introspectedTable.getRemarks() == null) {
        	return;
        }
        StringBuilder sb = new StringBuilder();
        innerClass.addJavaDocLine("/**");
        sb.append(" * @author ");
        sb.append(systemPro.getProperty("user.name"));
        innerClass.addJavaDocLine(sb.toString().replace("\n", " "));
        sb.setLength(0);
        sb.append(" * @date ");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String date = sdf.format(new Date());
        sb.append(date);
        innerClass.addJavaDocLine(sb.toString().replace("\n", " "));
        sb.setLength(0);
        sb.append(" * @description ");
        sb.append(introspectedTable.getRemarks());
        innerClass.addJavaDocLine(sb.toString().replace("\n", " "));
        innerClass.addJavaDocLine(" */");
    }

    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        innerEnum.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        innerEnum.addJavaDocLine(sb.toString().replace("\n", " "));
        innerEnum.addJavaDocLine(" */");
    }

    public void addFieldComment(Field field, IntrospectedTable introspectedTable,
            IntrospectedColumn introspectedColumn) {
        if (suppressAllComments) {
            return;
        }
        if(introspectedColumn.getRemarks() == null) {
        	return;
        }
        field.addAnnotation("@ApiModelProperty(\""+introspectedColumn.getRemarks()+"\")");	
    }

    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        field.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        field.addJavaDocLine(sb.toString().replace("\n", " "));
        field.addJavaDocLine(" */");
    }

	public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
		if (suppressAllComments) {
			return;
		}
		method.addJavaDocLine("/**");
		StringBuilder sb = new StringBuilder();
		sb.append(" * ");
		switch (method.getName()) {
		case GeneratorConstants.METHOD_DEL_BY_KEY:
			sb.append("根据主键删除实体");
			break;
		case GeneratorConstants.METHOD_INSERT:
			sb.append("插入实体（包含空值）");
			break;
		case GeneratorConstants.METHOD_INSERT_SELECTIVE:
			sb.append("插入实体（不包含空值）");
			break;
		case GeneratorConstants.METHOD_SELECT_BY_KEY:
			sb.append("根据主键查询实体");
			break;
		case GeneratorConstants.METHOD_UPDATE_BY_KEY_SELECTIVE:
			sb.append("根据主键更新实体（不包含实体）");
			break;
		case GeneratorConstants.METHOD_UPDATE_BY_KEY:
			sb.append("根据主键更新实体（包含实体）");
			break;
		default:
			break;
		}
		method.addJavaDocLine(sb.toString().replace("\n", " "));
		method.addJavaDocLine(" */");
	}

    public void addGetterComment(Method method, IntrospectedTable introspectedTable,
            IntrospectedColumn introspectedColumn) {
    }

    public void addSetterComment(Method method, IntrospectedTable introspectedTable,
            IntrospectedColumn introspectedColumn) {
    }

    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
        if (suppressAllComments) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        innerClass.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        innerClass.addJavaDocLine(sb.toString().replace("\n", " "));
        sb.setLength(0);
        sb.append(" * @author ");
        sb.append(systemPro.getProperty("user.name"));
        sb.append(" ");
        sb.append(currentDateStr);
        innerClass.addJavaDocLine(" */");
    }

	@Override
	public void addClassAnnotation(InnerClass arg0, IntrospectedTable arg1, Set<FullyQualifiedJavaType> arg2) {
	}

	/**
	 * 这个方法木有用，不知道为什么
	 */
	@Override
	public void addFieldAnnotation(Field arg0, IntrospectedTable arg1, Set<FullyQualifiedJavaType> arg2) {
	}

	/**
	 * 这个方法木有用，不知道为什么
	 */
	@Override
	public void addFieldAnnotation(Field arg0, IntrospectedTable arg1, IntrospectedColumn arg2,
			Set<FullyQualifiedJavaType> arg3) {
		if(StringUtils.isNotEmpty(arg2.getRemarks())) {
			arg0.addAnnotation("@ApiModelProperty(\""+arg2.getRemarks()+"\")");	
		}
	}

	@Override
	public void addGeneralMethodAnnotation(Method arg0, IntrospectedTable arg1, Set<FullyQualifiedJavaType> arg2) {
	}

	@Override
	public void addGeneralMethodAnnotation(Method arg0, IntrospectedTable arg1, IntrospectedColumn arg2,
			Set<FullyQualifiedJavaType> arg3) {
	}

	@Override
	public void addModelClassComment(TopLevelClass arg0, IntrospectedTable arg1) {
		arg0.addImportedType("io.swagger.annotations.ApiModelProperty");
		addClassComment(arg0, arg1);
	}
    
    
}
