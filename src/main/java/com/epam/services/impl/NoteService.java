package com.epam.services.impl;

import com.epam.model.Note;
import com.epam.model.Tag;
import com.epam.repository.NoteRepository;
import com.epam.services.CrudService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log
@Service
public class NoteService implements CrudService<Note, Long> {

  private final NoteRepository noteRepository;

  @Autowired
  public NoteService(NoteRepository noteRepository) {
    this.noteRepository = noteRepository;
  }

  public Note getByTitle(String title) {
    return noteRepository.findNoteByTitle(title);
  }

  public void addTagToNote(Tag tag) {
    //todo

  }

  public void removeTagFromNote(Tag tag) {
    //todo

  }

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

  public List<Note> getAllNotesByTag(List<Tag> tags) {
    return noteRepository.getNoteByTags(tags);
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
