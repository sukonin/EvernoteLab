package com.epam.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table
@ToString(exclude = "notes")
public class Tag implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String tag;
  @ManyToMany(fetch = FetchType.EAGER, mappedBy = "tags", cascade = CascadeType.ALL)
  private List<Note> notes = new ArrayList<>();

}
