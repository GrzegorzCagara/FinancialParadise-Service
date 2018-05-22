package com.sda.spring.repository;

import com.sda.spring.entity.SuspiciousPerson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface SuspiciousPersonRepository extends CrudRepository<SuspiciousPerson, Integer> {

    SuspiciousPerson findSuspiciousPersonByFirstNameAndLastNameAndPesel(String firstName, String lastName, String pesel);
}
