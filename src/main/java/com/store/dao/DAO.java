package com.store.dao;

import java.util.List;

public interface DAO<T> {
	public List<T> selectList() throws Exception;

	public int insert(T t) throws Exception;

	public int delete(int no) throws Exception;

	public T selectOne(int no) throws Exception;

	public int update(T t) throws Exception;

	public T exist(String email, String password) throws Exception;
}
