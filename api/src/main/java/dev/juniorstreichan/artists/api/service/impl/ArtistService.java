package dev.juniorstreichan.artists.api.service.impl;

import dev.juniorstreichan.artists.api.service.IArtistService;
import dev.juniorstreichan.artists.core.model.Artist;
import dev.juniorstreichan.artists.core.repository.ArtistRepository;
import dev.juniorstreichan.artists.core.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
class ArtistService extends BaseService<Artist, ArtistRepository> implements IArtistService {
    @Autowired
    protected ArtistService(ArtistRepository repository) {
        super(repository);
    }

    @Override
    public List<Artist> listByName(String name, Sort.Direction direction) {
        log.info("Busca por nome: " + name);
        return repository.findByNameContainingIgnoreCase(
            name,
            Sort.by(direction, "name")
        );
    }

    @Override
    public List<Artist> listByNameOrderByNameSize(String name, Sort.Direction direction) {
        log.info("Busca por nome: " + name);
        return repository.findByNameOrderByNameLength(
            name,
            Sort.by(direction, "name")
        );
    }
}
