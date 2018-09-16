package cn.jboa.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface BaseDao<T> {
    //���һ������
    void save(T entity) throws Exception;
    //����һ��������������
    void update(T entity) throws Exception;
    //ɾ��һ������
    void delete(T entity) throws Exception;
    //����id����һ������
    T findById(Serializable id) throws Exception;
    //��ѯ����
	List<T> findAll(DetachedCriteria dCriteria) throws Exception;
    List<T> findAll(DetachedCriteria dCriteria,int fistResult,int maxResult) throws Exception;
    //��ѯ����
    int findTotalRecords(DetachedCriteria dCriteria) throws Exception;
}
