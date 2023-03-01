package com.ryukyo.lessonslearned.service;

import com.ryukyo.lessonslearned.constants.SchoolConstants;
import com.ryukyo.lessonslearned.model.Person;
import com.ryukyo.lessonslearned.model.Roles;
import com.ryukyo.lessonslearned.repository.PersonRepository;
import com.ryukyo.lessonslearned.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RolesRepository rolesRepository;

    public boolean createNewPerson(Person person){
        boolean isSaved = false;
        Roles role = rolesRepository.getByRoleName(SchoolConstants.STUDENT_ROLE);
        person.setRoles(role);
        person = personRepository.save(person);
        if (null != person && person.getPersonId() > 0)
        {
            isSaved = true;
        }
        return isSaved;
    }
}
