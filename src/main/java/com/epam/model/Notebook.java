package com.epam.model;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user", "notes"})
public class Notebook implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String tittle;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @OnDelete(action = OnDeleteAction.CASCADE)
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "notebook", cascade = CascadeType.ALL,
      orphanRemoval = true)
  private List<Note> notes = new ArrayList<>();


}
