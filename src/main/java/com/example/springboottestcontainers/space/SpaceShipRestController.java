package com.example.springboottestcontainers.space;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/space")
@RequiredArgsConstructor
@Profile("redis")
public class SpaceShipRestController {
    public static String CACHE_NAME = "shipcache";
    private final CacheManager cacheManager;

    //    private final RedisTemplate<String, Object> redisTemplate;
    @GetMapping("/all")
    public List<SpaceShip> allShips() {
        Cache cache = cacheManager.getCache(CACHE_NAME);
        return cache.get("ships", List.class);
    }

    @PostMapping("/")
    public List<SpaceShip> newShip(String captainName) {
        Cache cache = cacheManager.getCache(CACHE_NAME);
        List<SpaceShip> ships = (List<SpaceShip>) cache.get("ships", List.class);
        if (ships == null)
            ships = new ArrayList<>();
        ships.add(SpaceShip.builder().fuelLeft(88).model("Round").captain(captainName).build());
        cache.put("ships", ships);
        return allShips();
    }
}
