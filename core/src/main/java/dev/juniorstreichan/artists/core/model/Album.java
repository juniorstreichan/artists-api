package dev.juniorstreichan.artists.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Album implements BusinessEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "description is required")
  @Column(nullable = false)
  private String description;

  @ManyToOne
  @JoinColumn(name = "artist_id")
  @JsonIgnore
  @ToString.Exclude
  private Artist artist;
}
