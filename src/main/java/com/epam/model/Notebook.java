package com.epam.model;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table
@Data
@NoArgsConstructor
@ToString(exclude = {"user", "notes"})
public class Notebook implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column(nullable = false)
  private String title;

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "notebook", cascade = CascadeType.ALL,
      orphanRemoval = true)
  private List<Note> notes = new ArrayList<>();


}
