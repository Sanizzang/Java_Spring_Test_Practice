//package com.example.demo.medium.controller;
//
//import com.example.demo.common.domain.exception.CertificationCodeNotMatchedException;
//import com.example.demo.common.domain.exception.ResourceNotFoundException;
//import com.example.user.domain.User;
//import com.example.user.domain.UserStatus;
//import com.example.user.domain.UserCreate;
//import com.example.user.domain.UserUpdate;
//import com.example.user.service.UserServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.mockito.BDDMockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.test.context.jdbc.SqlGroup;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
//import static org.mockito.ArgumentMatchers.any;
//
//@SpringBootTest
//@TestPropertySource("classpath:test-application.properties")
//@SqlGroup({
//        @Sql(value = "/sql/user-service-test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
//        @Sql(value = "/sql/delete-all-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
//})
//class UserServiceTest {
//
//    @Autowired
//    private UserServiceImpl userService;
//    @MockBean
//    private JavaMailSender mailSender;
//
//    @Test
//    void getByEmail은_ACTIVE_상태인_유저를_찾아올_수_있다() {
//        // given
//        String email = "tksl4732@gmail.com";
//
//        // when
//        User result = userService.getByEmail(email);
//
//        // then
//        assertThat(result.getNickname()).isEqualTo("tksl4732");
//    }
//
//    @Test
//    void getByEmail은_PENDING_상태인_유저를_찾아올_수_없다() {
//        // given
//        String email = "tksl47322@gmail.com";
//
//        // when
//        // then
//        assertThatThrownBy(() -> {
//            User result = userService.getByEmail(email);
//        }).isInstanceOf(ResourceNotFoundException.class);
//    }
//
//    @Test
//    void getById은_ACTIVE_상태인_유저를_찾아올_수_있다() {
//        // given
//        // when
//        User result = userService.getById(1);
//
//        // then
//        assertThat(result.getNickname()).isEqualTo("tksl4732");
//    }
//
//    @Test
//    void getById은_PENDING_상태인_유저를_찾아올_수_없다() {
//        // given
//        // when
//        // then
//        assertThatThrownBy(() -> {
//            User result = userService.getById(2);
//        }).isInstanceOf(ResourceNotFoundException.class);
//    }
//
//    @Test
//    void userCreateDto_를_이용하여_유저를_생성할_수_있다() {
//        // given
//        UserCreate userCreate = UserCreate.builder()
//                .email("kok2020@kakao.com")
//                .address("Gyeongi")
//                .nickname("kok202-k")
//                .build();
//        BDDMockito.doNothing().when(mailSender).send(any(SimpleMailMessage.class));
//
//        // when
//        User result = userService.create(userCreate);
//
//        // then
//        assertThat(result.getId()).isNotNull();
//        assertThat(result.getStatus()).isEqualTo(UserStatus.PENDING);
////        assertThat(result.getCertificationCode()).isEqualTo("T.T");
//    }
//
//    @Test
//    void userUpdateDto_를_이용하여_유저를_수정할_수_있다() {
//        // given
//        UserUpdate userUpdateDto = UserUpdate.builder()
//                .address("Gyeongi")
//                .nickname("kok202-k")
//                .build();
//
//        // when
//        userService.update(1, userUpdateDto);
//
//        // then
//        User user = userService.getById(1);
//        assertThat(user.getId()).isNotNull();
//        assertThat(user.getAddress()).isEqualTo("Incheon");
//        assertThat(user.getNickname()).isEqualTo("kok202-n");
//    }
//
//    @Test
//    void user를_로그인_시키면_마지막_로그인_시간이_변경된다() {
//        // given
//        // when
//        userService.login(1);
//
//        // then
//        User user = userService.getById(1);
//        assertThat(user.getLastLoginAt()).isGreaterThan(0L);
//    }
//
//    @Test
//    void PENDING_상태의_사용자는_인증_코드로_ACTIVE_시킬_수_있다() {
//        // given
//        // when
//        userService.verifyEmail(2, "aaaa-aaaa");
//
//        // then
//        User user = userService.getById(2);
//        assertThat(user.getStatus()).isGreaterThan(UserStatus.ACTIVE);
//    }
//
//    @Test
//    void PENDING_상태의_사용자는_잘못된_인증_코드를_받으면_에러를_던진다() {
//        // given
//        // when
//        // then
//        assertThatThrownBy(() -> {
//            userService.verifyEmail(2, "bbbb-bbbb");
//        }).isInstanceOf(CertificationCodeNotMatchedException.class);
//    }
//}
