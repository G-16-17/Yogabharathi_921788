package com.cognizant.moviecruiser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.moviecruiser.model.Movie;
import com.cognizant.moviecruiser.service.Customerservice;
import com.cognizant.moviecruiser.service.MovieService;
import com.cognizant.moviecruiser.util.DateUtil;

@SpringBootApplication
public class MoviecruiserApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(MoviecruiserApplication.class);
	private static MovieService movieService;
	private static Customerservice custService;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MoviecruiserApplication.class, args);
		movieService = context.getBean(MovieService.class);
		custService = context.getBean(Customerservice.class);
		// testAddMovie();
		testCustomerService();
	}

	public static void testAddMovie() {
		LOGGER.info("Start");
		Movie movie1 = new Movie("Super Deluxe", true, "4.8", DateUtil.convertToDate("29/03/2019"), "Comedy", true);
		Movie movie2 = new Movie("Chak De! India", true, "4.5", DateUtil.convertToDate("10/08/2007"), "Drama", true);
		Movie movie3 = new Movie("A Beautiful Mind", true, "4.3", DateUtil.convertToDate("10/08/2007"), "Romance",
				true);
		movieService.addMovie(movie2);
		movieService.addMovie(movie3);
		LOGGER.debug("Movies: {}", movieService.showMovieListAdmin());
		LOGGER.debug("CustomerList: {}", movieService.getMovieById(1).getCustomerList());
		LOGGER.info("End");
	}

	public static void testCustomerService() {
		LOGGER.info("Start");
//		LOGGER.debug("CustomerList: {}", custService.getAllCustomer());
//		LOGGER.debug("Customer: {}", custService.getCustomerById(1002));
		LOGGER.debug("MovieList: {}", custService.getCustomerById(1001).getMovieList());

//		custService.addCustomer("Mani");
		LOGGER.info("End");
	}
}
