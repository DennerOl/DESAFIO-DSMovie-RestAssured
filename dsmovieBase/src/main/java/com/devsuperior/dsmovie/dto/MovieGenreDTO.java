package com.devsuperior.dsmovie.dto;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import org.springframework.beans.BeanUtils;

import com.devsuperior.dsmovie.entities.MovieEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MovieGenreDTO {

	private static final DecimalFormat df = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));

	@Schema(description = "Database generated movie ID")
	private Long id;

	@Schema(description = "Movie title")
	@NotBlank(message = "Campo requerido")
	@Size(min = 5, max = 80, message = "Tamanho deve ser entre 5 e 80 caracteres")
	private String title;

	private Double score;

	private Integer count;

	private String image;
	private String genre;

	public MovieGenreDTO() {
	}

	public MovieGenreDTO(Long id,
			String title,
			Double score, Integer count, String image, String genre) {
		this.id = id;
		this.title = title;
		this.score = score;
		this.count = count;
		this.image = image;
		this.genre = genre;
	}

	public MovieGenreDTO(MovieEntity movie) {
		id = movie.getId();
		title = movie.getTitle();
		score = movie.getScore();
		count = movie.getCount();
		image = movie.getImage();
		genre = movie.getGenre().getName();
	}

	public static DecimalFormat getDf() {
		return df;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Double getScore() {
		return score;
	}

	public Integer getCount() {
		return count;
	}

	public String getImage() {
		return image;
	}

	public String getGenre() {
		return genre;
	}

	@Override
	public String toString() {
		return "MovieGenreDTO [id=" + id + ", title=" + title + ", score=" + score + ", count=" + count + ", image=" + image
				+ "]";
	}
}
