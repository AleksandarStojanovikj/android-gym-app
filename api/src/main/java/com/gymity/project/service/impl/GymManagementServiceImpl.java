package com.gymity.project.service.impl;

import com.gymity.project.exceptions.GymAlreadyExists;
import com.gymity.project.model.Gym;
import com.gymity.project.repository.GymsRepository;
import com.gymity.project.service.GymManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GymManagementServiceImpl implements GymManagementService {
    private final GymsRepository gymsRepository;

    @Autowired
    public GymManagementServiceImpl(GymsRepository gymsRepository) {
        this.gymsRepository = gymsRepository;
    }

    @Override
    public void addGym(Gym gym) throws GymAlreadyExists {
        if(gymsRepository.findByName(gym.name) != null)
            throw new GymAlreadyExists();

        gymsRepository.save(gym);
    }

    @Override
    public List<Gym> getAllGyms() {
        return gymsRepository.findAll();
    }
}
