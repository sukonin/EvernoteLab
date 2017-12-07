package com.epam.services.impl;

import com.epam.model.Note;
import com.epam.repository.NoteRepository;
import com.epam.services.CrudService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteService implements CrudService<Note, Long> {

  private final NoteRepository noteRepository;

  @Autowired
  public NoteService(NoteRepository noteRepository) {
    this.noteRepository = noteRepository;
  }


  @Override
  public List<Note> getAll() {
    return noteRepository.findAll();
  }

  @Override
  public Note getById(Long id) {
    return noteRepository.findOne(id);
  }

  @Override
  public void saveOrUpdate(Note domainObject) {
    noteRepository.save(domainObject);
  }

  @Override
  public void delete(Long id) {
    noteRepository.delete(id);
  }

  @Override
  public void deleteAll() {
    noteRepository.deleteAll();
  }
}
