package com.ingta.framework.ibatis.util;

/**
 * @author 作者 杨永兴:
 * @version 创建时间：2013-5-8 下午02:25:02 说明：
 */
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ReflectUtils {

    private static final Log logger = LogFactory.getLog(ReflectUtils.class);

    public static void setFieldValue(Object target, String fname, Class<?> ftype, Object fvalue) {
        if (target == null || fname == null || "".equals(fname)
                || (fvalue != null && !ftype.isAssignableFrom(fvalue.getClass()))) {
            return;
        }
        Class<?> clazz = target.getClass();
        try {
            Method method = clazz.getDeclaredMethod("set" + Character.toUpperCase(fname.charAt(0))
                    + fname.substring(1), ftype);
            if (!Modifier.isPublic(method.getModifiers())) {
                method.setAccessible(true);
            }
            method.invoke(target, fvalue);

        } catch (Exception me) {
            if (logger.isDebugEnabled()) {
                logger.debug(me);
            }
            try {
                Field field = clazz.getDeclaredField(fname);
                if (!Modifier.isPublic(field.getModifiers())) {
                    field.setAccessible(true);
                }
                field.set(target, fvalue);
            } catch (Exception fe) {
                if (logger.isDebugEnabled()) {
                    logger.debug(fe);
                }
            }
        }
    }

    public static Class<?> getSuperClassGenricType(Class<?> clazz, int index) {
        // 返回表示此 Class 所表示的实体的直接超类的 Type,也就是extends的类。不包含implements的
        // Type是Class的父类
        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            logger.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
            return Object.class;
        }
        // 返回表示此类型实际类型参数的 Type 对象的数组,数组里放的都是对应类型的Class
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName()
                    + "'s Parameterized Type: " + params.length);
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            logger.warn(clazz.getSimpleName()
                    + " not set the actual class on superclass generic parameter");
            return Object.class;
        }
        return (Class<?>) params[index];
    }

    public static String getFieldClassName(Class<?> clazz, String fname) {
        String reslut = null;
        try {
            Field field = clazz.getDeclaredField(fname);
            System.out.println(field.get(fname).getClass() + "--------------");
            reslut = field.get(fname).getClass().getName();
        } catch (Exception me) {
            if (logger.isDebugEnabled()) {
                logger.debug(me);
            }
        }
        return reslut;
    }

    public static Object getFieldValue(Object target, String fname) {
        Object reslut = null;
        if (target == null || fname == null || "".equals(fname)) {
            return null;
        }
        Class<?> clazz = target.getClass();
        try {
            Field field = clazz.getDeclaredField(fname);
            reslut = field.get(fname);

        } catch (Exception me) {
            if (logger.isDebugEnabled()) {
                logger.debug(me);
            }
        }
        return reslut;
    }

    public static String[] getFieldNames(Class<?> clazz) {
        String[] result = null;
        try {
            Field[] fields = clazz.getDeclaredFields();
            int length = fields.length;
            if (length > 0) {
                result = new String[length];
                for (int i = 0; i < length; i++) {
                    result[i] = fields[i].getName();
                }
            }
        } catch (Exception me) {
            if (logger.isDebugEnabled()) {
                logger.debug(me);
            }
        }
        return result;
    }

    public static String[] getFieldClassNames(Class<?> clazz) {
        String[] result = null;
        try {
            Type genType = clazz.getGenericSuperclass();
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            int length = params.length;
            if (length > 0) {
                result = new String[length];
                StringBuffer sb;
                for (int i = 0; i < length; i++) {
                    sb = new StringBuffer(params[i].toString());
                    result[i] = sb.substring(sb.lastIndexOf(".") + 1).toLowerCase().toString();
                }
            }
        } catch (Exception me) {
            if (logger.isDebugEnabled()) {
                logger.debug(me);
            }
        }
        return result;
    }

    public static Map<String, Object> describe(Object target) {
        Map<String, Object> result = null;
        if (target == null) {
            return null;
        }
        Class<?> clazz = target.getClass();
        try {
            Field[] fields = clazz.getDeclaredFields();
            if (fields != null && fields.length > 0) {
                result = new HashMap<String, Object>();
                for (Field field : fields) {
                    result.put(field.getName(), field.get(field.getName()));
                }
            }
        } catch (Exception me) {
            if (logger.isDebugEnabled()) {
                logger.debug(me);
            }
        }
        return result;
    }
}
