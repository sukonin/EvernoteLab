package com.epam.model;


import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
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
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Data
@Entity
@Table
@ToString(exclude = {"notebook", "tags"}) //Иначе StackOverFlow
public class Note implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String title;
  private String content;
  private Boolean isActive;
  private Date date;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Notebook.class)
  @JoinColumn(name = "notebook_id")
//  @OnDelete(action = OnDeleteAction.CASCADE)
  private Notebook notebook;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(name = "note_tag",
      joinColumns = @JoinColumn(name = "note_id"),
      inverseJoinColumns = @JoinColumn(name = "tag_id"))
  /*TODO заменить на Set*/
  private List<Tag> tags = new ArrayList<>();

}
