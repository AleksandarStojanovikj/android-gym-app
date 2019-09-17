package com.gymity.project.service.impl;

import com.gymity.project.exceptions.GymAlreadyExists;
import com.gymity.project.exceptions.GymDoesNotExist;
import com.gymity.project.exceptions.UserDoesNotExist;
import com.gymity.project.model.Comment;
import com.gymity.project.model.Gym;
import com.gymity.project.model.Offer;
import com.gymity.project.model.Users;
import com.gymity.project.model.dto.OfferDto;
import com.gymity.project.repository.CommentRepository;
import com.gymity.project.repository.GymsRepository;
import com.gymity.project.repository.UserRepository;
import com.gymity.project.service.GymManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GymManagementServiceImpl implements GymManagementService {
    private final GymsRepository gymsRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Autowired
    public GymManagementServiceImpl(GymsRepository gymsRepository, CommentRepository commentRepository, UserRepository userRepository) {
        this.gymsRepository = gymsRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
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
    public List<OfferDto> getGymOffers(Long id) throws GymDoesNotExist {
        Gym gym = gymsRepository.findById(id).orElseThrow(GymDoesNotExist::new);

        return gym.offers.stream()
                .map(OfferDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public Comment addCommentForGym(Long id, Comment comment) throws GymDoesNotExist, UserDoesNotExist {
        Gym gym = gymsRepository.findById(id).orElseThrow(GymDoesNotExist::new);
        Users user = Optional.of(userRepository.findByCredentialsUsername(comment.getUser().credentials.username))
                .orElseThrow(UserDoesNotExist::new);

        comment.setGym(gym);
        comment.setUser(user);
        commentRepository.save(comment);

        return comment;
    }
}
