package com.ucs.projetotematico.dao;

import java.util.List;

import com.ucs.projetotematico.entity.ModelAbstract;

public interface ModelDaoInterface<M extends ModelAbstract> {
	public List<M> findAll();

	public M findById(Integer id);

	public M findLike(M m);

	public void remove(Integer id);

	public void saveOrUpdate(M model);
}
