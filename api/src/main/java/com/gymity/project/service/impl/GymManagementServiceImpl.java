package com.gymity.project.service.impl;

import com.gymity.project.exceptions.GymAlreadyExists;
import com.gymity.project.exceptions.GymDoesNotExist;
import com.gymity.project.model.Comment;
import com.gymity.project.model.Gym;
import com.gymity.project.model.Offer;
import com.gymity.project.repository.GymsRepository;
import com.gymity.project.service.GymManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        if (gymsRepository.findByName(gym.name) != null)
            throw new GymAlreadyExists();

        gymsRepository.save(gym);
    }

    @Override
    public Gym getGym(Long id) throws GymDoesNotExist {
        if (gymsRepository.findById(id) == null)
            throw new GymDoesNotExist();

        return gymsRepository.findById(id).get();
    }

    @Override
    public List<Gym> getAllGyms() {
        return gymsRepository.findAll();
    }

    @Override
    public List<Comment> getGymComments(Long id) throws GymDoesNotExist {
        Gym gym = gymsRepository.findById(id).orElseThrow(GymDoesNotExist::new);

        return gym.comments;
    }

    @Override
    public List<Offer> getGymOffers(Long id) throws GymDoesNotExist {
        Gym gym = gymsRepository.findById(id).orElseThrow(GymDoesNotExist::new);

        return gym.offers;
    }
}
