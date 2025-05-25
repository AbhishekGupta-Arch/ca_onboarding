package com.cloudcounselage.ca_onboarding;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UtmLinkRepository extends JpaRepository<UtmLink, Long> {
    UtmLink findByLink(String link);
    UtmLink findByAmbassadorId(Long ambassadorId);
}