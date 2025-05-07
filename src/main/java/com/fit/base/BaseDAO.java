package com.fit.base;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.internal.CriteriaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * @AUTO 基础 DAO 类，提供通用的 CRUD 操作
 * @Author AIM
 * @DATE 2025/5/6
 */
@Slf4j
@Repository
public abstract class BaseDAO<T, ID extends Serializable> extends HibernateDaoSupport {

    private final Class<T> clazz;

    @Autowired
    public void setHibernateTemplateForDAO(HibernateTemplate hibernateTemplate) {
        // 通过setter方法注入HibernateTemplate
        super.setHibernateTemplate(hibernateTemplate);
    }

    public BaseDAO() {
        this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Assert.notNull(clazz, "Type argument cannot be null");
    }

    public void setSessionFactoryForDAO(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    protected Session getCurrentSession() {
        return getSessionFactory().getCurrentSession();
    }

    public ID save(T entity) {
        log.debug("Saving {} instance", clazz.getSimpleName());
        try {
            return (ID) getHibernateTemplate().save(entity);
        } catch (RuntimeException re) {
            log.error("Save failed", re);
            throw new DataAccessException("Save " + clazz.getSimpleName() + " failed", re);
        }
    }

    public void update(T entity) {
        log.debug("Updating {} instance", clazz.getSimpleName());
        try {
            getHibernateTemplate().update(entity);
        } catch (RuntimeException re) {
            log.error("Update failed", re);
            throw new DataAccessException("Update " + clazz.getSimpleName() + " failed", re);
        }
    }

    public void saveOrUpdate(T entity) {
        log.debug("Attaching dirty {} instance", clazz.getSimpleName());
        try {
            getHibernateTemplate().saveOrUpdate(entity);
        } catch (RuntimeException re) {
            log.error("Attach failed", re);
            throw new DataAccessException("Attach " + clazz.getSimpleName() + " failed", re);
        }
    }

    public void delete(T entity) {
        log.debug("Deleting {} instance", clazz.getSimpleName());
        try {
            getHibernateTemplate().delete(entity);
        } catch (RuntimeException re) {
            log.error("Delete failed", re);
            throw new DataAccessException("Delete " + clazz.getSimpleName() + " failed", re);
        }
    }

    public void deleteById(ID id) {
        log.debug("Deleting {} instance with id: {}", clazz.getSimpleName(), id);
        try {
            T entity = findById(id);
            if (entity != null) {
                delete(entity);
            }
        } catch (RuntimeException re) {
            log.error("Delete by id failed", re);
            throw new DataAccessException("Delete " + clazz.getSimpleName() + " by id failed", re);
        }
    }

    public T findById(ID id) {
        log.debug("Getting {} instance with id: {}", clazz.getSimpleName(), id);
        try {
            return getHibernateTemplate().get(clazz, id);
        } catch (RuntimeException re) {
            log.error("Get failed", re);
            throw new DataAccessException("Get " + clazz.getSimpleName() + " by id failed", re);
        }
    }

    public List<T> findAll() {
        log.debug("Finding all {} instances", clazz.getSimpleName());
        try {
            return getHibernateTemplate().loadAll(clazz);
        } catch (RuntimeException re) {
            log.error("Find all failed", re);
            throw new DataAccessException("Find all " + clazz.getSimpleName() + " failed", re);
        }
    }

    public List<T> findAllForMap(Map<String, Object> param) {
        Criteria c = getCurrentSession().createCriteria(clazz);
        for (String key : param.keySet()) {
            if (param.get(key) != null) {
                if (key.contains("name")) {
                    c.add(Restrictions.eq(key, param.get(key)));
                } else {
                    c.add(Expression.like(key, "%" + param.get(key) + "%"));
                }
            }
        }
        return c.list();
    }

    public PageResult pageAll() {
        return findAll(null);
    }

    public PageResult findAll(Map<String, Object> param) {
        log.debug("Finding page {} instances", clazz.getSimpleName());
        PageResult pgr = new PageResult();
        String start = (String) param.get("start");
        String limit = (String) param.get("limit");
        try {
            Criteria c = getCurrentSession().createCriteria(clazz);
            for (String key : param.keySet()) {
                if (param.get(key) != null) {
                    if (key.contains("name")) {
                        c.add(Restrictions.eq(key, param.get(key)));
                    } else {
                        c.add(Expression.like(key, "%" + param.get(key) + "%"));
                    }
                }
            }
            // 获取总记录数
            Projection entityProjection = ((CriteriaImpl) c).getProjection();
            c.setProjection(Projections.rowCount());
            int rowCount = ((Number) c.uniqueResult()).intValue();
            pgr.setRowCount(rowCount);
            // 恢复原有Projection
            c.setProjection(entityProjection);
            // 设置分页
            if (StringUtils.hasText(start)) {
                c.setFirstResult(Integer.parseInt(start));
            }
            if (StringUtils.hasText(limit)) {
                c.setMaxResults(Integer.parseInt(limit));
            }
            // 获取分页数据
            pgr.setData(c.list());
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
        return pgr;
    }

    public List<T> findByProperty(String propertyName, Object value) {
        return findByProperty(propertyName, value, MatchMode.EXACT);
    }

    public List<T> findByProperty(String propertyName, Object value, MatchMode matchMode) {
        log.debug("Finding {} instance with property: {}, value: {}", clazz.getSimpleName(), propertyName, value);
        try {
            Criteria criteria = getCurrentSession().createCriteria(clazz);
            if (matchMode == MatchMode.EXACT) {
                criteria.add(Restrictions.eq(propertyName, value));
            } else {
                criteria.add(Restrictions.like(propertyName, value.toString(), matchMode));
            }
            return criteria.list();
        } catch (RuntimeException re) {
            log.error("Find by property failed", re);
            throw new DataAccessException("Find by property failed", re);
        }
    }

    public List<T> findByExample(T exampleInstance) {
        log.debug("Finding {} instance by example", clazz.getSimpleName());
        try {
            List<T> results = getHibernateTemplate().findByExample(exampleInstance);
            log.debug("Find by example successful, result size: {}", results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("Find by example failed", re);
            throw new DataAccessException("Find by example failed", re);
        }
    }

    public T merge(T detachedInstance) {
        log.debug("Merging {} instance", clazz.getSimpleName());
        try {
            return getHibernateTemplate().merge(detachedInstance);
        } catch (RuntimeException re) {
            log.error("Merge failed", re);
            throw new DataAccessException("Merge failed", re);
        }
    }

    protected PageResult findPageResult(Map<String, String> paramMap, Map<String, String> fieldMap) {
        return findPageResult(paramMap, fieldMap, null);
    }

    protected PageResult findPageResult(Map<String, String> paramMap, Map<String, String> fieldMap, Criterion... extraCriteria) {
        PageResult pgr = new PageResult();
        int start = StringUtils.hasText(paramMap.get("start")) ? Integer.parseInt(paramMap.get("start")) : 0;
        int limit = StringUtils.hasText(paramMap.get("limit")) ? Integer.parseInt(paramMap.get("limit")) : 10;

        try {
            Criteria criteria = getCurrentSession().createCriteria(clazz);

            // 添加查询条件
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                String fieldName = fieldMap.get(entry.getKey());
                if (fieldName != null && StringUtils.hasText(entry.getValue())) {
                    criteria.add(Restrictions.like(fieldName, "%" + entry.getValue() + "%"));
                }
            }

            // 添加额外条件
            if (extraCriteria != null) {
                for (Criterion criterion : extraCriteria) {
                    criteria.add(criterion);
                }
            }

            // 获取总记录数
            criteria.setProjection(Projections.rowCount());
            int rowCount = ((Number) criteria.uniqueResult()).intValue();
            pgr.setRowCount(rowCount);

            // 重置投影以获取实际数据
            criteria.setProjection(null);
            criteria.setResultTransformer(Criteria.ROOT_ENTITY);

            // 设置分页
            criteria.setFirstResult(start);
            criteria.setMaxResults(limit);

            pgr.setData(criteria.list());
        } catch (RuntimeException re) {
            log.error("Find page result failed", re);
            throw new DataAccessException("Find page result failed", re);
        }
        return pgr;
    }

    // 自定义异常
    public static class DataAccessException extends RuntimeException {
        public DataAccessException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}