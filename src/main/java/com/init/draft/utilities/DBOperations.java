package com.expense.tracker.utilities;

import com.expense.tracker.enums.PersistOption;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DBOperations {

    @PersistenceContext
    private EntityManager entityManager;

    // ─────────────────────────────────────────────
    // SAVE / UPDATE ENTITY
    // ─────────────────────────────────────────────

    @Transactional
    public <T> void saveEntity(T entity, PersistOption option) {
        try {
            if (option == PersistOption.ADD) {
                entityManager.persist(entity);
            } else {
                entityManager.merge(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to save entity: " + entity, e);
        }
    }

    // ─────────────────────────────────────────────
    // FETCH LIST — mapped to a DTO class
    // Use this for reporting queries
    // ─────────────────────────────────────────────

    @SuppressWarnings("unchecked")
    public <T> List<T> fetchList(String sql, Class<T> dtoClass, Map<String, Object> params) {
        NativeQuery<T> query = entityManager
                .createNativeQuery(sql)
                .unwrap(NativeQuery.class);

        params.forEach(query::setParameter);

        return query
                .setResultTransformer(Transformers.aliasToBean(dtoClass))
                .getResultList();
    }

    // ─────────────────────────────────────────────
    // FETCH LIST — raw Object[] (when no DTO needed)
    // ─────────────────────────────────────────────

    @SuppressWarnings("unchecked")
    public List<Object[]> fetchRawList(String sql, Map<String, Object> params) {
        NativeQuery<?> query = entityManager
                .createNativeQuery(sql)
                .unwrap(NativeQuery.class);

        params.forEach(query::setParameter);

        return (List<Object[]>) query.getResultList();
    }

    // ─────────────────────────────────────────────
    // FETCH SINGLE RESULT — mapped to a DTO class
    // ─────────────────────────────────────────────

    @SuppressWarnings("unchecked")
    public <T> T fetchSingle(String sql, Class<T> dtoClass, Map<String, Object> params) {
        NativeQuery<T> query = entityManager
                .createNativeQuery(sql)
                .unwrap(NativeQuery.class);

        params.forEach(query::setParameter);

        return query
                .setResultTransformer(Transformers.aliasToBean(dtoClass))
                .uniqueResult();
    }

    // ─────────────────────────────────────────────
    // EXECUTE UPDATE / DELETE
    // ─────────────────────────────────────────────

    @Transactional
    public int executeUpdate(String sql, Map<String, Object> params) {
        try {
            NativeQuery<?> query = entityManager
                    .createNativeQuery(sql)
                    .unwrap(NativeQuery.class);

            params.forEach(query::setParameter);

            return query.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Failed to execute update: " + e.getMessage(), e);
        }
    }
}