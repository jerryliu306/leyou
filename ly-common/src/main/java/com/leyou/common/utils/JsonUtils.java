package com.leyou.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author: HuYi.Zhang
 * @Description 对对象进行JSON的序列化或者反序列化
 * @create: 2018-04-24 17:20
 **/
public class JsonUtils {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    /**
     * 把一个对象序列化为String类型
     */
    public static String toString(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj.getClass() == String.class) {
            return (String) obj;
        }
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("json序列化出错：" + obj, e);
            return null;
        }
    }

    /**
     * 反序列化
     *
     * @param json   要反序列化的json字符串
     * @param tClass 要反序列化的类型
     * @param <T>    泛型
     * @return 泛型的类型
     */
    public static <T> T toBean(String json, Class<T> tClass) {
        try {
            return MAPPER.readValue(json, tClass);
        } catch (IOException e) {
            logger.error("json解析出错：" + json, e);
            return null;
        }
    }

    /**
     * 把一个json反序列化为List类型
     *
     * @param json   要反序列化的json字符串
     * @param eClass 集合中元素类型
     * @return 元素类型为泛型的List
     */
    public static <E> List<E> toList(String json, Class<E> eClass) {
        try {
            return MAPPER.readValue(json, MAPPER.getTypeFactory().constructCollectionType(List.class, eClass));
        } catch (IOException e) {
            logger.error("json解析出错：" + json, e);
            return null;
        }
    }

    /**
     * 把一个json反序列化为Map类型
     *
     * @param json   要反序列化的json字符串
     * @param kClass 集合中key的类型
     * @param vClass 集合中value的类型
     */
    public static <K, V> Map<K, V> toMap(String json, Class<K> kClass, Class<V> vClass) {
        try {
            return MAPPER.readValue(json, MAPPER.getTypeFactory().constructMapType(Map.class, kClass, vClass));
        } catch (IOException e) {
            logger.error("json解析出错：" + json, e);
            return null;
        }
    }

    /**
     * 把json字符串反序列化，当反序列化的结果比较复杂时，通过这个方法转换
     *
     * @param json 要反序列化的json字符串
     * @param type 在传参时，需要传递TypeReference的匿名内部类，
     *             把要返回的类型写在TypeReference的泛型中，则返回的就是泛型中类型
     */
    public static <T> T nativeRead(String json, TypeReference<T> type) {
        try {
            return MAPPER.readValue(json, type);
        } catch (IOException e) {
            logger.error("json解析出错：" + json, e);
            return null;
        }
    }
}
