package com.epam.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@Entity
@Table
@ToString(exclude = {"notebook", "tags"})
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Note implements Serializable {

  @Id
  @JsonIgnore
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  @Column(nullable = false)
  private String title;
  @Size(max = 500)
  private String content;
  @Column(nullable = false)
  private Boolean isActive;
  @Column(nullable = false)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  private Date date;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "notebook_id")
  @NotNull
  private Notebook notebook;

  @JsonIgnore
  @ManyToMany
  @JoinTable(name = "note_tag",
      joinColumns = @JoinColumn(name = "note_id"),
      inverseJoinColumns = @JoinColumn(name = "tag_id"))
  private List<Tag> tags = new ArrayList<>();

}
