package com.epam.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Data
@Entity
@Table
@ToString(exclude = "notes")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Tag implements Serializable {

  @Id
  @JsonIgnore
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  private String tag;

  @JsonIgnore
  @ManyToMany(mappedBy = "tags")
  private List<Note> notes = new ArrayList<>();

  @JsonIgnore
  @ManyToOne
  @NotNull
  private User user;

}
