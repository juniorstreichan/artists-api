package dev.juniorstreichan.artists.api.service.impl;

import dev.juniorstreichan.artists.api.service.IArtistService;
import dev.juniorstreichan.artists.core.model.Artist;
import dev.juniorstreichan.artists.core.repository.ArtistRepository;
import dev.juniorstreichan.artists.core.service.BaseService;

import java.util.List;

class ArtistService extends BaseService<Artist, ArtistRepository> implements IArtistService {
    protected ArtistService(ArtistRepository repository) {
        super(repository);
    }

    @Override
    public List<Artist> listOrderByNameLenght() {
        return null;
    }
}
