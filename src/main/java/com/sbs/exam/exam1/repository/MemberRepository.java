package com.sbs.exam.exam1.repository;

import com.sbs.exam.exam1.dto.Member;
import com.sbs.mysqlutil.MysqlUtil;
import com.sbs.mysqlutil.SecSql;

public class MemberRepository {

	public Member getMemeberByLoginId(String loginId) {
		SecSql sql = new SecSql();
		sql.append("SELECT M.*");
		sql.append("FROM `member` AS M");
		sql.append("WHERE M.loginId = ?", loginId);
		
		return MysqlUtil.selectRow(sql, Member.class);
	}

}
