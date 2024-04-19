package com.fz.onlineshoestore.service;

import com.fz.onlineshoestore.model.Shoe;
import com.fz.onlineshoestore.repository.ShoeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AdminServices {
    ShoeRepository shoeRepository;
    SavePhotoService savePhotoService;

    public AdminServices(ShoeRepository shoeRepository, SavePhotoService savePhotoService) {
        this.shoeRepository = shoeRepository;
        this.savePhotoService = savePhotoService;
    }

    ////////////////////////////////////////////////////////////////add
    public Shoe addShoe(Shoe shoe) {
        try {
            //get image name and set name
            String shoeName = savePhotoService.getName(shoe);
            shoe.setPhotoName(shoeName);
            //save image
            savePhotoService.savePhoto(shoe);

            log.error("im hereeee");
            return shoeRepository.save(shoe);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    //----------------------------------------------------------------remove
    public String removeShoe(Long id) {

        try {
            if (shoeRepository.findById(id) != null) {
                shoeRepository.deleteById(id);
                return "successfully removed";
            } else {
                return "not found";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
//
//    public void removeShoe(List<Shoe> shoes) {
//        try {
//            shoeRepository.deleteAll(shoes);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    //----------------------------------------------------------------get
    public Optional<List<Shoe>> getShoes() {
        try {
            return Optional.of(shoeRepository.findAll());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Shoe> getShoe(Long shoeId) {
        try {
            return shoeRepository.findById(shoeId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
