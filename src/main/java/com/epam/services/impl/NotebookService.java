package com.epam.services.impl;

import com.epam.aspect.PerformanceMonitor;
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

  @PerformanceMonitor
  public List<Notebook> getAllNotebookByUser(User user) {
    return notebookRepository.findNotebookByUser(user);
  }

  @PerformanceMonitor
  public Notebook getByTitle(String title, String user) {
    return notebookRepository.findByTitleAndUserEmail(title, user);
  }

  @PerformanceMonitor
  @Override
  public List<Notebook> getAll() {
    return notebookRepository.findAll();
  }

  @PerformanceMonitor
  @Override
  public Notebook getById(Long id) {
    return notebookRepository.findOne(id);
  }

  @PerformanceMonitor
  @Override
  @Transactional
  public void saveOrUpdate(Notebook notebook) {
    notebookRepository.saveAndFlush(notebook);
  }

  @PerformanceMonitor
  @Override
  @Transactional
  public void delete(Long id) {
    notebookRepository.delete(id);
  }

}
