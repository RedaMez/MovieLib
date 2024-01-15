package com.movielib.backend.controller;

import com.movielib.backend.dto.MovieDTO;
import com.movielib.backend.dto.ReviewDTO;
import com.movielib.backend.model.Review;
import com.movielib.backend.services.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public String getReviewList(Model model){
        List<ReviewDTO> reviews = reviewService.findAllReviews();
        model.addAttribute("reviews", reviews);
        return "reviews-list";
    }

    @GetMapping("/reviews/{reviewId}")
    public String getReviewDetail(@PathVariable("reviewId") long id, Model model){
        ReviewDTO reviewDTO = reviewService.findReviewById(id);
        model.addAttribute("review", reviewDTO);
        return "reviews-detail";
    }

    @GetMapping("/reviews/{movieId}/new")
    public String createReviewForm(@PathVariable("movieId") long movieId, Model model){
        Review review = new Review();
        model.addAttribute("movieId", movieId);
        model.addAttribute("review", review);
        return "reviews-create";
    }

    @PostMapping("/reviews/{movieId}/new")
    public String createReview(@PathVariable("movieId") Long movieId,
                               @ModelAttribute("review")ReviewDTO reviewDTO,
                               BindingResult result,
                               Model model){
        if(result.hasErrors()){
            model.addAttribute("review", reviewDTO);
            model.addAttribute("movieId", movieId);
            return "reviews-create";
        }
        reviewService.createReview(movieId, reviewDTO);
        return "redirect:/movies/" + movieId;
    }

    @GetMapping("/reviews/{reviewId}/edit")
    public String editReviewForm(@PathVariable("reviewId") long reviewId, Model model){
        ReviewDTO review = reviewService.findReviewById(reviewId);
        model.addAttribute("review", review);
        return "reviews-edit";
    }

    @PostMapping(path = "/reviews/{reviewId}/edit")
    public String updateReview(@PathVariable("reviewId") long reviewId,
                              @Valid @ModelAttribute("movie") ReviewDTO review,
                              BindingResult result){
        if(result.hasErrors()) {
            return "reviews-edit";
        }
        review.setId(reviewId);
        reviewService.updateReview(reviewId, review);
        return "redirect:/reviews";
    }

    @GetMapping(path = "/reviews/{reviewId}/delete")
    public String deleteReview(@PathVariable("reviewId") Long reviewId){
        reviewService.deleteReview(reviewId);
        return "redirect:/reviews";
    }

    @GetMapping("/reviews/search")
    public String searchMovie(@RequestParam(value = "query") String query, Model model){
        List<ReviewDTO> reviews = reviewService.searchReviews(query);
        model.addAttribute("review", reviews);
        return "reviews-list";
    }
}
