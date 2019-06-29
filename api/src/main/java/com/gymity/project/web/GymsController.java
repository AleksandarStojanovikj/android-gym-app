package com.gymity.project.web;

import com.gymity.project.exceptions.GymAlreadyExists;
import com.gymity.project.exceptions.GymDoesNotExist;
import com.gymity.project.model.Gym;
import com.gymity.project.service.GymManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/gyms", produces = MediaType.APPLICATION_JSON_VALUE)
public class GymsController {
    private final GymManagementService gymManagementService;

    @Autowired
    public GymsController(GymManagementService gymManagementService) {
        this.gymManagementService = gymManagementService;
    }

    @PostMapping
    public ResponseEntity<Gym> addGym(@RequestBody Gym gym) {
        try {
            gymManagementService.addGym(gym);
            return ResponseEntity.status(HttpStatus.OK).body(gym);
        } catch (GymAlreadyExists gymAlreadyExists) {
            System.out.println(gymAlreadyExists.message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Gym>> getAllGyms() {
        return ResponseEntity.status(HttpStatus.OK).body(gymManagementService.getAllGyms());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Gym> getGym(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(gymManagementService.getGym(id));
        } catch (GymDoesNotExist gymDoesNotExist){
            System.out.println(gymDoesNotExist.message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
