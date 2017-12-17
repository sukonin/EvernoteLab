package com.epam.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table
@ToString(exclude = "notes")
public class Tag implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @JsonIgnore
  private Long id;
  @Column(unique = true)
  private String tag;
  @ManyToMany(fetch = FetchType.EAGER, mappedBy = "tags")
  @JsonIgnore
  private List<Note> notes = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
  @JsonIgnore
  private User user;

}
