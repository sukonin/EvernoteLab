package com.epam.services.impl;

import com.epam.aspect.PerformanceMonitor;
import com.epam.model.Notebook;
import com.epam.model.User;
import com.epam.repository.NotebookRepository;
import com.epam.services.CrudService;
import java.util.List;
import javax.jws.soap.SOAPBinding.Use;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@Log
public class NotebookService implements CrudService<Notebook, Long> {

  private final NotebookRepository notebookRepository;

  @Autowired
  public NotebookService(NotebookRepository notebookRepository) {
    this.notebookRepository = notebookRepository;
  }

  @PerformanceMonitor
  public List<Notebook> getAllNotebookByUser(User user) {

    return notebookRepository.findNotebooksByUser_Id(user.getId());
  }

  @PerformanceMonitor
  @Override
  public List<Notebook> getAll() {
    return notebookRepository.findAll();
  }

  @PerformanceMonitor
  @Override
  public Notebook getById(Long id) {
    return notebookRepository.findNotebookById(id);
  }


  @Transactional
  @PerformanceMonitor
  public void saveOrUpdate(Notebook notebook) {
    notebookRepository.save(notebook);
  }

  @PerformanceMonitor
  @Override
  @Transactional
  public void delete(Long id) {
    notebookRepository.deleteById(id);
  }

}
