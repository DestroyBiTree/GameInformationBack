package com.promise.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * HTTP请求工具类，用于发送GET和POST请求并返回JSON数据
 */
public class HttpClientUtil {

    private static final RestTemplate restTemplate = new RestTemplate();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 发送GET请求
     * @param url 请求URL
     * @param params 请求参数
     * @return JSON响应数据
     */
    public static Map<String, Object> sendGetRequest(String url, Map<String, Object> params) {
        try {
            // 构建带查询参数的URL
            StringBuilder urlBuilder = new StringBuilder(url);
            if (params != null && !params.isEmpty()) {
                urlBuilder.append("?");
                int i = 0;
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    if (i > 0) {
                        urlBuilder.append("&");
                    }
                    urlBuilder.append(entry.getKey()).append("=")
                            .append(entry.getValue() != null ? entry.getValue().toString() : "");
                    i++;
                }
            }
            String finalUrl = urlBuilder.toString();

            // 构建请求头
            HttpHeaders headers = new HttpHeaders();

            // 构建请求实体（无请求体）
            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            // 发送GET请求
            ResponseEntity<Map> responseEntity = restTemplate.exchange(
                    finalUrl, HttpMethod.GET, requestEntity, Map.class);

            // 返回响应体
            return responseEntity.getBody();
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            // 处理HTTP错误
            throw new RuntimeException("HTTP请求失败: " + e.getStatusCode() + " - " + e.getResponseBodyAsString(), e);
        } catch (Exception e) {
            // 处理其他异常
            throw new RuntimeException("发送GET请求失败", e);
        }
    }

    /**
     * 发送POST请求
     * @param url 请求URL
     * @param requestBody 请求体
     * @return JSON响应数据
     */
    public static Map<String, Object> sendPostRequest(String url, Object requestBody) {
        try {
            // 构建请求头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // 构建请求实体
            HttpEntity<Object> requestEntity = new HttpEntity<>(requestBody, headers);

            // 打印请求地址
            System.out.println("发送POST请求到: " + url);
            
            // 发送POST请求
            ResponseEntity<Map> responseEntity = restTemplate.postForEntity(
                    url, requestEntity, Map.class);

            // 返回响应体
            return responseEntity.getBody();
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            // 处理HTTP错误
            throw new RuntimeException("HTTP请求失败: " + e.getStatusCode() + " - " + e.getResponseBodyAsString(), e);
        } catch (Exception e) {
            // 处理其他异常
            throw new RuntimeException("发送POST请求失败", e);
        }
    }
}