package com.rest.jwt_auth.restjwtproject;

import com.rest.jwt_auth.controller.AdminController;
import com.rest.jwt_auth.controller.AuthController;
import com.rest.jwt_auth.controller.BookController;
import com.rest.jwt_auth.controller.MainController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestJwtProjectApplicationTest {

    @Autowired
    private AdminController adminController;

    @Autowired
    private AuthController authController;

    @Autowired
    private BookController bookController;

    @Autowired
    private MainController mainController;

    @Test
    void test_adminController_isNotNull() {
        Assertions.assertThat(adminController).isNotNull();
    }

    @Test
    void test_authController_isNotNull() {
        Assertions.assertThat(authController).isNotNull();
    }

    @Test
    void test_bookController_isNotNull() {
        Assertions.assertThat(bookController).isNotNull();
    }

    @Test
    void test_mainController_isNotNull() {
        Assertions.assertThat(mainController).isNotNull();
    }
}
