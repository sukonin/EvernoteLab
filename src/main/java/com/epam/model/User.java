package com.epam.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Data
@Entity
@Table
@NoArgsConstructor
@ToString(exclude = {"notebookList", "tags"})
public class User implements Serializable {

  @Id
  @JsonIgnore
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  @Column(unique = true)
  private String email;
  private String password;
  @Column(nullable = false)
  private String username;

  @OneToMany( mappedBy = "user", cascade = CascadeType.ALL,
      orphanRemoval = true)
  @JsonIgnore
  @LazyCollection(LazyCollectionOption.FALSE)
  private List<Notebook> notebookList = new ArrayList<>();

  @OneToMany( mappedBy = "user", cascade = CascadeType.ALL)
  @JsonIgnore
  @LazyCollection(LazyCollectionOption.FALSE)
  private List<Tag> tags = new ArrayList<>();


}
