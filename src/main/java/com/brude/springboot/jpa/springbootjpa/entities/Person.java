package com.brude.springboot.jpa.springbootjpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "persons")
public class Person {

        @Embedded
        private Audit audit = new Audit();

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "first_name")
        private String firstName;

        @Column(name = "last_name")
        private String lastName;

        @Column(name = "programming_languaje")
        private String programmingLanguaje;

        //si ponemos constructor con parametros
        //estamos oblñigados a poner constructor vacio
        public Person() {}

        public Person(Long id, String firstName, String lastName, String programmingLanguaje) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.programmingLanguaje = programmingLanguaje;
        }



        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getFirstName() {
            return firstName;
        }
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
        public String getLastName() {
            return lastName;
        }
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
        public String getProgrammingLanguaje() {
            return programmingLanguaje;
        }
        public void setProgrammingLanguaje(String programmingLanguaje) {
            this.programmingLanguaje = programmingLanguaje;
        }

        @Override
        public String toString() {
            return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", programmingLanguaje="
                    + programmingLanguaje + ", createdAt=" + audit.getCreatedAt() + ", updatedAt=" + audit.getUpdatedAt() + "]";
        }

        
}
