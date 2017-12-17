package com.epam.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;


@Data
@Entity
@Table
@ToString(exclude = {"notebook", "tags"})//Иначе StackOverFlow
@JsonIgnoreProperties(value = "notebook")
public class Note implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @JsonIgnore
  private Long id;
  @Column(nullable = false)
  private String title;
  @Size(max = 500)
  private String content;
  @Column(nullable = false)
  private Boolean isActive;
  @Column(nullable = false)
  private Date date;

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Notebook.class)
  @JoinColumn(name = "notebook_id",nullable = false)
  @JsonIgnore
  private Notebook notebook;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "note_tag",
      joinColumns = @JoinColumn(name = "note_id"),
      inverseJoinColumns = @JoinColumn(name = "tag_id"))
  @JsonIgnore
  private List<Tag> tags = new ArrayList<>();

}
