package com.epam.services.impl;

import com.epam.aspect.PerformanceMonitor;
import com.epam.model.Note;
import com.epam.model.Notebook;
import com.epam.model.Tag;
import com.epam.model.User;
import com.epam.repository.NoteRepository;
import com.epam.services.CrudService;
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
  private final TagService tagService;

  @Autowired
  public NoteService(NoteRepository noteRepository,
      TagService tagService) {
    this.noteRepository = noteRepository;
    this.tagService = tagService;
  }

  @Transactional
  @PerformanceMonitor
  public void addTagToNote(Long tag_id, Long note_id, User user) {
    Note note = noteRepository.getOne(note_id);
    Tag tag = tagService.getById(tag_id);
    List<Note> notes = tag.getNotes();
    notes.add(note);
    tag.setNotes(notes);
    tag.setUser(user);

    List<Tag> tags = note.getTags();
    tags.add(tag);
    note.setTags(tags);

    tagService.saveOrUpdate(tag);
    saveOrUpdate(note);
  }

  @Transactional
  @PerformanceMonitor
  public void removeTagFromNote(Long note_id, Long tag_id) {
    Tag tag = tagService.getById(tag_id);
    Note note = noteRepository.getOne(note_id);

    note.getTags().remove(tag);
    tag.getNotes().remove(note);
    noteRepository.saveAndFlush(note);
    tagService.saveOrUpdate(tag);
  }

  @PerformanceMonitor
  public List<Note> getAllNotesByTag(String tag, User user) {
    List<Note> all = getAll(user);
    List<Note> result = new ArrayList<>();
    for (Note note : all) {
      List<Tag> tags = note.getTags();
      for (Tag tag1 : tags) {
        if (tag1.getTag().equals(tag)) {
          result.add(note);
        }
      }
    }
    return result;
  }

  @PerformanceMonitor
  public List<Note> getAllNotesByNotebook(Notebook notebook) {
    return noteRepository.findNotesByNotebook(notebook);
  }

  @PerformanceMonitor
  public List<Note> getAll(User user) {
    return noteRepository.findNotesByNotebook_User_Id(user.getId());
  }

  @Override
  public List<Note> getAll() {
    return noteRepository.findAll();
  }

  @Override
  public Note getById(Long id) {
    return noteRepository.findNoteById(id);
  }

  @PerformanceMonitor
  @Override
  @Transactional
  public void saveOrUpdate(Note domainObject) {
    noteRepository.save(domainObject);
  }

  @PerformanceMonitor
  @Override
  @Transactional
  public void delete(Long id) {
    noteRepository.deleteById(id);
  }


}
