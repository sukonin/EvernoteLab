package com.epam.repository;

import com.epam.model.Note;
import com.epam.model.Tag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

  List<Note> getNoteByTags(List<Tag> tags);

  Note findNoteByTitle(String title);
}
