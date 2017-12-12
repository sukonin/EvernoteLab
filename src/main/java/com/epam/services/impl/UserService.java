package com.epam.services.impl;

import com.epam.model.User;
import com.epam.repository.UserRepository;
import com.epam.services.CrudService;
import java.util.List;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@Log
public class UserService implements CrudService<User, Long> {

  private final UserRepository repository;

  @Autowired
  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  public User getByEmail(String email) {
    return repository.findUserByEmail(email);
  }

  @Override
  public List<User> getAll() {
    return repository.findAll();
  }

  @Override
  public User getById(Long id) {
    return repository.findOne(id);
  }

  @Transactional
  @Override
  public void saveOrUpdate(User domainObject) {
    repository.saveAndFlush(domainObject);
  }

  @Transactional
  @Override
  public void delete(Long id) {
    repository.delete(id);
  }

}
