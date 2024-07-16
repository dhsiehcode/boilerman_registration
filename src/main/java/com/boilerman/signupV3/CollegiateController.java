package com.boilerman.signupV3;


import com.boilerman.signupV3.DTO.Collegiate;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class CollegiateController {

    private final CollegiateRepository repository;
    private final CollegiateModelAssembler assembler;

    CollegiateController(CollegiateRepository repository, CollegiateModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/collegiate")
    CollectionModel<EntityModel<Collegiate>> all() {
        List<EntityModel<Collegiate>> entries = new ArrayList<EntityModel<Collegiate>>();
        List<Collegiate> original_list = repository.findAll();

        for (Collegiate original: original_list) {
            entries.add(assembler.toModel(original));
        }

        return CollectionModel.of(entries, linkTo(methodOn(CollegiateController.class).all()).withSelfRel());
    }
    // rsponse entity builds a respnse for you
    @PostMapping("/collegiate")
    ResponseEntity<?> newCollegiate(@RequestBody Collegiate newCollegeEntry) {

        EntityModel<Collegiate> entityModel = assembler.toModel(repository.save(newCollegeEntry));

        try {
            if (!GoogleSheets.checkSheetExists(LocalDate.now().getYear() + "_Collegiate")) {

                GoogleSheets.createCollegiateSheet();
            }

            GoogleSheets.postCollegiate(newCollegeEntry);
        } catch (GeneralSecurityException e) {
            return new ResponseEntity<String>("Could not write to excel sheet", HttpStatus.NOT_MODIFIED);
        } catch (IOException e) {
            return new ResponseEntity<String>("Could not write to excel sheet", HttpStatus.NOT_MODIFIED);
        }

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @GetMapping("/collegiate/{id}")
    EntityModel<Collegiate> getEntry(@PathVariable Long id) {
        Collegiate entry = repository.findById(id).orElseThrow(() -> new EntryNotFoundException(id));

        return assembler.toModel(entry);
    }

    @DeleteMapping("/collegiate/{id}")
    ResponseEntity<?> deleteEntry(@PathVariable Long id) {

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }


}
