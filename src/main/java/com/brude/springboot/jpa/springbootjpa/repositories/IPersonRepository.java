package com.brude.springboot.jpa.springbootjpa.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.brude.springboot.jpa.springbootjpa.dtos.PersonDto;
import com.brude.springboot.jpa.springbootjpa.entities.Person;
import java.util.List;
import java.util.Optional;


//generic del tipo persopn y el tipo de dato de la PK
public interface IPersonRepository extends CrudRepository<Person , Long> {

    @Query("SELECT p from Person p where p.id not in ?1")
    public List<Person> getPersonsById(List<Long> ids);

    @Query("SELECT p.lastName, length(p.lastName) from Person p where length(p.lastName)=(select min(length(p.lastName)) from Person p)")
    public List<Object[]> getShorterName();

    @Query("SELECT p from Person p where p.id=(select max(p.id) from Person p)")
    public Optional<Person> getUltimoRegistro();

    @Query("select min(p.id), max(p.id), sum(p.id) , avg(length(p.firstName)) , count(id) from Person p")
    public Object getResumeAggregationFunctions();

    @Query("select max(length(p.lastName)) from Person p")
    public Integer getMaxLength();

    @Query("select min(length(p.lastName)) from Person p")
    public Integer getMinLength();


    @Query("select p.firstName, length(p.firstName) FROM Person p")
    public List<Object[]> getPersonNameLength();

    @Query("select count(p) from Person p")
    Long totalPerson();

    @Query("select min(p.id) from Person p")
    Long minId();

    @Query("select max(p.id) from Person p")
    Long maxId();


    @Query("SELECT p FROM Person p order by p.firstName desc, p.lastName desc")
    List <Person> getAllOrdered();

    //y su equivalente
    List <Person> findAllByOrderByFirstNameDescLastNameAsc();

    List<Person> findByIdBetween(Long id1, Long id2);
    List<Person> findByLastNameBetween(String name1 , String name2);
    //order by convencion de nombres
    List<Person> findByLastNameBetweenOrderByIdDescFirstNameAsc(String name1 , String name2);

    //order by con query
    @Query("select p from Person p where p.lastName between ?1 and ?2 order by p.firstName desc")
    List<Person> findAllBetweenLastName(String c1 , String c2 );

    @Query("select p from Person p where p.lastName between 'T' and 'X'")
    List<Person> findAllBetwwenLastName();

    @Query("select p from Person p where p.id between 2 and 5")
    List<Person> findAllBetweenId();

    @Query("select upper( p.firstName || ' ' || p.lastName)  from Person p")
    List<String> findFullNameUpper();

    @Query("select lower(p.firstName || ' ' || p.lastName) from Person p")
    List<String> findFullNameLower();

    //@Query("select concat(p.firstName, ' ', p.lastName) as fullname from Person p")
    @Query("select p.firstName || ' ' || p.lastName as fullname from Person p")
    List<String> findFullNameConcat();

    @Query("select count(distinct(p.programmingLanguaje)) from Person p")
    Long findAllPLanguajesDistinctCount();

    @Query("select distinct(p.programmingLanguaje) from Person p")
    List<String> findAllPLanguajesDistinct();

    @Query("select distinct(p.firstName) from Person p")
    List<String> findAllNamesDistinct();

    @Query("select new com.brude.springboot.jpa.springbootjpa.dtos.PersonDto(p.firstName, p.lastName) from Person p")
    List<PersonDto> findAllPersonDto();

    @Query("select new Person(p.firstName, p.lastName) from Person p")
    List<Person> findAllClassPerson();

    @Query("select p.firstName from Person p where p.id=?1")
    String getNameById(Long id);

    @Query("select concat(p.firstName, ' ', p.lastName) as fullname from Person p where p.id=?1")
    String getFullNameById(Long id);

    @Query("select p from Person p where p.id = ?1")
    Optional<Person> findOne(Long id);

    @Query("select p from Person p where p.firstName like %?1%")
    Optional<Person> findOneLikeName(String name);

    @Query("select p, p.programmingLanguaje from Person p")
    List <Object[]> findAllMixPerson();

    @Query("select p.id, p.firstName, p.lastName, p.programmingLanguaje from Person p")
    List <Object[]> obtenerPersonDataFullList();

    @Query("select p.id, p.firstName, p.lastName, p.programmingLanguaje from Person p where p.id=?1")
    Optional<Object[]> obtenerPersonDataFullById(Long Id);

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
