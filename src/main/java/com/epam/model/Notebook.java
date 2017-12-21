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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table
@Data
@NoArgsConstructor
@ToString(exclude = {"user", "notes"})
@EqualsAndHashCode(of = "id")
public class Notebook implements Serializable {

  @Id
  @JsonIgnore
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  @Column(nullable = false)
  @NotNull
  private String title;

  @ManyToOne
  @JoinColumn(name = "user_id")
  @NotNull
  @JsonIgnore
  private User user;

  @JsonIgnore
  @OneToMany(mappedBy = "notebook", cascade = CascadeType.ALL)
  private List<Note> notes = new ArrayList<>();


}
