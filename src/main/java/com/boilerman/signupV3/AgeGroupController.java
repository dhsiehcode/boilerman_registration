package com.boilerman.signupV3;

import com.boilerman.signupV3.DTO.AgeGroup;
import org.apache.http.protocol.HTTP;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


/**
 *
 * sample POST request
 *
 * $ curl -v -X POST localhost:8080/age_group -H 'Content-Type:application/json'
 *  -d '{"firstName": "Dennis", "lastName": "Hsieh", "usatNum":"0001", "paymentNum": "00001"
 *       "email":"hhorse225@gmail.com", "city":"west lala", "state":"IN",
 *       "phoneNumber":"6103933737", "shirtSize","S", "dateOfBirth","09/23/2002", "sex":"m"}' | json_pp
 */


@RestController
public class AgeGroupController {

    private final AgeGroupRepository repository;
    private final AgeGroupModelAssembler assembler;

    public AgeGroupController(AgeGroupRepository repository, AgeGroupModelAssembler assembler) {

        this.repository = repository;
        this.assembler = assembler;

    }

    @GetMapping("/age_group_entries")
    CollectionModel<EntityModel<AgeGroup>> all() {
        List<EntityModel<AgeGroup>> entries = new ArrayList<EntityModel<AgeGroup>>();
        List<AgeGroup> original_list = repository.findAll();

        for (AgeGroup original: original_list) {
            entries.add(assembler.toModel(original));
        }

        return CollectionModel.of(entries, linkTo(methodOn(AgeGroupController.class).all()).withSelfRel());
    }
    //

    @GetMapping("/age_group/{id}")
    EntityModel<AgeGroup> getAgeGroup(@RequestBody Long id) {
        AgeGroup entry = repository.findById(id).orElseThrow(() -> new EntryNotFoundException(id));

        return assembler.toModel(entry);
    }


    @PostMapping("/age_group")
    ResponseEntity<?> newAgeGroup(@RequestBody AgeGroup newAgeGroupEntry) {

        try {

            if (!GoogleSheets.checkSheetExists(LocalDate.now().getYear() + "_AgeGroup")) {
                GoogleSheets.createAgeGroupSheet();
            }

            GoogleSheets.postAgeGroup(newAgeGroupEntry);

        } catch (GeneralSecurityException e) {
            return new ResponseEntity<String>("Could not write to excel sheet", HttpStatus.NOT_MODIFIED);
        } catch (IOException e) {
            return new ResponseEntity<String>("Could not write to excel sheet", HttpStatus.NOT_MODIFIED);
        }




        EntityModel<AgeGroup> entityModel = assembler.toModel(repository.save(newAgeGroupEntry));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }
}
