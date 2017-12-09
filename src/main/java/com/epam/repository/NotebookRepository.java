package com.epam.repository;

import com.epam.model.Notebook;
import com.epam.model.User;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NotebookRepository extends JpaRepository<Notebook, Long> {

  List<Notebook> findByUser(User user);
  Notebook findByTittle(String title);

}
