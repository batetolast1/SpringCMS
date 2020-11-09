package io.github.batetolast1.springcms.dao;

import java.util.Set;

public interface BaseDao<T, ID> {

    void save(T t);

    T findById(ID var);

    void update(T t);

    void delete(ID var);

    Set<T> findAll();
}
