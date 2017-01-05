package com.chinamcloud.devops.utils.persistence;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

/**
 * 查询条件过滤器.
 * 
 * <pre>
 * EQ      等价于 SQL中的   … where x.lastname = ?1 and x.firstname = ?2
 * LIKE    等价于 SQL中的   … where x.firstname like ?1
 * GT      等价于 SQL中的   … where x.age > ?1
 * GTE     等价于 SQL中的   … where x.age >= ?1
 * LT      等价于 SQL中的   … where x.age < ?1
 * LTE     等价于 SQL中的   … where x.age =< ?1
 * NOT     等价于 SQL中的   … where x.lastname <> ?1
 * IsNull  等价于 SQL中的   … where x.age is null
 * NotNull 等价于 SQL中的   … where x.age not null
 * </pre>
 * 
 * @author liukai
 * 
 */
public class SearchFilter {

	public enum Operator {
		EQ, GT, GTE, IsNull, LIKE, LT, LTE, NOT, NotNull
	}

	/**
	 * 解析查询参数.
	 * 
	 * <pre>
	 * searchParams中key的格式为operator_fieldname.
	 * eg:EQ_name
	 * </pre>
	 */
	public static Map<String, SearchFilter> parse(Map<String, Object> searchParams) {

		Map<String, SearchFilter> filters = new HashMap<>();

		for (Entry<String, Object> entry : searchParams.entrySet()) {

			// 过滤掉空值
			String key = entry.getKey();
			Object value = entry.getValue();
			if (StringUtils.isBlank((String) value.toString())) {
				continue;
			}

			// 拆分operator与filedAttribute
			String[] names = StringUtils.split(key, "_");
			if (names.length != 2) {
				throw new IllegalArgumentException(key + " 不是一个有效的查询参数!");
			}

			String filedName = names[1];
			Operator operator = Operator.valueOf(names[0]);

			// 构建searchFilter对象.
			SearchFilter filter = new SearchFilter(filedName, operator, value);
			filters.put(key, filter);
		}

		return filters;
	}

	/**
	 * 查询字段.
	 */
	public String fieldName;

	/**
	 * 查询操作符.
	 */
	public Operator operator;

	/**
	 * 查询值
	 */
	public Object value;

	public SearchFilter(String fieldName, Operator operator, Object value) {
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
	}
}
