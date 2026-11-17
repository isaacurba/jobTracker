package com.jotTracker.data.models;

import com.jotTracker.enums.ApplicationStatus.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.jotTracker.enums.ApplicationStatus.APPLIED;
import static org.junit.jupiter.api.Assertions.*;

class JobApplicationTest {

    private JobApplication jobApplication;

    @BeforeEach
    public void setUp(){
        jobApplication = new JobApplication();
    }

    @Test
    public void test_to_set_and_get_company_name(){
        jobApplication.setCompanyName("sante");
        assertEquals("sante", jobApplication.getCompanyName());
    }

    @Test
    public void test_to_test_to_set_and_get_application_status(){
        jobApplication.setStatus(APPLIED);
        assertEquals(APPLIED, jobApplication.getStatus());
    }

    @Test
    public void test_to_set_and_get_note(){
        jobApplication.setNote("This is a note of a particular company");
        assertEquals("This is a note of a particular company", jobApplication.getNote());
    }
}