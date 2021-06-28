package com.fradantim.hateoas.web;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;

public interface ResourceGetsModelById <T extends RepresentationModel<T>, ID> {
	public ResponseEntity<T> getById(ID id);
}
