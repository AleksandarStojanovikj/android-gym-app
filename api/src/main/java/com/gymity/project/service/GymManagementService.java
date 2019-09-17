package com.gymity.project.service;

import com.gymity.project.exceptions.GymAlreadyExists;
import com.gymity.project.exceptions.GymDoesNotExist;
import com.gymity.project.exceptions.UserDoesNotExist;
import com.gymity.project.model.Comment;
import com.gymity.project.model.Gym;
import com.gymity.project.model.Offer;
import com.gymity.project.model.dto.OfferDto;

import java.util.List;

public interface GymManagementService {
    void addGym(Gym gym) throws GymAlreadyExists;

    Gym getGym(Long id) throws GymDoesNotExist;

    List<Gym> getAllGyms();

    List<Comment> getGymComments(Long id) throws GymDoesNotExist;

    List<OfferDto> getGymOffers(Long id) throws GymDoesNotExist;

    Comment addCommentForGym(Long id, Comment comment) throws GymDoesNotExist, UserDoesNotExist;
}
