package com.epam.services.impl;

import com.epam.model.Notebook;
import com.epam.model.User;
import com.epam.repository.NotebookRepository;
import com.epam.services.CrudService;
import java.util.List;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log
public class NotebookService implements CrudService<Notebook, Long> {

  private final NotebookRepository notebookRepository;

  @Autowired
  public NotebookService(NotebookRepository notebookRepository) {
    this.notebookRepository = notebookRepository;
  }

  public List<Notebook> getAllNotebookByUser(User user) {
    return notebookRepository.findByUser(user);
  }

  public Notebook getByTitle(String title) {
    return notebookRepository.findByTittle(title);
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
  public void saveOrUpdate(Notebook notebook) {
    notebookRepository.saveAndFlush(notebook);
  }

  @Override
  public void delete(Long id) {
    notebookRepository.delete(id);
  }

  public void deleteAll() {
    notebookRepository.deleteAll();
  }
}
