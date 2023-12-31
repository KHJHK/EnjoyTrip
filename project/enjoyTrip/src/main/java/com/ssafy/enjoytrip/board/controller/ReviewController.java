package com.ssafy.enjoytrip.board.controller;

import com.ssafy.enjoytrip.board.dto.ReviewDto;
import com.ssafy.enjoytrip.board.dto.ReviewPhotoDto;
import com.ssafy.enjoytrip.board.model.service.ReviewService;
import com.ssafy.enjoytrip.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {
    @Value("${file.path.upload-review}")
    private String uploadPath;


    private final ReviewService reviewService;
    @PostMapping
    public ResponseEntity<?> postReview(@RequestParam(required = false) MultipartFile[] files,
                                        @RequestParam String reviewTitle,
                                        @RequestParam String reviewContent){
        try {
            ReviewDto review = new ReviewDto();
            review.setUserNo(JWTUtil.userNo);
            review.setReviewTitle(reviewTitle);
            review.setReviewContent(reviewContent);

            if(files != null) {
                String today = new SimpleDateFormat("yyMMdd").format(new Date());
                String saveFolder = uploadPath + File.separator + today;
                File folder = new File(saveFolder);
                if (!folder.exists()) {
                    folder.mkdirs();
                }
                List<ReviewPhotoDto> fileInfos = new ArrayList<ReviewPhotoDto>();
                int cnt = 0;
                for (MultipartFile mfile : files) {
                    ReviewPhotoDto fileInfoDto = new ReviewPhotoDto();
                    String originalFileName = mfile.getOriginalFilename();
                    if (!originalFileName.isEmpty()) {
                        String saveFileName = UUID.randomUUID().toString()
                                + originalFileName.substring(originalFileName.lastIndexOf('.'));
                        fileInfoDto.setImageLocation(today);
                        fileInfoDto.setOriginalName(originalFileName);
                        fileInfoDto.setSaveName(saveFileName);
                        fileInfoDto.setOrder(cnt++);
                        mfile.transferTo(new File(folder, saveFileName));
                    }
                    fileInfos.add(fileInfoDto);
                }
                review.setPhotos(fileInfos);
            }

            reviewService.postReview(review);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping
    public ResponseEntity<?> getReviewList(){
        try{
            List<ReviewDto> reviewList = reviewService.getReviewList();
            return new ResponseEntity<List<ReviewDto>>(reviewList, HttpStatus.OK);
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<?> getReviewById(@PathVariable int reviewId){
        try{
            reviewService.increaseHits(reviewId);
            ReviewDto review = reviewService.getReviewById(reviewId);
            return new ResponseEntity<ReviewDto>(review, HttpStatus.OK);
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @PutMapping
    public ResponseEntity<?> modifyReview(@RequestParam(required = false) MultipartFile[] files,
                                          @RequestParam int reviewId,
                                          @RequestParam int userNo,
                                          @RequestParam String reviewTitle,
                                          @RequestParam String reviewContent){
        try{
            if(JWTUtil.userNo != userNo){
                return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
            }
            ReviewDto review = new ReviewDto();
            review.setReviewId(reviewId);
            review.setReviewTitle(reviewTitle);
            review.setReviewContent(reviewContent);

            if(files != null) {
                String today = new SimpleDateFormat("yyMMdd").format(new Date());
                String saveFolder = uploadPath + File.separator + today;
                File folder = new File(saveFolder);
                if (!folder.exists()) {
                    folder.mkdirs();
                }
                List<ReviewPhotoDto> fileInfos = new ArrayList<ReviewPhotoDto>();
                int cnt = 0;
                for (MultipartFile mfile : files) {
                    ReviewPhotoDto fileInfoDto = new ReviewPhotoDto();
                    String originalFileName = mfile.getOriginalFilename();
                    if (!originalFileName.isEmpty()) {
                        String saveFileName = UUID.randomUUID().toString()
                                + originalFileName.substring(originalFileName.lastIndexOf('.'));
                        fileInfoDto.setImageLocation(today);
                        fileInfoDto.setOriginalName(originalFileName);
                        fileInfoDto.setSaveName(saveFileName);
                        fileInfoDto.setOrder(cnt++);
                        mfile.transferTo(new File(folder, saveFileName));
                    }
                    fileInfos.add(fileInfoDto);
                }
                review.setPhotos(fileInfos);
            }
            reviewService.modifyReview(review, uploadPath);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable int reviewId){
        try{
            int userNo = reviewService.getReviewById(reviewId).getUserNo();
            if(JWTUtil.userNo != userNo){
                return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
            }
            reviewService.deleteReview(reviewId, uploadPath);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
