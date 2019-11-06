package dev.juniorstreichan.artists.api.service;

import dev.juniorstreichan.artists.core.model.Artist;
import dev.juniorstreichan.artists.core.service.CRUDService;

import java.util.List;

public interface IArtistService extends CRUDService<Artist> {
    List<Artist> listOrderByNameLenght();
}
