package com.gymity.project.service;

import com.gymity.project.exceptions.GymAlreadyExists;
import com.gymity.project.exceptions.GymDoesNotExist;
import com.gymity.project.model.Gym;

import java.util.List;

public interface GymManagementService {
    void addGym(Gym gym) throws GymAlreadyExists;
    Gym getGym(Long id) throws GymDoesNotExist;
    List<Gym> getAllGyms();
}
