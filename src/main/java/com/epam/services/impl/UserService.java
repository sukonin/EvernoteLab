package com.epam.services.impl;

import com.epam.aspect.PerformanceMonitor;
import com.epam.model.Notebook;
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

  private final UserRepository userRepository;
  private final NotebookService service;

  @Autowired
  public UserService(UserRepository userRepository,
      NotebookService service) {
    this.userRepository = userRepository;
    this.service = service;
  }

  public User getByEmail(String email) {
    return userRepository.findUserByEmail(email);
  }


  @PerformanceMonitor
  @Override
  public List<User> getAll() {
    return userRepository.findAll();
  }

  @PerformanceMonitor
  @Override
  public User getById(Long id) {
    return userRepository.findOne(id);
  }

  @PerformanceMonitor
  @Transactional
  @Override
  public void saveOrUpdate(User domainObject) {
    userRepository.save(domainObject);

    Notebook defaultNotebook = new Notebook();
    defaultNotebook.setTitle("Default Notebook");

    defaultNotebook.setUser(domainObject);

    service.saveOrUpdate(defaultNotebook);
  }

  @PerformanceMonitor
  @Transactional
  @Override
  public void delete(Long id) {
    userRepository.delete(id);
  }

}
