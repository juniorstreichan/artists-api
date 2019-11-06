package dev.juniorstreichan.artists.core.repository;

import dev.juniorstreichan.artists.core.mock.Generator;
import dev.juniorstreichan.artists.core.model.Artist;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

public class ArtistRepositoryTests extends RepositoryTest {

    @Autowired
    private ArtistRepository artistRepository;
    private Artist myArtist;

    @BeforeEach
    void deve_inserir_novo_artista() {
        var artist = Generator.generateArtist();
        myArtist = artistRepository.save(artist);

        Assertions.assertThat(myArtist.getId()).isNotNull();
        Assertions.assertThat(myArtist.getId()).isGreaterThan(0);
        Assertions.assertThat(myArtist.getName()).isEqualTo(artist.getName());
        Assertions.assertThat(myArtist.getAlbums()).isNotEmpty();
    }


    @Test
    void deve_alterar_artista() {
        var newName = "New name";
        myArtist.setName(newName);
        var updateArtists = artistRepository.save(myArtist);

        Assertions.assertThat(updateArtists.getId()).isEqualTo(myArtist.getId());
        Assertions.assertThat(updateArtists.getName()).isEqualTo(newName);
    }

    @Test
    void deve_excluir_artista() {
        artistRepository.delete(myArtist);
        var deleted = artistRepository.findById(myArtist.getId()).orElse(null);
        Assertions.assertThat(deleted).isNull();
    }

    @Test
    void deve_fazer_busca_com_ordenacao() {
        artistRepository.save(Generator.generateArtist());
        artistRepository.save(Generator.generateArtist());
        artistRepository.save(Generator.generateArtist());

        var artists = artistRepository.findOrderByNameLength(Sort.by("fn_len"));
        Assertions.assertThat(artists.get(0).getName().length()).isLessThanOrEqualTo(artists.get(1).getName().length());
    }
}
