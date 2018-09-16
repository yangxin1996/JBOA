package cn.jboa.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface BaseDao<T> {
    //添加一个对象
    void save(T entity) throws Exception;
    //更新一个对象，所有属性
    void update(T entity) throws Exception;
    //删除一个对象
    void delete(T entity) throws Exception;
    //根据id查找一个对象
    T findById(Serializable id) throws Exception;
    //查询所有
	List<T> findAll(DetachedCriteria dCriteria) throws Exception;
    List<T> findAll(DetachedCriteria dCriteria,int fistResult,int maxResult) throws Exception;
    //查询总数
    int findTotalRecords(DetachedCriteria dCriteria) throws Exception;
}
