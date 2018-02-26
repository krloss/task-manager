package org.test.taskmanager;

public interface SaveEventRepository<T> {
    public T save(T entity);
}
