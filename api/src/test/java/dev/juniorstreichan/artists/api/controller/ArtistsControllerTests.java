package dev.juniorstreichan.artists.api.controller;

import dev.juniorstreichan.artists.api.ApiApplicationTests;
import dev.juniorstreichan.artists.core.model.Album;
import dev.juniorstreichan.artists.core.model.Artist;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArtistsControllerTests extends ApiApplicationTests {

    private List<Artist> artists = new ArrayList<>();

    @BeforeEach
    public void deve_inserir_artistas() {
        var a1 = new Artist(
            null,
            "Serj tankian",
            Arrays.asList(
                new Album(
                    null,
                    "Harakiri",
                    null
                ),
                new Album(
                    null,
                    "Black Blooms",
                    null
                ),
                new Album(
                    null,
                    "The Rough Dog",
                    null
                )
            )
        );

        var a2 = new Artist(
            null,
            "Mike Shinoda",
            Arrays.asList(
                new Album(
                    null,
                    "The Rising Tied",
                    null
                ),
                new Album(
                    null,
                    "Post Traumatic",
                    null
                ),
                new Album(
                    null,
                    "Post Traumatic EP",
                    null
                ),
                new Album(
                    null,
                    "Where'd You Go",
                    null
                )

            )
        );

        var a3 = new Artist(
            null,
            "Michel Teló",
            Arrays.asList(
                new Album(
                    null,
                    "Bem Sertanejo",
                    null
                ),
                new Album(
                    null,
                    "Bem Sertanejo - O Show (Ao Vivo)",
                    null
                ),
                new Album(
                    null,
                    "Bem Sertanejo - (1ª Temporada) - EP",
                    null
                )
            )
        );

        var a4 = new Artist(
            null,
            "Guns N' Roses",
            Arrays.asList(
                new Album(
                    null,
                    "Use Your IIIIusion I",
                    null
                ),
                new Album(
                    null,
                    "Use Your IIIIusion II",
                    null
                ),
                new Album(
                    null,
                    "Greatest Hits",
                    null
                )
            )
        );

        Arrays.asList(a1, a2, a3, a4).forEach((artist) -> {
            var response = RestAssured.given()
                .request()
                .header("Accept", ContentType.ANY)
                .header("Content-type", ContentType.JSON)
                .body(artist)
                .post("v1/artist");

            response.then()
                .log().body()
                .and().statusCode(HttpStatus.CREATED.value())
                .body(
                    "id", Matchers.notNullValue(),
                    "name", Matchers.equalTo(artist.getName())
                );

            artists.add(response.body().as(Artist.class));
        });


    }


    @Test
    void deve_buscar_paginado() {
        var response = RestAssured.given()
            .request()
            .header("Accept", ContentType.ANY)
            .header("Content-type", ContentType.JSON)
            .get("v1/artist/page");

        response.then()
            .log().body()
            .and().statusCode(HttpStatus.OK.value())
            .body(
                "content", Matchers.notNullValue(),
                "numberOfElements", Matchers.equalTo(artists.size())
            );
    }

    @Test
    void deve_buscar_por_id() {
        var response = RestAssured.given()
            .request()
            .header("Accept", ContentType.ANY)
            .header("Content-type", ContentType.JSON)
            .pathParam("id", artists.get(1).getId())
            .get("v1/artist/{id}");

        response.then()
            .log().body()
            .and().log().headers()
            .and().statusCode(HttpStatus.OK.value())
            .body(
                Matchers.notNullValue()
            );
    }

    @Test
    void deve_alterar_artista() {
        var artist = artists.get(1);
        var newName = "Michael jackson";
        artist.setName(newName);

        var response = RestAssured.given()
            .request()
            .header("Accept", ContentType.ANY)
            .header("Content-type", ContentType.JSON)
            .body(artist)
            .put("v1/artist");

        response.then()
            .log().body()
            .and().statusCode(HttpStatus.OK.value())
            .body(
                "id", Matchers.notNullValue(),
                "name", Matchers.equalTo(newName)
            );

    }


    @Test
    void deve_deletar_artista() {
        var artist = artists.get(1);
        var response = RestAssured.given()
            .request()
            .header("Accept", ContentType.ANY)
            .header("Content-type", ContentType.JSON)
            .pathParam("id", artist.getId())
            .delete("v1/artist/{id}");

        response.then()
            .log().body()
            .and().log().headers()
            .and().statusCode(HttpStatus.NO_CONTENT.value());

        response = RestAssured.given()
            .request()
            .header("Accept", ContentType.ANY)
            .header("Content-type", ContentType.JSON)
            .pathParam("id", artist.getId())
            .get("v1/artist/{id}");

        response.then()
            .log().body()
            .and().log().headers()
            .and().statusCode(HttpStatus.NOT_FOUND.value());

    }


    @Test
    void deve_buscar_por_nome() {
        var artist = artists.get(1);
        var response = RestAssured.given()
            .request()
            .header("Accept", ContentType.ANY)
            .header("Content-type", ContentType.JSON)
            .queryParam("name", artist.getName().substring(0, 2))
            .queryParam("direction", "asc")
            .get("v1/artist/list");

        response.then()
            .log().body()
            .and().log().headers()
            .and().statusCode(HttpStatus.OK.value());

        response = RestAssured.given()
            .request()
            .header("Accept", ContentType.ANY)
            .header("Content-type", ContentType.JSON)
            .queryParam("name", artist.getName().substring(0, 2))
            .queryParam("direction", "asc")
            .queryParam("sortBy", "size")
            .get("v1/artist/list");

        response.then()
            .log().body()
            .and().log().headers()
            .and().statusCode(HttpStatus.OK.value());
    }
}
