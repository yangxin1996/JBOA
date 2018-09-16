package cn.jboa.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.HibernateTemplate;

import cn.jboa.dao.BaseDao;

public class BaseDaoImpl <T> implements BaseDao<T>{

	@Resource
	protected HibernateTemplate hibernateTemplate;
	
	private Class<T> clazz;
	
	@SuppressWarnings("unchecked")
	public BaseDaoImpl() throws Exception {
		// 使用反射技术得到T的真实类型
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass(); // 获取当前new的对象的 泛型的父类 类型
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0]; // 获取第一个类型参数的真实类型
		/*System.out.println("clazz ---> " + clazz);*/
	}

	@Override
	public void save(T entity) throws Exception {
		// TODO Auto-generated method stub
		hibernateTemplate.saveOrUpdate(entity);
	}

	@Override
	public void update(T entity) throws Exception {
		// TODO Auto-generated method stub
		hibernateTemplate.merge(entity);
	}

	@Override
	public void delete(T entity) throws Exception {
		// TODO Auto-generated method stub
		hibernateTemplate.delete(entity);
	}

	@Override
	public T findById(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		return hibernateTemplate.get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(DetachedCriteria dCriteria, int fistResult, int maxResult) throws Exception {
		// TODO Auto-generated method stub
		return (List<T>) hibernateTemplate.findByCriteria(dCriteria,fistResult,maxResult);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int findTotalRecords(DetachedCriteria dCriteria) throws Exception {
		// TODO Auto-generated method stub
		List<Long> list = (List<Long>) hibernateTemplate.findByCriteria(dCriteria);
		return list.isEmpty()?0:list.get(0).intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(DetachedCriteria dCriteria) throws Exception {
		// TODO Auto-generated method stub
		return (List<T>) hibernateTemplate.findByCriteria(dCriteria);
	}
}
