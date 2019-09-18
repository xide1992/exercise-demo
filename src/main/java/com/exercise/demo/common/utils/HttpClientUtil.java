package com.exercise.demo.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.exercise.demo.model.enums.HttpMethodType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/9/17 16:58
 */
public class HttpClientUtil {

    /**
     * http 请求方法
     *
     * @param url            地址
     * @param params         参数
     * @param resultMap      返回实体类型
     * @param httpMethodType http请求类型
     * @param timeOut        设置超时时间
     * @param httpHeaders    请求头
     * @param <T>            返回泛型
     * @return 返回值
     * @throws Exception
     */
    public static <T> T requestHttpMethod(String url, Object params, Class<T> resultMap, HttpMethodType httpMethodType, Integer timeOut, HttpHeaders httpHeaders) throws Exception {
        if (httpHeaders == null) {
            httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-type", "application/json; charset=UTF-8");
        }
        if (params == null) {
            params = new Object();
        }
        ResponseEntity<T> responseEntity = null;
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        if (timeOut == null || timeOut <= 0) {
            requestFactory.setConnectTimeout(180000);
            requestFactory.setReadTimeout(180000);
        }
        RestTemplate restTemplate = new RestTemplate(requestFactory);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(objectToMap(params), httpHeaders);
        switch (httpMethodType.getMessage()) {
            case "GET":
                responseEntity = restTemplate.getForEntity(url, resultMap, params);
                break;
            case "POST":
                if (httpHeaders.getContentType().toString().equals("text/plain") || httpHeaders.getContentType().toString().equals("text/xml;charset=utf-8")) {
                    HttpEntity<Object> httpEntity = new HttpEntity<>(params, httpHeaders);
                    responseEntity = restTemplate.postForEntity(url, httpEntity, resultMap);
                } else {
                    responseEntity = restTemplate.postForEntity(url, entity, resultMap);
                }
                break;
            case "PUT":
                responseEntity = restTemplate.exchange(url, HttpMethod.PUT, entity, resultMap);
                break;
            case "DELETE":
                responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, entity, resultMap);
                break;
            default:
                responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, resultMap);
                break;
        }
        if (responseEntity == null || responseEntity.getStatusCodeValue() != 200 || responseEntity.getBody() == null) {
            return null;
        }
//        T result = JSON.toJavaObject(JSON.parseObject(responseEntity.getBody().toString()),resultMap);
        return responseEntity.getBody();
    }

    private static Map<String, Object> objectToMap(Object obj) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            map.put(fieldName, JSONObject.toJSON(value));
        }
        return map;
    }

    /**
     * http 请求方法
     *
     * @param url       地址
     * @param params    参数
     * @param resultMap 返回实体类型
     * @param <T>       返回泛型
     * @return 返回值
     * @throws Exception
     */
    public static <T> T getHttpMethod(String url, Object params, Class<T> resultMap) throws Exception {
        return requestHttpMethod(url, params, resultMap, HttpMethodType.GET, null, null);
    }

    /**
     * http 请求方法
     *
     * @param url       地址
     * @param params    参数
     * @param resultMap 返回实体类型
     * @param <T>       返回泛型
     * @return 返回值
     * @throws Exception
     */
    public static <T> T postHttpMethod(String url, Object params, Class<T> resultMap) throws Exception {
        return requestHttpMethod(url, params, resultMap, HttpMethodType.POST, null, null);
    }

    /**
     * http 请求方法
     *
     * @param url       地址
     * @param params    参数
     * @param resultMap 返回实体类型
     * @param <T>       返回泛型
     * @return 返回值
     * @throws Exception
     */
    public static <T> T postHttpMethod(String url, Object params, Class<T> resultMap, HttpHeaders httpHeaders) throws Exception {
        return requestHttpMethod(url, params, resultMap, HttpMethodType.POST, null, httpHeaders);
    }

    /**
     * http 请求方法
     *
     * @param url            地址
     * @param params         参数
     * @param resultMap      返回实体类型
     * @param httpMethodType http请求类型
     * @param httpHeaders    请求头
     * @param <T>            返回泛型
     * @return 返回值
     * @throws Exception
     */
    public static <T> T requestHttpMethod(String url, Object params, Class<T> resultMap, HttpMethodType httpMethodType, HttpHeaders httpHeaders) throws Exception {
        return requestHttpMethod(url, params, resultMap, httpMethodType, null, httpHeaders);
    }

    /**
     * http 请求方法
     *
     * @param url            地址
     * @param params         参数
     * @param resultMap      返回实体类型
     * @param httpMethodType http请求类型
     * @param <T>            返回泛型
     * @return 返回值
     * @throws Exception
     */
    public static <T> T requestHttpMethod(String url, Object params, Class<T> resultMap, HttpMethodType httpMethodType) throws Exception {
        return requestHttpMethod(url, params, resultMap, httpMethodType, null, null);
    }

    /**
     * http 请求方法
     *
     * @param url            地址
     * @param resultMap      返回实体类型
     * @param httpMethodType http请求类型
     * @param <T>            返回泛型
     * @return 返回值
     * @throws Exception
     */
    public static <T> T requestHttpMethod(String url, Class<T> resultMap, HttpMethodType httpMethodType) throws Exception {
        return requestHttpMethod(url, null, resultMap, httpMethodType, null, null);
    }
}