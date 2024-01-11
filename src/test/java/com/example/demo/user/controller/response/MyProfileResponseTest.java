package com.example.demo.user.controller.response;

import com.example.user.controller.response.MyProfileResponse;
import com.example.user.domain.User;
import com.example.user.domain.UserStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class MyProfileResponseTest {

    @Test
    public void User으로_응답을_생성할_수_있다() {
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
        MyProfileResponse myProfileResponse = MyProfileResponse.from(user);

        // then
        assertThat(myProfileResponse.getId()).isEqualTo(1);
        assertThat(myProfileResponse.getEmail()).isEqualTo("tksl4732@gmail.com");
        assertThat(myProfileResponse.getAddress()).isEqualTo("Daejeon");
        assertThat(myProfileResponse.getStatus()).isEqualTo(UserStatus.ACTIVE);
        assertThat(myProfileResponse.getLastLoginAt()).isEqualTo(100L);
    }
}
