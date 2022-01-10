package com.example.computershop.repository.custom.impl;


import com.example.computershop.entities.Student;
import com.example.computershop.repository.custom.StudentRepositoryCustom;
import com.example.computershop.ultil.PageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Repository
public class StudentRepositoryCustomImpl implements StudentRepositoryCustom {
    private static final String GET_COUNT = "get_count";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Student> getPagination(String fileName, Date dateFrom, Date dateTo, int size, int page, String orderBy, boolean desc) {
        String sql = " select s.* from students s where s.deletedAt is null ";
        Query query = buildFilterQuery(sql, fileName, dateFrom, dateTo, size, page, orderBy, desc, "");
        return query.getResultList();
    }

    @Override
    public long getCount(String fullName, Date dateFrom, Date dateTo, int size, int page, String orderBy, boolean desc) {
        String sql = "  select count(*) from students s where s.deletedAt is null " ;
        Query query = buildFilterQuery(sql, fullName, dateFrom, dateTo, page, size, orderBy, desc, GET_COUNT);
        long count = ((BigInteger) query.getSingleResult()).intValue();
        return count;
    }
    public Query buildFilterQuery(String sql, String fullName, Date dateFrom, Date dateTo, int size, int page, String orderBy, boolean desc, String type) {
        if (dateFrom != null) {
            sql += " and s.createdAt > :dateFrom";
        }
        if (dateTo != null) {
            sql += " and a.createdAt <= :dateTo";
        }
        if (StringUtils.isNotBlank(fullName)) {
            sql += " and s.fullName like concat('%',:fullName,'%')";
        }
        Query query;
        if (!type.equals(GET_COUNT)) {
            sql += PageUtil.buildPaginationSql(page,size,"s."+orderBy,desc);
            query = entityManager.createNativeQuery(sql, Student.class);
        } else query = entityManager.createNativeQuery(sql);

        if (dateFrom != null) {
            query.setParameter("dateFrom", dateFrom);
        }
        if (dateTo != null) {
            query.setParameter("dateTo", dateTo);
        }
        if (StringUtils.isNotBlank(fullName)) {
            query.setParameter("fullName", fullName);
        }
        return query;
    }
}
