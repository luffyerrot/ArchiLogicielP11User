package fr.pierre.medheadUser.service;

import fr.pierre.medheadUser.service.jwtoken.JwtService;
import fr.pierre.medheadUser.utils.MockUserDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class JwtokenServiceTest {

    @InjectMocks
    private JwtService jwtService;

    /*@Test
    public void getExpirationTime_expectToBe20() {
        final long expiration = jwtService.getExpirationTime();
        Assertions.assertEquals(20, expiration);
    }

    @Test
    public void generateToken_expectToBeOk_ReturnString() {
        // Given
        final UserDetails userDetails = new MockUserDetail("username", "password", "authority");

        // When
        String response = jwtService.generateToken(userDetails);

        // Then
        Assertions.assertNotNull(response);
        Assertions.assertEquals("token", response);
    }*/
}
