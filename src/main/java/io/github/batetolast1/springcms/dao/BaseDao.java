package io.github.batetolast1.springcms.dao;

import java.util.List;

public interface BaseDao<T, ID> {

    void save(T t);

    T findById(ID var);

    void update(T t);

    void delete(ID var);

    List<T> findAll();
}
