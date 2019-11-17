package com.exercise.demo.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;

import java.lang.reflect.Type;
import java.util.*;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/9/15 23:13
 * @since JDK1.8
 */
public class JsonUtil {

    private JsonUtil() {
    }

    /**
     * json转化成Bean,使用fastjson来处理
     *
     * @param jsonStr json字符串
     * @param <T>     任意类型或自定义类型
     * @return 指定clazz类型对象
     */
    public static <T> T jsonToObject(String jsonStr, Class<? extends Object> clazz) {
        return JSON.parseObject(jsonStr, (Type) clazz);
    }

    /**
     * 泛型json字符串转换为Bean，使用fastJson来处理
     *
     * @param jsonStr
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T jsonToObject(String jsonStr, TypeReference<T> clazz) {
        return JSON.parseObject(jsonStr, clazz);
    }

    /**
     * json转化成Bean,使用fastjson来处理
     *
     * @param jsonStr
     * @return 返回list
     */
    public static List jsonToList(String jsonStr) {
        return JSON.parseObject(jsonStr, new TypeReference<ArrayList>() {
        });
    }

    /**
     * json转化成Bean,使用fastjson来处理
     *
     * @param jsonStr
     * @return 返回set
     */
    public static Set jsonToSet(String jsonStr) {
        return JSON.parseObject(jsonStr, new TypeReference<HashSet>() {
        });
    }

    /**
     * 转化json为json对象
     *
     * @param jsonStr
     * @return
     */
    public static JSONObject jsonToObject(String jsonStr) {
        return JSONObject.parseObject(jsonStr);
    }

//    /**
//     * Bean转化为json，使用Gson
//     * @param obj Bean对象
//     * @return json格式字符串
//     */
//    public static String objectToJson(Object obj){
//        Gson gson = new Gson();
//        return gson.toJson(obj);
//    }

    /**
     * @param node
     * @param path
     * @param values
     * @param nextIndex
     */
    public static void getJsonValue(JsonNode node, String[] path, List<String> values, int nextIndex) {
        if (isEmpty(node)) {
            return;
        }
        // 是路径的最后就直接取值
        if (nextIndex == path.length) {
            if (node.isArray()) {
                for (int i = 0; i < node.size(); i++) {
                    JsonNode child = node.get(i).get(path[nextIndex - 1]);
                    if (isEmpty(child)) {
                        continue;
                    }
                    values.add(child.toString());
                }
            } else {
                JsonNode child = node.get(path[nextIndex - 1]);
                if (!isEmpty(child)) {
                    values.add(child.toString());
                }
            }
            return;
        }
        // 判断是Node下是集合还是一个节点
        node = node.get(path[nextIndex - 1]);
        if (node.isArray()) {
            for (int i = 0; i < node.size(); i++) {
                getJsonValue(node.get(i), path, values, nextIndex + 1);
            }
        } else {
            getJsonValue(node, path, values, nextIndex + 1);
        }
    }

    /**
     * @param jsonObj
     * @param keyPath
     * @return
     */
    public static String select(JSONObject jsonObj, String keyPath) {
        if (null == jsonObj || null == keyPath) {
            return null;
        }
        String[] patharr = keyPath.split("\\.");
        JSONObject current = jsonObj;
        Object retvalue = null;
        for (int i = 0; i < patharr.length; i++) {
            String key = patharr[i];
            retvalue = current.get(key);
            if (i < (patharr.length - 1)) {
                current = (JSONObject) retvalue;
            }
        }
        if (retvalue == null) {
            return "";
        }
        return retvalue.toString();
    }

    /**
     * 判断对象是否为空
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        boolean result = true;
        if (obj == null) {
            return true;
        }
        if (obj instanceof String) {
            result = (obj.toString().trim().length() == 0) || obj.toString().trim().equals("null");
        } else if (obj instanceof Collection) {
            result = ((Collection) obj).size() == 0;
        } else {
            result = ((obj == null) || (obj.toString().trim().length() < 1));
        }
        return result;
    }
}
