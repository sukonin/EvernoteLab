package com.epam.services.impl;

import com.epam.model.Notebook;
import com.epam.model.User;
import com.epam.repository.NotebookRepository;
import com.epam.services.CrudService;
import java.util.List;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log
@Transactional(readOnly = true)
public class NotebookService implements CrudService<Notebook, Long> {

  private final NotebookRepository notebookRepository;

  @Autowired
  public NotebookService(NotebookRepository notebookRepository) {
    this.notebookRepository = notebookRepository;
  }

  public List<Notebook> getAllNotebookByUser(User user) {
    return notebookRepository.findByUser(user);
  }

  public Notebook getByTitle(String title, String user) {
    return notebookRepository.findByTitleAndUserEmail(title, user);
  }

  @Override
  public List<Notebook> getAll() {
    return notebookRepository.findAll();
  }

  @Override
  public Notebook getById(Long id) {
    return notebookRepository.findOne(id);
  }

  @Override
  @Transactional
  public void saveOrUpdate(Notebook notebook) {
    notebookRepository.saveAndFlush(notebook);
  }

  @Override
  @Transactional
  public void delete(Long id) {
    notebookRepository.delete(id);
  }

  @Transactional
  public void deleteAll() {
    notebookRepository.deleteAll();
  }
}
