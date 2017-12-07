package com.epam.services.impl;

import com.epam.model.User;
import com.epam.repository.UserRepository;
import com.epam.services.CrudService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements CrudService<User, Long> {

  private final UserRepository repository;

  @Autowired
  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<User> getAll() {
    return repository.findAll();
  }

  @Override
  public User getById(Long id) {
    return repository.findOne(id);
  }

  @Override
  public void saveOrUpdate(User domainObject) {
    repository.saveAndFlush(domainObject);
  }

  @Override
  public void delete(Long id) {
    repository.delete(id);
  }

  @Override
  public void deleteAll() {
    repository.deleteAll();
  }
}
