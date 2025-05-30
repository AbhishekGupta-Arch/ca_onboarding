package com.cloudcounselage.ca_onboarding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CaController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private CommunityAmbassadorRepository repository;

    @Autowired
    private UtmLinkRepository utmLinkRepository;

    @PostMapping("/api/ca/onboard")
    public String onboard(@RequestBody CommunityAmbassador ambassador) {
        if (ambassador.getName() == null || ambassador.getEmail() == null) {
            return "Error: Name or email cannot be null";
        }
        try {
            CommunityAmbassador savedAmbassador = repository.save(ambassador);
            String utmLink = "https://www.industryacademiacommunity.com/?utm_source=ambassador&utm_medium=email&utm_campaign=" + savedAmbassador.getId();

            UtmLink link = new UtmLink();
            link.setLink(utmLink);
            link.setAmbassadorId(savedAmbassador.getId());
            link.setClickCount(0);
            utmLinkRepository.save(link);

            emailService.sendWelcomeEmail(ambassador.getEmail(), ambassador.getName(), utmLink);
            return "CA onboarded successfully! UTM link sent to " + ambassador.getEmail();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}