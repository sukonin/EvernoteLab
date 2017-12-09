package com.epam.repository;

import com.epam.model.Note;
import com.epam.model.Tag;
import java.sql.Date;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

  Set<Note> findNotesByTagsIn(List<Tag> tags);

  Note findNoteByTitle(String title);

  List<Note> findNotesByIsActive(Boolean isActive);

  Note findNoteByContent(String content);

  List<Note> findNotesByDate(Date date);

}
