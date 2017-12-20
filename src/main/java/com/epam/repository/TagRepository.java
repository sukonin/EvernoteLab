package com.epam.repository;

import com.epam.model.Tag;
import com.epam.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

  Tag getByTagAndUser(String tag, User user);

  List<Tag> findTagsByUser(User user);

  Tag findTagById(Long id);


}
