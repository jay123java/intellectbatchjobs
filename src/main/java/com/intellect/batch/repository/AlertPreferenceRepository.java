package com.intellect.batch.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.intellect.batch.entity.UserAlertPreference;


@RestResource(path = "alertpreferences")
public interface AlertPreferenceRepository extends CrudRepository<UserAlertPreference, Long>{

}
