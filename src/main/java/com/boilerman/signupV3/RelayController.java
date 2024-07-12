package com.boilerman.signupV3;


import com.boilerman.signupV3.DTO.Relay;
import com.boilerman.signupV3.DTO.ThreeRelay;
import com.boilerman.signupV3.DTO.TwoRelay;
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
public class RelayController {

    private final RelayRepository repository;
    private final RelayModelAssembler assembler;

    RelayController(RelayRepository repository, RelayModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/relay/{id}")
    EntityModel<Relay> getEntry(@PathVariable Long id) {
        Relay entry = repository.findById(id).orElseThrow(() -> new EntryNotFoundException(id));

        return assembler.toModel(entry);
    }

    @GetMapping("/relay/all")
    CollectionModel<EntityModel<Relay>> getAllEntries() {

        List<EntityModel<Relay>> entries = new ArrayList<>();

        List<Relay> original_list = repository.findAll();

        for (Relay original : original_list) {
            entries.add(assembler.toModel(original));
        }

        return CollectionModel.of(entries, linkTo(methodOn(RelayController.class).getAllEntries()).withSelfRel());

    }

    @PostMapping("/relay/two")
    ResponseEntity<?> newTwoRelay(@RequestBody TwoRelay newRelay) {

        EntityModel<Relay> entityModel = assembler.toModel(repository.save(newRelay));

        try {
            if (!GoogleSheets.checkSheetExists(LocalDate.now().getYear() + "_Relay")) {
                GoogleSheets.createRelaySheet();
            }
            GoogleSheets.postRelay(newRelay);
        } catch (GeneralSecurityException e) {
            return new ResponseEntity<String>("Could not write to excel sheet", HttpStatus.NOT_MODIFIED);
        } catch (IOException e) {
            return new ResponseEntity<String>("Could not write to excel sheet", HttpStatus.NOT_MODIFIED);
        }

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }


    @PostMapping("/relay/three")
    ResponseEntity<?> newThreeRelay(@RequestBody ThreeRelay newRelay) {

        System.out.println(newRelay.toString());

        EntityModel<Relay> entityModel = assembler.toModel(repository.save(newRelay));

        try {
            if (!GoogleSheets.checkSheetExists(LocalDate.now().getYear() + "_Relay")) {
                GoogleSheets.createRelaySheet();
            }
            GoogleSheets.postRelay(newRelay);
        } catch (GeneralSecurityException e) {
            return new ResponseEntity<String>("Could not write to excel sheet", HttpStatus.NOT_MODIFIED);
        } catch (IOException e) {
            return new ResponseEntity<String>("Could not write to excel sheet", HttpStatus.NOT_MODIFIED);
        }


        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }






}
