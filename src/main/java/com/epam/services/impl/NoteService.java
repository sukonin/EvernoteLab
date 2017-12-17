package com.epam.services.impl;

import com.epam.aspect.PerformanceMonitor;
import com.epam.model.Note;
import com.epam.model.Notebook;
import com.epam.model.Tag;
import com.epam.model.User;
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
  private final TagService tagService;

  @Autowired
  public NoteService(NoteRepository noteRepository, TagRepository tagRepository,
      TagService tagService) {
    this.noteRepository = noteRepository;
    this.tagService = tagService;
  }

  @PerformanceMonitor
  public List<Note> getByTitle(String title, User user) {

    List<Note> result = new ArrayList<>();
    List<Notebook> notebookList = user.getNotebookList();
    for (Notebook notebook : notebookList) {
      List<Note> notes = notebook.getNotes();
      for (Note note : notes) {
        if (note.getTitle().equals(title)) {
          result.add(note);
        }
      }
    }
    return result;
  }


  @PerformanceMonitor
  public List<Note> getByStatus(Boolean isActive, User user) {
    List<Note> result = new ArrayList<>();
    List<Notebook> notebookList = user.getNotebookList();
    for (Notebook notebook : notebookList) {
      List<Note> notes = notebook.getNotes();
      for (Note note : notes) {
        if (note.getIsActive().equals(isActive)) {
          result.add(note);
        }
      }
    }
    return result;
  }

  @PerformanceMonitor
  public List<Note> getByContent(String content, User user) {
    List<Note> result = new ArrayList<>();
    List<Notebook> notebookList = user.getNotebookList();
    for (Notebook notebook : notebookList) {
      List<Note> notes = notebook.getNotes();
      for (Note note : notes) {
        if (note.getContent().contains(content)) {
          result.add(note);
        }
      }
    }
    return result;
  }

  @PerformanceMonitor
  public List<Note> getNotesByDate(Date date, User user) {

    List<Note> result = new ArrayList<>();
    List<Notebook> notebookList = user.getNotebookList();
    for (Notebook notebook : notebookList) {
      List<Note> notes = notebook.getNotes();
      for (Note note : notes) {
        if (note.getDate().equals(date)) {
          result.add(note);
        }
      }
    }
    return result;
  }

  @Transactional
  public void addTagToNote(Tag tag, String id, User user) {
    Note note = noteRepository.getOne(Long.valueOf(id));

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

  @PerformanceMonitor
  @Transactional
  public void removeTagFromNote(Long note_id, Long tag_id) {
    Tag tag = tagService.getById(tag_id);
    Note note = noteRepository.getOne(note_id);

    note.getTags().remove(tag);
    tag.getNotes().remove(note);
    noteRepository.saveAndFlush(note);
    tagService.saveOrUpdate(tag);
  }

  /*TODO FIX IT*/
  @PerformanceMonitor
  public List<Note> getAllNotesByTag(String tag, User user) {
    List<Note> all = getAll(user);
    List<Note> result = new ArrayList<>();

    for (Note note : all) {
      System.err.println(note.getTags());
      List<Tag> tags = note.getTags();
      for (Tag tag1 : tags) {

        List<Note> notes = tag1.getNotes();
        System.err.println(notes);
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
    List<Notebook> notebookList = user.getNotebookList();
    List<Note> result = new ArrayList<>();
    for (Notebook notebook : notebookList) {
      List<Note> notes = notebook.getNotes();
      result.addAll(notes);
    }
    return result;
  }

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
