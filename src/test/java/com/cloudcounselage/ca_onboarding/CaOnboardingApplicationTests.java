package com.cloudcounselage.ca_onboarding;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class CaOnboardingApplicationTests {

	@MockBean
	private EmailService emailService;

	@Test
	void contextLoads() {
	}
}