package dev.juniorstreichan.artists.api.controller;

import dev.juniorstreichan.artists.api.service.IArtistService;
import dev.juniorstreichan.artists.core.model.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/artist")
public class ArtistsController {

    @Autowired
    private IArtistService artistService;

    @GetMapping
    public HttpEntity<Page<Artist>> getPage(Pageable pageable) {
        return ResponseEntity.ok(artistService.page(pageable));
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
