package dev.juniorstreichan.artists.core.repository;

import dev.juniorstreichan.artists.core.model.Artist;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    @Query("select a, LENGTH(a.name) as fn_len from Artist a where lower(a.name) like lower(concat('%',:name,'%')) ")
    List<Artist> findByNameOrderByNameLength(
        @Param("name") String name,
        Sort sort
    );

    List<Artist> findByNameContainingIgnoreCase(
        String name,
        Sort sort
    );
}
