package com.epam.services.impl;

import com.epam.aspect.PerformanceMonitor;
import com.epam.model.Note;
import com.epam.model.Notebook;
import com.epam.model.Tag;
import com.epam.repository.NoteRepository;
import com.epam.repository.TagRepository;
import com.epam.services.CrudService;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log
@Service
@Transactional(readOnly = true)
public class NoteService implements CrudService<Note, Long> {

  private final NoteRepository noteRepository;
  private final TagRepository tagRepository;

  @Autowired
  public NoteService(NoteRepository noteRepository, TagRepository tagRepository) {
    this.noteRepository = noteRepository;
    this.tagRepository = tagRepository;
  }

  @PerformanceMonitor
  public Note getByTitle(String title) {
    return noteRepository.findNoteByTitle(title);
  }

  @PerformanceMonitor
  public List<Note> getByStatus(Boolean isActive) {
    return noteRepository.findNotesByIsActive(isActive);
  }

  @PerformanceMonitor
  public Note getByContent(String content) {
    return noteRepository.findNoteByContent(content);
  }

  @PerformanceMonitor
  public List<Note> getNotesByDate(Date date) {
    return noteRepository.findNotesByDate(date);
  }

  @PerformanceMonitor
  @Transactional
  public void addTagToNote(Tag tag, Note note) {
    note.getTags().add(tag);
    noteRepository.save(note);
  }

  @PerformanceMonitor
  @Transactional
  public void removeTagFromNote(Tag tag, Note note) {
    note.getTags().remove(tag);
    tag.getNotes().remove(note);
    noteRepository.save(note);
    tagRepository.save(tag);
  }

  @PerformanceMonitor
  public List<Note> getAllNotesByTag(Tag tag) {
    List<Note> notes = noteRepository.findAll();
    List<Note> result = new ArrayList<>();
    for (Note note : notes) {
      if (!note.getTags().isEmpty()) {
        List<Tag> tags = note.getTags();
        for (Tag tag1 : tags) {
          if (tag1.getTag().equals(tag.getTag())) {
            result.add(note);
          }
        }
      }
    }
    return result;
  }

  @PerformanceMonitor
  public List<Note> getAllNotesByTag(List<Tag> tags) {
    return noteRepository.findNotesByTagsIn(tags);
  }

  @PerformanceMonitor
  public List<Note> getAllNotesByNotebook(Notebook notebook) {
    return noteRepository.findNotesByNotebook(notebook);
  }

  @PerformanceMonitor
  @Override
  public List<Note> getAll() {
    return noteRepository.findAll();
  }

  @PerformanceMonitor
  @Override
  public Note getById(Long id) {
    return noteRepository.findOne(id);
  }

  @PerformanceMonitor
  @Transactional
  @Override
  public void saveOrUpdate(Note domainObject) {
    noteRepository.save(domainObject);
  }

  @PerformanceMonitor
  @Transactional
  @Override
  public void delete(Long id) {
    noteRepository.delete(id);
  }

  @PerformanceMonitor
  @Transactional
  public void deleteAll() {
    noteRepository.deleteAll();
  }
}
