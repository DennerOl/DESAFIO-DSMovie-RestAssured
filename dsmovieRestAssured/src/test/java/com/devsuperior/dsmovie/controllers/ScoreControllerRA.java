package com.devsuperior.dsmovie.controllers;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.devsuperior.dsmovie.tests.TokenUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ScoreControllerRA {

	private Long nonExistingMovieId;
	private String clientUsername, clientPassword, adminUsername, adminPassword;
	private String adminToken, clientToken;

	private Map<String, Object> postMovieInstance;

	@BeforeEach
	public void setup() throws JSONException {
		baseURI = "http://localhost:8080";

		postMovieInstance = new HashMap<>();
		postMovieInstance.put("id", 1);
		postMovieInstance.put("title", "Test Movie");
		postMovieInstance.put("score", 1);
		postMovieInstance.put("count", 1);
		postMovieInstance.put("image",
				"https://www.themoviedb.org/t/p/w533_and_h300_bestv2/jBJWaqoSCiARWtfV0GlqHrcdidd.jpg");

		List<Map<String, Object>> scores = new ArrayList<>();

		Map<String, Object> score1 = new HashMap<>();
		score1.put("text", 5);

		Map<String, Object> score2 = new HashMap<>();
		score2.put("text", 3);

		scores.add(score1);
		scores.add(score2);

		postMovieInstance.put("scores", scores);

		clientUsername = "alex@gmail.com";
		clientPassword = "123456";
		adminUsername = "maria@gmail.com";
		adminPassword = "123456";

		clientToken = TokenUtil.obtainAccessToken(clientUsername, clientPassword);
		adminToken = TokenUtil.obtainAccessToken(adminUsername, adminPassword);

	}

	@Test
	public void saveScoreShouldReturnNotFoundWhenMovieIdDoesNotExist() throws Exception {

		nonExistingMovieId = 999L;
		postMovieInstance.put("movieId", nonExistingMovieId);
		JSONObject updateScore = new JSONObject(postMovieInstance);
		given()
				.header("Content-type", "application/json")
				.header("Authorization", "Bearer " + adminToken)
				.body(updateScore)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.when()
				.put("/scores")
				.then()
				.statusCode(404);
	}

	@Test
	public void saveScoreShouldReturnUnprocessableEntityWhenMissingMovieId() throws Exception {

		postMovieInstance.put("id", " ");
		JSONObject updateScore = new JSONObject(postMovieInstance);
		given()
				.header("Content-type", "application/json")
				.header("Authorization", "Bearer " + clientToken)
				.body(updateScore)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.when()
				.put("/scores")
				.then()
				.statusCode(422);

	}

	@Test
	public void saveScoreShouldReturnUnprocessableEntityWhenScoreIsLessThanZero() throws Exception {

		postMovieInstance.put("scores", -3);
		JSONObject updateScore = new JSONObject(postMovieInstance);
		given()
				.header("Content-type", "application/json")
				.header("Authorization", "Bearer " + adminToken)
				.body(updateScore)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.when()
				.put("/scores")
				.then()
				.statusCode(422);

	}
}
