package com.example.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    // we will test StudentRepository
    @Autowired
    private StudentRepository underTest;

    // run deleteAll() method after every test method
    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }


    @Test
    void itShouldCheckIfStudentExistsEmail() {
        // given
        // insert some generic student with email
        String email = "jmail@email.com";
        Student student = new Student("jamal",email,Gender.MALE);
        underTest.save(student);
        // when
        // if we check if email exists
        boolean expected = underTest.selectExistsEmail(email);
        // then
        // must return true
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldCheckIfStudentDoesntExistsEmail() {
        // given
        String email = "jmaila@email.com";
        // when
        boolean expected = underTest.selectExistsEmail(email);
        // then
        assertThat(expected).isFalse();
    }
}