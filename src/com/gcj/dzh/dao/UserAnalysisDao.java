package com.gcj.dzh.dao;

import java.util.List;

import com.gcj.dzh.bean.UserAnalysis;

public interface UserAnalysisDao {

	public void insert_user_analysis(UserAnalysis userAnalysis);

	public List<UserAnalysis> select_user_analysis();
}
