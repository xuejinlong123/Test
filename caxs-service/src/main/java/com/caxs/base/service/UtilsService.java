package com.caxs.base.service;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @Description:
 * @Author: xuejinlong
 * @system name: 新一代消费金融系统
 * @copyright：长安新生（深圳）金融投资有限公司
 * @Date: Created in  2018/8/2
 */
public class UtilsService {
    /**
     * @description: 将Map对象转化为JavaBean
     * @author
     * @date 2018/8/2 20:02
     */
    public Object convertMap(Class type, Map map)
            throws IntrospectionException, IllegalAccessException,
            InstantiationException, InvocationTargetException {
        // 获取类属性
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        // 创建 JavaBean 对象
        Object obj = type.newInstance();

        // 给 JavaBean 对象的属性赋值
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : propertyDescriptors) {
            //获取属性名
            String propertyName = descriptor.getName();
            //获取属性类型
            String propertyType = descriptor.getPropertyType().getTypeName();
            if (map.containsKey(propertyName)) {
                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                Object[] args = new Object[1];
                //如果数据库类型是BigDecimal，传来不可以是非BigDecimal类型或者为空和null，会产生参数类型不匹配异常，
                //这里将需要类型为BigDecimal的前台数据做类型转换
                if ("java.math.BigDecimal".equals(propertyType)) {
                    if (map.get(propertyName) == null || "".equals(map.get(propertyName))) {
                        continue;
                    }
                    BigDecimal value = new BigDecimal(map.get(propertyName).toString());
                    args[0] = value;
                } else {
                    Object value = map.get(propertyName);
                    args[0] = value;
                }
                descriptor.getWriteMethod().invoke(obj, args);
            }
        }
        return obj;
    }

    /**
     * @description: 普通MD5加密
     * @author
     * @date 2018/8/2 20:16
     */
    public String MD5(String input) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return "check jdk";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        char[] charArray = input.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuilder hexValue = new StringBuilder();
        for (byte md5Byte : md5Bytes) {
            int val = ((int) md5Byte) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

}
