package com.epam.services;

import java.util.List;

public interface CrudService<T, E> {

  List<T> getAll();

  T getById(E id);

  void saveOrUpdate(T domainObject);

  void delete(E id);

  void deleteAll();
}
