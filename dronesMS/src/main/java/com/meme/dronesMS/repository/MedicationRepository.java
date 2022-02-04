package com.meme.dronesMS.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.meme.dronesMS.model.Medication;

@Repository
public interface MedicationRepository extends CrudRepository<Medication, Integer>{

}
