package com.movielib.backend.mapper;

import com.movielib.backend.dto.MovieDTO;
import com.movielib.backend.model.Actor;
import com.movielib.backend.model.Category;
import com.movielib.backend.model.Movie;

import java.util.stream.Collectors;

public class MovieMapper {


    /*public static MovieDTO mapToMovieDTO(Movie movie){
        return MovieDTO.builder()
                .id(movie.getId())
                .imageUrl(movie.getImageUrl())
                .title(movie.getTitle())
                .category(movie.getCategory().getName())
                .summary(movie.getSummary())
                .releaseDate(movie.getReleaseDate())
                .director(movie.getDirector())
                .rating(movie.getRating())
                .actors(movie.getActors()
                        .stream()
                        .map(Actor::getName)
                        .collect(Collectors.toList()))
                .reviews(movie.getReviews()
                        .stream()
                        .map(Review::toString)
                        .collect(Collectors.toList()))
                .build();
    }*/

    public static MovieDTO mapToMovieDTO(Movie movie){
        return MovieDTO.builder()
                .id(movie.getId())
                .imageUrl(movie.getImageUrl())
                .title(movie.getTitle())
                .category(movie.getCategory().getName())
                .summary(movie.getSummary())
                .releaseDate(movie.getReleaseDate())
                .director(movie.getDirector())
                .rating(movie.getRating())
                .actors(movie.getActors()
                        .stream()
                        .map(Actor::getName)
                        .collect(Collectors.toList()))
                .reviews(movie.getReviews()
                        .stream()
                        .map(ReviewMapper::mapToReviewDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    /*public static Movie mapToMovie(MovieDTO movieDTO) {
        return Movie.builder()
                .id(movieDTO.getId())
                .title(movieDTO.getTitle())
                .imageUrl(movieDTO.getImageUrl())
                .category(Category.builder().name(movieDTO.getCategory()).build())
                .summary(movieDTO.getSummary())
                .releaseDate(movieDTO.getReleaseDate())
                .director(movieDTO.getDirector())
                .rating(movieDTO.getRating())
                .actors(movieDTO.getActors()
                        .stream()
                        .map(Actor::new)
                        .collect(Collectors.toList()))
                .reviews(mapToReviewList(movieDTO.getReviews()))
                .build();
    }*/

    public static Movie mapToMovie(MovieDTO movieDTO) {
        Movie movie = Movie.builder()
                .id(movieDTO.getId())
                .title(movieDTO.getTitle())
                .imageUrl(movieDTO.getImageUrl())
                .category(Category.builder().name(movieDTO.getCategory()).build())
                .summary(movieDTO.getSummary())
                .releaseDate(movieDTO.getReleaseDate())
                .director(movieDTO.getDirector())
                .rating(movieDTO.getRating())
                .actors(movieDTO.getActors()
                        .stream()
                        .map(Actor::new)
                        .collect(Collectors.toList()))
                .reviews(movieDTO.getReviews()
                        .stream()
                        .map(ReviewMapper::mapToReview)
                        .collect(Collectors.toList()))
                .build();
        movie.getReviews().get(0).setMovie(movie);
        return movie;
    }

    /*public static List<Review> mapToReviewList(List<String> reviews){
        List<Review> reviewList = new ArrayList<>();

        if(reviews != null){
            for(String reviewString: reviews){
                Review review = mapToReview(reviewString);
                reviewList.add(review);
            }
        }
        return reviewList;
    }

    public static Review mapToReview(String reviewString){
        String[] splitted = reviewString.split(" ");
        if(splitted.length >= 4){
            Optional<Movie> movieOptional = movieRepository
                    .findById(Long.parseLong(splitted[1]));
            if(movieOptional.isEmpty()){
                throw new IllegalStateException("movie doesn't exists");
            }
            Movie movie = movieOptional.get();
            Optional<User> userOptional = userRepository.findById(Long.parseLong(splitted[0]));
            if(userOptional.isEmpty()){
                throw new IllegalStateException("movie doesn't exists");
            }
            User user = userOptional.get();
            double rating = Double.parseDouble(splitted[2]);
            String comment = reviewString.substring(reviewString.indexOf(splitted[3]));
            return Review.builder()
                    .user(user)
                    .movie(movie)
                    .rating(rating)
                    .comment(comment)
                    .build();
        } else {
            throw new IllegalStateException("review not valid");
        }
    }*/
}
