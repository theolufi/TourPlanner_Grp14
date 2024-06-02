package service;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapQuestManagerTest {

    @Test
    void testErrorRequestRoute() {
        String resp = MapQuestManager.requestRoute("10", "20");
        JSONObject routeObj = new JSONObject(resp);
        assertNotEquals(null, routeObj.getJSONObject("route").getJSONObject("routeError"));
    }

    @Test
    void testGetRouteDistance() {
        assertEquals("697.7911", MapQuestManager.getRouteDistance("Shkoder", "Vienna"));
        assertNotEquals("500.0", MapQuestManager.getRouteDistance("koplik", "shkoder"));
    }
}