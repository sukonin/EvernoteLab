package com.epam.services.impl;

import com.epam.model.Tag;
import com.epam.repository.TagRepository;
import com.epam.services.CrudService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService implements CrudService<Tag, Long> {

  private final TagRepository tagRepository;

  @Autowired
  public TagService(TagRepository tagRepository) {
    this.tagRepository = tagRepository;
  }

  @Override
  public List<Tag> getAll() {
    return tagRepository.findAll();
  }

  @Override
  public Tag getById(Long id) {
    return tagRepository.getOne(id);
  }

  @Override
  public void saveOrUpdate(Tag domainObject) {
    tagRepository.save(domainObject);
  }

  @Override
  public void delete(Long id) {
    tagRepository.delete(id);
  }

  @Override
  public void deleteAll() {
    tagRepository.deleteAll();
  }
}