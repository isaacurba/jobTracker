
package com.jotTracker.data.repositories;

import com.jotTracker.data.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class UserRepoTest {

    @Autowired
    private UserRepo userRepo;

    private User createUser(String email) {
        return User.builder()
                .fullName("Zicco")
                .email(email)
                .password("52315678")
                .build();
    }

    @Test
    void saveUserTest() {
        User user = createUser("zicco@mail.com");

        User savedUser = userRepo.save(user);

        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);
        assertEquals(1, userRepo.count());
    }

    @Test
    void getUserByIdTest() {
        User user = createUser("zicco@mail.com");
        User savedUser = userRepo.save(user);

        User foundUser = userRepo.findById(savedUser.getId()).orElseThrow();

        assertThat(foundUser.getId()).isEqualTo(savedUser.getId());
        assertThat(foundUser.getEmail()).isEqualTo("zicco@mail.com");
        assertThat(foundUser.getFullName()).isEqualTo("Zicco");
    }

    @Test
    void getMultipleUsersTest() {
        User firstUser = createUser("zicco@mail.com");
        User secondUser = createUser("anotherzicco@mail.com");

        userRepo.save(firstUser);
        userRepo.save(secondUser);

        assertEquals(2, userRepo.count());
    }

    @Test
    void updateUserTest() {
        User user = createUser("zicco@mail.com");
        User savedUser = userRepo.save(user);

        savedUser.setFullName("Zicco Updated");
        savedUser.setEmail("updated@mail.com");

        userRepo.save(savedUser);

        User updatedUser = userRepo.findById(savedUser.getId()).orElseThrow();

        assertThat(updatedUser.getFullName()).isEqualTo("Zicco Updated");
        assertThat(updatedUser.getEmail()).isEqualTo("updated@mail.com");
    }

    @Test
    void deleteUserTest() {
        User user = createUser("zicco@mail.com");
        User savedUser = userRepo.save(user);

        userRepo.delete(savedUser);

        assertThat(userRepo.findById(savedUser.getId())).isEmpty();
        assertEquals(0, userRepo.count());
    }

    @Test
    void findByIdWithNonExistentIdTest() {
        assertThat(userRepo.findById(-1L)).isEmpty();
    }

    @Test
    void findByEmailTest(){
        User user = createUser("zicco@mail.com");
        userRepo.save(user);
        Optional<User> foundUser = userRepo.findByEmail("zicco@mail.com");
        assertThat(foundUser.isPresent()).isTrue();

    }
}
