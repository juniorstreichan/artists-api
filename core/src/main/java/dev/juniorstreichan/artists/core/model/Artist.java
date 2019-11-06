package dev.juniorstreichan.artists.core.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Artist implements BusinessEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "name is required")
  @Column(nullable = false)
  private String name;

  @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
  private List<Album> albums = new ArrayList<>();
}
