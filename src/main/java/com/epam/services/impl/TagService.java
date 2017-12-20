package com.epam.services.impl;

import com.epam.aspect.PerformanceMonitor;
import com.epam.model.Tag;
import com.epam.model.User;
import com.epam.repository.TagRepository;
import com.epam.services.CrudService;
import java.util.List;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log
@Service
@Transactional
public class TagService implements CrudService<Tag, Long> {

  private final TagRepository tagRepository;

  @Autowired
  public TagService(TagRepository tagRepository) {
    this.tagRepository = tagRepository;
  }

  @PerformanceMonitor
  public Tag getByTag(String tag, User user) {
    return tagRepository.getByTagAndUser(tag, user);
  }

  @PerformanceMonitor
  public List<Tag> getByTagsByUser(User user) {
    return tagRepository.findTagsByUser(user);
  }

  @PerformanceMonitor
  @Override
  public List<Tag> getAll() {
    return tagRepository.findAll();
  }

  @PerformanceMonitor
  @Override
  public Tag getById(Long id) {
    return tagRepository.findTagById(id);
  }

  @PerformanceMonitor
  @Override
  public void saveOrUpdate(Tag domainObject) {
    tagRepository.save(domainObject);
  }

  @PerformanceMonitor
  @Override
  public void delete(Long id) {
    tagRepository.deleteById(id);
  }

}
