package com.example.padraosingleton.dao;

import java.util.List;

public interface DAO<T> {
    void save(T t);
    void update(T t, String[] params);
    void delete(T t);
    List<T> getAll();
    T get(int id);
}
