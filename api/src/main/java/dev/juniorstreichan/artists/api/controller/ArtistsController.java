package dev.juniorstreichan.artists.api.controller;

import dev.juniorstreichan.artists.api.service.IArtistService;
import dev.juniorstreichan.artists.core.model.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/artist")
public class ArtistsController {

    @Autowired
    private IArtistService artistService;

    @GetMapping("page")
    public HttpEntity<Page<Artist>> getPage(Pageable pageable) {
        return ResponseEntity.ok(artistService.page(pageable));
    }

    @GetMapping("list")
    public HttpEntity<List<Artist>> getListByName(
        @RequestParam(name = "name", defaultValue = "") String name,
        @RequestParam(name = "sortBy", defaultValue = "") String sortBy,
        @RequestParam(name = "direction", defaultValue = "asc") String direction
    ) {
        List<Artist> list;
        switch (sortBy) {
            case "size":
                list = artistService.listByNameOrderByNameSize(name, Sort.Direction.fromString(direction));
                break;
            /*
            foi feito em formato de switch
            para suportar outros tipos de ordenação futuramente
            */
            default:
                list = artistService.listByName(name, Sort.Direction.fromString(direction));

        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("{id}")
    public HttpEntity<Artist> getById(
        @PathVariable Long id
    ) {
        var artist = artistService.byId(id);
        if (artist == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(artist);
    }

    @PostMapping
    public HttpEntity<Artist> post(
        @RequestBody Artist artist
    ) {
        try {
            artist.getAlbums().forEach((a) -> a.setArtist(artist));

            return ResponseEntity.status(201).body(artistService.insert(artist));
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping
    public HttpEntity<Artist> put(
        @RequestBody Artist artist
    ) {
        try {
            artist.getAlbums().forEach((a) -> a.setArtist(artist));

            return ResponseEntity.ok(artistService.update(artist));
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("{id}")
    public HttpEntity<Artist> deleteById(
        @PathVariable Long id
    ) {
        artistService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
