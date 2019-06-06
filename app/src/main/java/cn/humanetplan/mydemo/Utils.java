package cn.humanetplan.mydemo;

import android.text.TextUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**

 * date     :  2019/6/6.
 */

public class Utils {
    //根据属性名称获取对应的值
    public static <T> String getAttributeValue(T t, String typeName) {
        String str = "";
        if (t == null) {
            return str;
        }
        if (typeName == null || TextUtils.isEmpty(typeName)) {
            return str;
        }
        try {
            Field field = t.getClass().getDeclaredField(typeName);
            field.setAccessible(true);
            str = (String) field.get(t);
            return str;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return str;
    }

    //根据属性名称设置该属性在实体中的值
    public static <T> T setValueByFieldName(String typeName, T t, String val) {
        String value = "";
        try {
            if (typeName == null) {

            } else {
                Class<?> clz = t.getClass();
                Field field = clz.getDeclaredField(typeName);
                field.setAccessible(true);

                if (field.getGenericType().toString().equals("class java.lang.String")) {
                    Method method = t.getClass().getMethod("set" + getMethodName(field.getName()), String.class);
                    method.invoke(t, val);

                }

                if (field.getGenericType().toString().equals("class java.lang.Integer"))//
                {
                    Method method = t.getClass().getMethod("set" + getMethodName(field.getName()), Integer.class);
                    method.invoke(t, Integer.parseInt(val));
                }
            }
        } catch (Exception e) {

        } finally {
            return t;
        }

    }


    /**
     * 将属性名称的首字母变成大写
     */
    public static String getMethodName(String fieldName) {
        byte[] bytes = fieldName.getBytes();
        bytes[0] = (byte) (bytes[0] - 'a' + 'A');
        return new String(bytes);
    }
}
