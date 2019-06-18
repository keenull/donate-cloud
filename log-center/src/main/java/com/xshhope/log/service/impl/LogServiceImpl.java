package com.xshhope.log.service.impl;

import com.xshhope.common.support.ActionResult;
import com.xshhope.common.support.ActionTemplate;
import com.xshhope.common.utils.Pager;
import com.xshhope.log.dao.LogDao;
import com.xshhope.log.service.LogService;
import com.xshhope.model.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 日志存储到mysql实现
 *
 * @author xshhope
 */
//@Primary
@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private LogDao logDao;

	/**
	 * 将日志保存到数据库<br>
	 * 注解@Async是开启异步执行
	 *
	 * @param log
	 */
	@Async
	@Override
	public void save(Log log) {
		if (log.getCreateTime() == null) {
			log.setCreateTime(new Date());
		}
		if (log.getFlag() == null) {
			log.setFlag(Boolean.TRUE);
		}

		logDao.save(log);

	}

	@Override
	public Pager<Log> findLogs(Map<String, Object> params) {
		int total = logDao.count(params);
		List<Log> list = Collections.emptyList();
		if (total > 0) {

			list = logDao.findData(params);
		}

		Pager<Log> pager = new Pager<>();
		pager.setData(list);
		pager.setTotalElements(total);
		return pager;
	}
}
