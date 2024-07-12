package com.boilerman.signupV3;

import com.boilerman.signupV3.DTO.AgeGroup;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class AgeGroupModelAssembler implements RepresentationModelAssembler<AgeGroup, EntityModel<AgeGroup>> {

    @Override
    public EntityModel<AgeGroup> toModel(AgeGroup entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(AgeGroupController.class).getAgeGroup(entity.getId())).withSelfRel()
        );
    }
}
