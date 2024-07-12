package com.boilerman.signupV3;

import com.boilerman.signupV3.DTO.Collegiate;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CollegiateModelAssembler implements RepresentationModelAssembler<Collegiate, EntityModel<Collegiate>> {

    public EntityModel<Collegiate> toModel(Collegiate entry) {

        return EntityModel.of(entry,
                                linkTo(methodOn(CollegiateController.class).getEntry(entry.getId())).withSelfRel(),
                                linkTo(methodOn(CollegiateController.class).all()).withRel("entry")
                );
    }
}
