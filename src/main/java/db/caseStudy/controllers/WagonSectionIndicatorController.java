package db.caseStudy.controllers;

import db.caseStudy.services.WagonSectionIndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;

@RestController
public class WagonSectionIndicatorController {

    @Autowired
    private WagonSectionIndicatorService wagonSectionIndicatorService;

    // This method handles the GET request for retrieving the waggon sections.
    @GetMapping("/station/{ril100}/train/{trainNumber}/waggon/{number}")
    public ResponseEntity<?> identifyWaggonSections(
            @PathVariable("ril100") String ril100,
            @PathVariable("trainNumber") int trainNumber,
            @PathVariable("number") int number
    ) {
        List<String> sections = wagonSectionIndicatorService.getWagonSections(ril100, trainNumber, number);
        HashMap<String, List<String>> sectionsMap = new HashMap<String, List<String>>();
        sectionsMap.put("sections", sections);
        return ResponseEntity.ok(sectionsMap);
    }
}