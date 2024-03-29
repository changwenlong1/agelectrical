package com.example.order.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;

public class JsonUtil extends tool.util.JsonUtil {

	public static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

	private static ObjectMapper mapper = new ObjectMapper();
	private static ObjectMapper mapperWithYMDDate = new ObjectMapper();

	static {
	    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false); //去掉默认的时间戳格式
	    mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true); //单引号处理
	    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //反序列化时，属性不存在的兼容处理
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")); //序列化时，日期的统一格式

        mapperWithYMDDate.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapperWithYMDDate.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        mapperWithYMDDate.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapperWithYMDDate.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
	}

	public static ObjectMapper getJsonMapper(){
		 return mapper;
	}


	/**
	 * Json字符串转成对象
	 * @param clz
	 * @param value
	 * @param <T>
	 */
	public static <T> T parseWithOnlyYMDDate(String value,Class<T> clz){
		if (StringUtils.isEmpty(value)) {
			return null;
		}
		try {
			return mapperWithYMDDate.readValue(value, clz);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	public static <T> T parse(byte[] bytes,Class<T> clz){
		try {
			return mapper.readValue(bytes, clz);
		}
		catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	public static <T> T parse(InputStream ins,Class<T> clz){
		try {
			return mapper.readValue(ins, clz);
		}
		catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	public static <T> T  parse(Reader reader,Class<T> clz){
		try {
			return mapper.readValue(reader, clz);
		}
		catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	public static JavaType createCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}

	@SuppressWarnings("unchecked")
	public static <T> T parse(String value, JavaType javaType) {
		if (StringUtils.isEmpty(value)) {
			return null;
		}

		try {
			return (T) mapper.readValue(value, javaType);
		}
		catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T update(String value, T object) {
		try {
			return (T) mapper.readerForUpdating(object).readValue(value);
		}
		catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	public static String writeValueAsString(Object o){
		try {
			return mapper.writeValueAsString(o);
		}
		catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

    public static void writeJson(Object obj, HttpServletResponse resp){
        try {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf8");
            PrintWriter pw=resp.getWriter();
			String str;
			if (!(obj instanceof String)) {
				str = toString(obj);
			} else {
				str = obj.toString();
			}
            pw.print(str);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

	public static void write(OutputStream outs,Object o){
		try {
			mapper.writeValue(outs,o);
		}
		catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	public static void write(Writer writer,Object o){
		try {
			mapper.writeValue(writer,o);
		}
		catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	public static void writeWithOnlyYMDDate(Writer writer,Object o){
		try {
			mapperWithYMDDate.writeValue(writer,o);
		}
		catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	public static String toString(Object o){
		try {
			return mapper.writeValueAsString(o);
		}
		catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	public static byte[] toBytes(Object o){
		try {
			return mapper.writeValueAsBytes(o);
		}
		catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}



}
