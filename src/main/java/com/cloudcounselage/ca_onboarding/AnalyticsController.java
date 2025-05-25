package com.cloudcounselage.ca_onboarding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AnalyticsController {

    @Autowired
    private UtmLinkRepository utmLinkRepository;

    @Autowired
    private CommunityAmbassadorRepository repository;

    @GetMapping("/api/utm/track")
    @Transactional
    public ResponseEntity<Void> trackUtmLink(@RequestParam String utmLink) {
        System.out.println("Received UTM Link: " + utmLink);
        UtmLink link = utmLinkRepository.findByLink(utmLink);
        if (link == null) {
            System.out.println("UTM Link not found in database");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        link.setClickCount(link.getClickCount() + 1);
        utmLinkRepository.save(link);
        System.out.println("Updated Click Count: " + link.getClickCount());
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create("https://www.industryacademiacommunity.com/"))
                .build();
    }

    @GetMapping("/api/ca/performance")
    public List<PerformanceData> getPerformanceDashboard() {
        List<PerformanceData> performanceData = new ArrayList<>();
        List<CommunityAmbassador> ambassadors = repository.findAll();
        for (CommunityAmbassador ambassador : ambassadors) {
            UtmLink link = utmLinkRepository.findByAmbassadorId(ambassador.getId());
            System.out.println("Ambassador ID: " + ambassador.getId() + ", UTM Link: " + (link != null ? link.getLink() : "Not found"));
            if (link != null) {
                PerformanceData data = new PerformanceData();
                data.setAmbassadorName(ambassador.getName());
                data.setAmbassadorEmail(ambassador.getEmail());
                data.setUtmLink(link.getLink());
                data.setClickCount(link.getClickCount());
                performanceData.add(data);
            }
        }
        return performanceData;
    }

    public static class PerformanceData {
        private String ambassadorName;
        private String ambassadorEmail;
        private String utmLink;
        private int clickCount;

        public String getAmbassadorName() { return ambassadorName; }
        public void setAmbassadorName(String ambassadorName) { this.ambassadorName = ambassadorName; }
        public String getAmbassadorEmail() { return ambassadorEmail; }
        public void setAmbassadorEmail(String ambassadorEmail) { this.ambassadorEmail = ambassadorEmail; }
        public String getUtmLink() { return utmLink; }
        public void setUtmLink(String utmLink) { this.utmLink = utmLink; }
        public int getClickCount() { return clickCount; }
        public void setClickCount(int clickCount) { this.clickCount = clickCount; }
    }
}