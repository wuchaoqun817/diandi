package com.lw.common.generator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * @description Lombok插件
 * @author wuchaoqun
 * @date 2019年1月30日
 */
public class LombokPlugin extends PluginAdapter{

    private final Collection<Annotations> annotations;

//    private static final String CUSTOM_CLASS    = "customClass";
//    private static final String DELIMITER        = ",";
//    private static final String DELIMITER_FORMAT = "@";


    public LombokPlugin() {
        annotations = new LinkedHashSet<Annotations>(Annotations.values().length);
    }

    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(
            TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable
    ) {
        addAnnotations(topLevelClass);
        return true;
    }

    @Override
    public boolean modelPrimaryKeyClassGenerated(
            TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable
    ) {
        addAnnotations(topLevelClass);
        return true;
    }

    @Override
    public boolean modelRecordWithBLOBsClassGenerated(
            TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable
    ) {
        addAnnotations(topLevelClass);
        return true;
    }

//    @Override
//    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
//        System.out.println(field.getName());
////        if(field.getName().equals("mathLearningStepRecords")){
//
//            String fmts = introspectedTable.getTableConfigurationProperty(CUSTOM_CLASS);
//
//
//            if (fmts != null && !"".equals(fmts)) {
//                //,分割每个字段
//                String[] columns = fmts.split(DELIMITER);
//                for (String column : columns) {
//                    //@分割key/value
//                    String[] formats = column.split(DELIMITER_FORMAT);
//                    String anno = formats[1];
//                    if (formats[0].equals(field.getName())) {
//                        System.out.println(introspectedColumn.getJdbcTypeName() + "___" + introspectedColumn.getFullyQualifiedJavaType().getFullyQualifiedName());
//                        System.out.println("-----------------" + introspectedColumn.getJdbcTypeName()+"____"+introspectedColumn.getJdbcType()+"____");
//
//                        FullyQualifiedJavaType type = new FullyQualifiedJavaType(anno);
//                        FullyQualifiedJavaType typeFinal = new FullyQualifiedJavaType("java.util.List<"+ type+">");
//                        field.setType(typeFinal);
//                        topLevelClass.addImportedType(type);
//                        topLevelClass.addImportedType(new FullyQualifiedJavaType("java.util.List"));//java.util.List
//                    }
//                }
//            }
//
//
////        }
//        return super.modelFieldGenerated(field, topLevelClass, introspectedColumn, introspectedTable, modelClassType);
//    }

    @Override
    public boolean modelGetterMethodGenerated(org.mybatis.generator.api.dom.java.Method method,
            TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable,
            ModelClassType modelClassType) {
         super.modelGetterMethodGenerated(method, topLevelClass, introspectedColumn, introspectedTable, modelClassType);
         return false;
    }

    @Override
    public boolean modelSetterMethodGenerated(org.mybatis.generator.api.dom.java.Method method,
            TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable,
            ModelClassType modelClassType) {
        super.modelSetterMethodGenerated(method, topLevelClass, introspectedColumn, introspectedTable, modelClassType);
        return false;
    }

    private void addAnnotations(TopLevelClass topLevelClass) {
        for (Annotations annotation : annotations) {
            topLevelClass.addImportedType(annotation.javaType);
            topLevelClass.addAnnotation(annotation.asAnnotation());
        }
    }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);

        //setter/getter
        annotations.add(Annotations.GETTER);
        annotations.add(Annotations.SETTER);

        for (String annotationName : properties.stringPropertyNames()) {
            if (annotationName.contains(".")) {
                // Not an annotation name
                continue;
            }
            String value = properties.getProperty(annotationName);
            if (!Boolean.parseBoolean(value)) {
                // The annotation is disabled, skip it
                continue;
            }
            Annotations annotation = Annotations.getValueOf(annotationName);
            if (annotation == null) {
                continue;
            }
            String optionsPrefix = annotationName + ".";
            for (String propertyName : properties.stringPropertyNames()) {
                if (!propertyName.startsWith(optionsPrefix)) {
                    // A property not related to this annotation
                    continue;
                }
                String propertyValue = properties.getProperty(propertyName);
                annotation.appendOptions(propertyName, propertyValue);
                annotations.add(annotation);
                annotations.addAll(Annotations.getDependencies(annotation));
            }
        }
    }

   /* @Override
    public boolean clientGenerated(
            Interface interfaze,
            TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable
    ) {
        interfaze.addImportedType(new FullyQualifiedJavaType(
                "org.apache.ibatis.annotations.Mapper"));
        interfaze.addAnnotation("@Mapper");
        return true;
    }*/

    private enum Annotations {
        DATA("data", "@Data", "lombok.Data"),
        BUILDER("builder", "@Builder", "lombok.Builder"),
        ALL_ARGS_CONSTRUCTOR("allArgsConstructor", "@AllArgsConstructor", "lombok.AllArgsConstructor"),
        NO_ARGS_CONSTRUCTOR("noArgsConstructor", "@NoArgsConstructor", "lombok.NoArgsConstructor"),
        ACCESSORS("accessors", "@Accessors", "lombok.experimental.Accessors"),
        TO_STRING("toString", "@ToString", "lombok.ToString"),
        GETTER("getter","@Getter","lombok.Getter"),
        SETTER("setter","@Setter","lombok.Setter");


        private final String paramName;
        private final String name;
        private final FullyQualifiedJavaType javaType;
        private final List<String> options;


        Annotations(String paramName, String name, String className) {
            this.paramName = paramName;
            this.name = name;
            this.javaType = new FullyQualifiedJavaType(className);
            this.options = new ArrayList<String>();
        }

        private static Annotations getValueOf(String paramName) {
            for (Annotations annotation : Annotations.values())
                if (String.CASE_INSENSITIVE_ORDER.compare(paramName, annotation.paramName) == 0)
                    return annotation;

            return null;
        }

        private static Collection<Annotations> getDependencies(Annotations annotation) {
            if (annotation == ALL_ARGS_CONSTRUCTOR)
                return Collections.singleton(NO_ARGS_CONSTRUCTOR);
            else
                return Collections.emptyList();
        }

        // A trivial quoting.
        // Because Lombok annotation options type is almost String or boolean.
        private static String quote(String value) {
            if (Boolean.TRUE.toString().equals(value) || Boolean.FALSE.toString().equals(value))
                // case of boolean, not passed as an array.
                return value;
            return value.replaceAll("[\\w]+", "\"$0\"");
        }

        private void appendOptions(String key, String value) {
            String keyPart = key.substring(key.indexOf(".") + 1);
            String valuePart = value.contains(",") ? String.format("{%s}", value) : value;
            this.options.add(String.format("%s=%s", keyPart, quote(valuePart)));
        }

        private String asAnnotation() {
            if (options.isEmpty()) {
                return name;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(name);
            sb.append("(");
            boolean first = true;
            for (String option : options) {
                if (first) {
                    first = false;
                } else {
                    sb.append(", ");
                }
                sb.append(option);
            }
            sb.append(")");
            return sb.toString();
        }
    }


}
