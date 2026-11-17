
package com.jotTracker.data.repositories;

import com.jotTracker.data.models.JobApplication;
import com.jotTracker.data.models.User;
import com.jotTracker.enums.ApplicationStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class JobApplicationRepoTest {

    @Autowired
    private JobApplicationRepo jobApplicationRepo;

    @Autowired
    private UserRepo userRepo;

    private User createUser() {
        return userRepo.save(
                User.builder()
                        .fullName("Zicco")
                        .email("zicco@mail.com")
                        .password("52315678")
                        .build()
        );
    }

    private JobApplication createApplication(
            User user,
            String companyName,
            ApplicationStatus status
    ) {
        JobApplication application = new JobApplication();

        application.setCompanyName(companyName);
        application.setApplicationDate(LocalDate.now());
        application.setStatus(status);
        application.setUser(user);

        return application;
    }

    @Test
    void findByStatusTest() {
        User user = createUser();

        jobApplicationRepo.save(createApplication(user, "Google", ApplicationStatus.APPLIED));

        List<JobApplication> foundApplications =
                jobApplicationRepo.findByStatus(ApplicationStatus.APPLIED);

        assertEquals(1, foundApplications.size());
        assertEquals("Google",foundApplications.get(0).getCompanyName()
        );
    }

    @Test
    void findMultipleApplicationsByStatusTest() {
        User user = createUser();

        jobApplicationRepo.save(createApplication(user, "Google", ApplicationStatus.APPLIED));

        jobApplicationRepo.save(createApplication(user, "Microsoft", ApplicationStatus.APPLIED));

        jobApplicationRepo.save(createApplication(user, "Amazon", ApplicationStatus.REJECTED));

        List<JobApplication> foundApplications =
                jobApplicationRepo.findByStatus(ApplicationStatus.APPLIED);

        assertEquals(2, foundApplications.size());

        List<String> companyNames = foundApplications.stream()
                .map(JobApplication::getCompanyName)
                .toList();

        assertTrue(companyNames.contains("Google"));
        assertTrue(companyNames.contains("Microsoft"));
    }

    @Test
    void findByStatusWhenNoApplicationsMatchTest() {
        User user = createUser();

        jobApplicationRepo.save(
                createApplication(
                        user, "Google", ApplicationStatus.APPLIED
                )
        );

        List<JobApplication> foundApplications =
                jobApplicationRepo.findByStatus(
                        ApplicationStatus.REJECTED
                );

        assertEquals(0, foundApplications.size());
    }
}
