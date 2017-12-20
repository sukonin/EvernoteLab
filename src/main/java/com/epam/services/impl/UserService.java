package com.epam.services.impl;

import com.epam.aspect.PerformanceMonitor;
import com.epam.exception.UserException;
import com.epam.model.Notebook;
import com.epam.model.User;
import com.epam.repository.UserRepository;
import com.epam.services.CrudService;
import java.util.List;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log
@Transactional(readOnly = true)
public class UserService implements CrudService<User, Long> {

  private final UserRepository userRepository;
  /*private final NotebookService notebookService;*/

  @Autowired
  public UserService(UserRepository userRepository,
      NotebookService notebookService) {
    this.userRepository = userRepository;
    /*this.notebookService = notebookService;*/
  }

  @PerformanceMonitor
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
    return userRepository.findUserById(id);
  }

  @PerformanceMonitor
  @Override
  @Transactional
  public void saveOrUpdate(User user) {

    if (getByEmail(user.getEmail()) != null) {
      throw new UserException(
          "User found with username " + user.getUsername() + ". Cannot create!");
    }

    String password = user.getPassword();
    password = new BCryptPasswordEncoder().encode(password);
    user.setPassword(password);
    userRepository.save(user);

  /*  Notebook defaultNotebook = new Notebook();
    defaultNotebook.setTitle("Default Notebook");
    defaultNotebook.setUser(user);
    notebookService.saveOrUpdate(defaultNotebook);*/
  }

  @PerformanceMonitor
  @Override
  @Transactional
  public void delete(Long id) {
    userRepository.deleteById(id);
  }

  public void update(User user) {
    userRepository.save(user);
  }
}
