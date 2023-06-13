import db.caseStudy.services.WagonSectionIndicatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WagonSectionIndicatorServiceTest {

    private WagonSectionIndicatorService wagonSectionIndicatorService;

    @BeforeEach
    void setUp() {
        wagonSectionIndicatorService = new WagonSectionIndicatorService();
    }

    @Test
    void testGetWagonSections() {
        String ril100 = "FF";
        int trainNumber = 2310;
        int wagonNumber = 10;
        List<String> wagonSections = wagonSectionIndicatorService.getWagonSections(ril100, trainNumber, wagonNumber);
        assertNotNull(wagonSections);
        assertFalse(wagonSections.isEmpty());
    }
}
