package dev.juniorstreichan.artists.api.service;

import dev.juniorstreichan.artists.core.model.Artist;
import dev.juniorstreichan.artists.core.service.CRUDService;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IArtistService extends CRUDService<Artist> {
    List<Artist> listByName(String name, Sort.Direction direction);

    List<Artist> listByNameOrderByNameSize(String name, Sort.Direction direction);
}
