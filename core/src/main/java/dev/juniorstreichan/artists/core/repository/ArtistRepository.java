package dev.juniorstreichan.artists.core.repository;

import dev.juniorstreichan.artists.core.model.Artist;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    @Query("select a.name, LENGTH(a.name) as fn_len from Artist a")
    List<Artist> findOrderByNameLength(Sort sort);
}
