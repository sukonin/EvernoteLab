package com.epam.services.impl;

import com.epam.model.Tag;
import com.epam.repository.TagRepository;
import com.epam.services.CrudService;
import java.util.List;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log
@Service
@Transactional(readOnly = true)
public class TagService implements CrudService<Tag, Long> {

  private final TagRepository tagRepository;

  @Autowired
  public TagService(TagRepository tagRepository) {
    this.tagRepository = tagRepository;
  }

  public Tag getByTag(String tag) {
    return tagRepository.getByTag(tag);
  }

  @Override
  public List<Tag> getAll() {
    return tagRepository.findAll();
  }

  @Override
  public Tag getById(Long id) {
    return tagRepository.getOne(id);
  }

  @Transactional
  @Override
  public void saveOrUpdate(Tag domainObject) {
    tagRepository.save(domainObject);
  }

  @Transactional
  @Override
  public void delete(Long id) {
    tagRepository.delete(id);
  }

  @Transactional
  public void deleteAll() {
    tagRepository.deleteAll();
  }
}
