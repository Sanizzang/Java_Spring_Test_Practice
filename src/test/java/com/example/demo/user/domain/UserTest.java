package com.example.demo.user.domain;

import com.example.demo.common.domain.exception.CertificationCodeNotMatchedException;
import com.example.demo.mock.TestClockHolder;
import com.example.demo.mock.TestUuidHolder;
import com.example.user.domain.User;
import com.example.user.domain.UserCreate;
import com.example.user.domain.UserStatus;
import com.example.user.domain.UserUpdate;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.*;

public class UserTest {

    @Test
    public void UserCreate_객체로_생성할_수_있다() {
        // given
        UserCreate userCreate = UserCreate.builder()
                .email("tksl4732@gmail.com")
                .nickname("tksl4732")
                .address("Daejeon")
                .build();

        // when
        User user = User.from(userCreate, new TestUuidHolder("aaa-aaa-aaa"));

        // then
        assertThat(user.getId()).isNull();
        assertThat(user.getEmail()).isEqualTo("tksl4732@gmail.com");
        assertThat(user.getAddress()).isEqualTo("Daejeon");
        assertThat(user.getStatus()).isEqualTo(UserStatus.PENDING);
        assertThat(user.getCertificationCode()).isEqualTo("aaa-aaa-aaa");
    }

    @Test
    public void UserUpdate_객체로_데이터를_업데이트_할_수_있다() {
        // given
        User user = User.builder()
                .id(1L)
                .email("tksl4732@gmail.com")
                .nickname("tksl4732")
                .address("Daejeon")
                .status(UserStatus.ACTIVE)
                .lastLoginAt(100L)
                .certificationCode("aaa-aaa-aaa")
                .build();

        UserUpdate userUpdate = UserUpdate.builder()
                .nickname("tksl4732-1")
                .address("Seoul")
                .build();

        // when
        user = user.update(userUpdate);

        // then
        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getEmail()).isEqualTo("tksl4732@gmail.com");
        assertThat(user.getNickname()).isEqualTo("tksl4732-1");
        assertThat(user.getAddress()).isEqualTo("Seoul");
        assertThat(user.getStatus()).isEqualTo(UserStatus.PENDING);
        assertThat(user.getCertificationCode()).isEqualTo("aaa-aaa-aaa");
        assertThat(user.getLastLoginAt()).isEqualTo(100L);

    }

    @Test
    public void 로그인을_할_수_있고_로그인시_마지막_로그인_시간이_변경된다() {
        // given
        User user = User.builder()
                .id(1L)
                .email("tksl4732@gmail.com")
                .nickname("tksl4732")
                .address("Daejeon")
                .status(UserStatus.ACTIVE)
                .lastLoginAt(100L)
                .certificationCode("aaa-aaa-aaa")
                .build();

        // when
        user = user.login(new TestClockHolder(1678530673958L));

        // then
        assertThat(user.getLastLoginAt()).isEqualTo(1678530673958L);
    }

    @Test
    public void 유효한_인증_코드로_계정을_활성화_할_수_있다() {
        // given
        User user = User.builder()
                .id(1L)
                .email("tksl4732@gmail.com")
                .nickname("tksl4732")
                .address("Daejeon")
                .status(UserStatus.ACTIVE)
                .lastLoginAt(100L)
                .certificationCode("aaa-aaa-aaa")
                .build();

        // when
        user = user.certificate("aaa-aaa-aaa");

        // then
        assertThat(user.getStatus()).isEqualTo(UserStatus.ACTIVE);
    }

    @Test
    public void 잘못된_인증_코드로_계정을_활성화_하려하면_에러를_던진다() {
        // given
        User user = User.builder()
                .id(1L)
                .email("tksl4732@gmail.com")
                .nickname("tksl4732")
                .address("Daejeon")
                .status(UserStatus.ACTIVE)
                .lastLoginAt(100L)
                .certificationCode("aaa-aaa-aaa")
                .build();

        // when

        // then
        assertThatThrownBy(() -> user.certificate("aaa-aaa-aaa"))
                .isInstanceOf(CertificationCodeNotMatchedException.class);
    }
}
