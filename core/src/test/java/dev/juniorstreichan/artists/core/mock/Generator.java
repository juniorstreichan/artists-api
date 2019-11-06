package dev.juniorstreichan.artists.core.mock;

import com.github.javafaker.Faker;
import dev.juniorstreichan.artists.core.model.Album;
import dev.juniorstreichan.artists.core.model.Artist;

import java.util.Locale;

public class Generator {
    private static final Faker faker = new Faker(new Locale("pt-BR"));

    public static Artist generateArtist() {
        var artist = new Artist();
        artist.setName(faker.artist().name());

        for (int i = 0; i < 4; i++) {
            artist.getAlbums().add(
                new Album(
                    null,
                    faker.music().chord() + " " + faker.music().instrument(),
                    artist
                )
            );
        }

        return artist;
    }

}
