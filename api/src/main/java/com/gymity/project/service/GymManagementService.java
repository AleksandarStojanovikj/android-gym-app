package com.gymity.project.service;

import com.gymity.project.exceptions.GymAlreadyExists;
import com.gymity.project.exceptions.GymDoesNotExist;
import com.gymity.project.model.Comment;
import com.gymity.project.model.Gym;
import com.gymity.project.model.Offer;

import java.util.List;

public interface GymManagementService {
    void addGym(Gym gym) throws GymAlreadyExists;
    Gym getGym(Long id) throws GymDoesNotExist;
    List<Gym> getAllGyms();
    List<Comment> getGymComments(Long id) throws GymDoesNotExist;
    List<Offer> getGymOffers(Long id) throws GymDoesNotExist;
}
