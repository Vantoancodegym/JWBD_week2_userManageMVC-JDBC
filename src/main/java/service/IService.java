package service;

import model.User;

import java.util.List;

public interface IService <T>{
    List<T> findAll();
    boolean create(T t);
    boolean update(int id,T t);
    boolean delete(int id);
    User findById(int id);
}
