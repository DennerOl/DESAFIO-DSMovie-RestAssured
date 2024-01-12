package com.devsuperior.dsmovie.controllers;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;

public class MovieControllerRA {

	private String movieName;
	private Long existingMovieId, nonExistingMovieId, dependentMovieId;

	@BeforeEach
	public void setup() throws JSONException {
		baseURI = "http://localhost:8080";

		movieName = "The Witcher";
	}

	@Test
	public void findAllShouldReturnOkWhenMovieNoArgumentsGiven() {

		given()
				.get("/movies")
				.then().statusCode(200);

	}

	@Test
	public void findAllShouldReturnPagedMoviesWhenMovieTitleParamIsNotEmpty() {
		given()
				.get("/movies?name={movieName}", movieName)
				.then()
				.statusCode(200)
				.body("content.id[0]", is(1))
				.body("content.title[0]", equalTo("The Witcher"))
				.body("content.score[0]", is(4.5f))
				.body("content.count[0]", is(2))
				.body("content.image[0]",
						equalTo("https://www.themoviedb.org/t/p/w533_and_h300_bestv2/jBJWaqoSCiARWtfV0GlqHrcdidd.jpg"));
	}

	@Test
	public void findByIdShouldReturnMovieWhenIdExists() {
		existingMovieId = 1L;
		given()
				.get("/movies/{id}", existingMovieId)
				.then()
				.statusCode(200)
				.body("id", is(1))
				.body("title", equalTo("The Witcher"))
				.body("score", is(4.5f))
				.body("count", is(2))
				.body("image",
						equalTo("https://www.themoviedb.org/t/p/w533_and_h300_bestv2/jBJWaqoSCiARWtfV0GlqHrcdidd.jpg"));
	}

	@Test
	public void findByIdShouldReturnNotFoundWhenIdDoesNotExist() {
		nonExistingMovieId = 1000L;
		given()
				.get("/movies/{id}", nonExistingMovieId)
				.then()
				.statusCode(404)
				.body("error", equalTo("Recurso não encontrado"))
				.body("status", equalTo(404));

	}

	@Test
	public void insertShouldReturnUnprocessableEntityWhenAdminLoggedAndBlankTitle() throws JSONException {
	}

	@Test
	public void insertShouldReturnForbiddenWhenClientLogged() throws Exception {
	}

	@Test
	public void insertShouldReturnUnauthorizedWhenInvalidToken() throws Exception {
	}
}
