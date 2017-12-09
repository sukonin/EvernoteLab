package com.epam.services;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface CrudService<T, E> {

  List<T> getAll();

  T getById(E id);

  void saveOrUpdate(T domainObject);

  void delete(E id);
}
