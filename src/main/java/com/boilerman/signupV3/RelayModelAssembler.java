package com.boilerman.signupV3;

import com.boilerman.signupV3.DTO.Relay;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RelayModelAssembler implements RepresentationModelAssembler<Relay, EntityModel<Relay>> {

    @Override
    public EntityModel<Relay> toModel(Relay entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(RelayController.class).getEntry(entity.getId())).withSelfRel()
        );
    }
}
