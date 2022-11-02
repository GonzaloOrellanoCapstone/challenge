package com.tenpo.challenge.service;

import com.tenpo.challenge.model.User;
import com.tenpo.challenge.repository.RefreshTokenRepository;
import com.tenpo.challenge.repository.UserRepository;
import com.tenpo.challenge.security.service.UserDetailsServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl customUserDetailsService;
    @Mock
    private RefreshTokenRepository refreshTokenRepository;
    @Mock
    private UserRepository userRepository;

    @Test(expected = NullPointerException.class)
    public void testLoadUserByUsernameException() {
        final String username = "existingUserName";
        final User user = mock(User.class);
        when(userRepository.findByUsername(username)).thenReturn(Optional.ofNullable(user));

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername("gonzalo");
    }

}
