package com.xshhope.log.service;


import com.xshhope.common.support.ActionResult;
import com.xshhope.common.utils.Pager;
import com.xshhope.model.log.Log;

import java.util.Map;

public interface LogService {

	/**
	 * 保存日志
	 *
	 * @param log
	 */
	void save(Log log);

	Pager<Log> findLogs(Map<String, Object> params);

}
