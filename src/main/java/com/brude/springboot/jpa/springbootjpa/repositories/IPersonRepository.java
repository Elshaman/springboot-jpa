package com.brude.springboot.jpa.springbootjpa.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.brude.springboot.jpa.springbootjpa.entities.Person;
import java.util.List;
import java.util.Optional;


//generic del tipo persopn y el tipo de dato de la PK
public interface IPersonRepository extends CrudRepository<Person , Long> {

    @Query("select p from Person p where p.id = ?1")
    Optional<Person> findOne(Long id);

    @Query("select p from Person p where p.firstName like %?1%")
    Optional<Person> findOneLikeName(String name);

    //convencion por defecto
    Optional<Person> findByFirstNameContaining(String name);

    List<Person> findByProgrammingLanguaje(String programmingLanguaje);

    @Query("select p from Person p where p.programmingLanguaje=?1 and p.firstName=?2 ")
    List<Person> BuscarByProgrammingLanguaje(String programmingLanguaje, String firstName);

    List<Person> findByProgrammingLanguajeAndFirstName(String programmingLanguaje, String firstName);

    @Query("select p.firstName, p.programmingLanguaje from Person p")
    List<Object[]> obtenerPersonData();

     @Query("select p.firstName, p.programmingLanguaje from Person p where p.programmingLanguaje=?1 and p.firstName=?2")
    List<Object[]> obtenerPersonData(String programmingLanguaje, String firstName);


}
