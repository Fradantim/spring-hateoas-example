package com.fradantim.hateoas.model.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import com.fradantim.hateoas.persistence.entity.HasId;
import com.fradantim.hateoas.web.ResourceGetsModelById;

public abstract class AssemblerUtil<ENTITY_TYPE extends HasId<ID_TYPE>, MODEL_TYPE extends RepresentationModel<MODEL_TYPE>, ID_TYPE>
		extends RepresentationModelAssemblerSupport<ENTITY_TYPE, MODEL_TYPE> {

	public AssemblerUtil(Class<? extends ResourceGetsModelById<MODEL_TYPE, ID_TYPE>> controllerClass,
			Class<MODEL_TYPE> resourceType) {
		super(controllerClass, resourceType);
	}

	/**
	 * Returns the {@link #RepresentationModel} (MODEL_TYPE) instance with only the
	 * self link or <code>null</code> if entity is <code>null</code>.
	 */
	@SuppressWarnings("unchecked")
	public MODEL_TYPE toMiniModel(ENTITY_TYPE entity) {
		if (entity == null)
			return null;
		MODEL_TYPE model = instantiateModel(entity);
		model.add(linkTo(methodOn((Class<? extends ResourceGetsModelById<MODEL_TYPE, ID_TYPE>>) getControllerClass())
				.getById(entity.getId())).withSelfRel());
		return model;
	}

	/**
	 * Returns a collection of {@link #RepresentationModel} (MODEL_TYPE)
	 * @see #toMiniModel(HasId)
	 */
	public Collection<MODEL_TYPE> toMiniModel(Collection<ENTITY_TYPE> entities) {
		if (entities == null || entities.isEmpty())
			return Collections.emptyList();

		return entities.stream().map(entity -> toMiniModel(entity)).collect(Collectors.toList());
	}
}
