package dev.juniorstreichan.artists.api.service;

import dev.juniorstreichan.artists.api.mock.Generator;
import dev.juniorstreichan.artists.core.model.Artist;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

;

public class ArtistServiceTests extends ServiceTest {

    @Autowired
    private IArtistService artistService;
    private Artist myArtist;

    @BeforeEach
    void deve_inserir_novo_artista() {
        var artist = Generator.generateArtist();
        myArtist = artistService.insert(artist);

        Assertions.assertThat(myArtist.getId()).isNotNull();
        Assertions.assertThat(myArtist.getId()).isGreaterThan(0);
        Assertions.assertThat(myArtist.getName()).isEqualTo(artist.getName());
        Assertions.assertThat(myArtist.getAlbums()).isNotEmpty();
    }

    @Test
    void deve_buscar_por_id() {
        var search = artistService.byId(myArtist.getId());

        Assertions.assertThat(search.getId()).isNotNull();
        Assertions.assertThat(search.getId()).isGreaterThan(0);
        Assertions.assertThat(search.getName()).isEqualTo(myArtist.getName());
        Assertions.assertThat(search.getAlbums()).isNotEmpty();
    }

    @Test
    void deve_alterar_artista() {
        var newName = "New name";
        myArtist.setName(newName);
        var updateArtists = artistService.update(myArtist);

        Assertions.assertThat(updateArtists.getId()).isEqualTo(myArtist.getId());
        Assertions.assertThat(updateArtists.getName()).isEqualTo(newName);
    }

    @Test
    void deve_dar_erro_alterar_id_invalido() {
        org.junit.jupiter.api.Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            artistService.update(Generator.generateArtist());
        });
        org.junit.jupiter.api.Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            myArtist.setId(1000L);
            artistService.update(myArtist);
        });
    }

    @Test
    void deve_excluir_artista() {
        artistService.delete(myArtist.getId());
        var deleted = artistService.byId(myArtist.getId());
        Assertions.assertThat(deleted).isNull();
    }

    @Test
    void deve_dar_erro_excluir_id_invalido() {
        org.junit.jupiter.api.Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            artistService.delete(1000L);
        });
        org.junit.jupiter.api.Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            artistService.delete(null);
        });
    }

    @Test
    void deve_fazer_busca_paginada() {
        var page = artistService.page(PageRequest.of(0, 20));

        Assertions.assertThat(page).isNotEmpty();
    }

    @Test
    void deve_fazer_buscas_por_nome() {
        var list1 = artistService.listByName(
            myArtist.getName().substring(0, 3),
            Sort.Direction.fromString("asc")
        );
        Assertions.assertThat(list1).isNotEmpty();

        var list2 = artistService.listByNameOrderByNameSize(
            myArtist.getName().substring(0, 3),
            Sort.Direction.fromString("asc")
        );
        Assertions.assertThat(list2).isNotEmpty();
    }
}
