package com.xshhope.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * 分页参数处理工具
 * 
 * @author xshhope
 *
 */
@Slf4j
public class PageUtil {

	/**
	 * 分页参数，起始位置，从0开始
	 */
	public static final String START = "page";
	/**
	 * 分页参数，每页数据条数
	 */
	public static final String LENGTH = "size";

	/**
	 * 转换并校验分页参数<br>
	 * mybatis中limit #{start, JdbcType=INTEGER}, #{length,
	 * JdbcType=INTEGER}里的类型转换貌似失效<br>
	 * 我们这里先把他转成Integer的类型
	 * 
	 * @param params
	 * @param required
	 *            分页参数是否是必填
	 */
	public static void pageParamConver(Map<String, Object> params, boolean required) {
		Integer length = MapUtils.getInteger(params, LENGTH);
		Integer start = MapUtils.getInteger(params, START);
		if (required) {// 分页参数必填时，校验参数
			if (params == null || !params.containsKey(START) || !params.containsKey(LENGTH)) {
				throw new IllegalArgumentException("请检查分页参数," + START + "," + LENGTH);
			}
		}

		if (!CollectionUtils.isEmpty(params)) {
			if (params.containsKey(LENGTH)) {
				if (length < 0) {
					log.error("length：{}，重置为0", length);
					length = 0;
				}
				params.put(LENGTH, length);
			}

			if (params.containsKey(START)) {
				if (start < 0) {
					log.error("start：{}，重置为0", start);
					start = 0;
				}
				params.put(START, (start - 1) * length);
			}
		}
	}
}
