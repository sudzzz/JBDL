package com.example.demoredis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.time.Duration;
import java.util.List;
import java.util.Map;



@RestController
public class PersonController {

    private static final String PERSON_KEY_PREFIX = "per::";
    private static final String PERSON_LIST_KEY = "per_list";
    private static final String PERSON_HASH_KEY_PREFIX = "per_hash::";

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    ObjectMapper objectMapper;

    // ------------ String ops -------------------
    /**
     * Key - String
     * Value - Person
     */

    @PostMapping("/string/person")
    public void savePerson(@RequestBody Person person){
        if(person.getId() == 0){
            return;
        }
        String key = getKey(person.getId());
        redisTemplate.opsForValue().set(key, person);
    }

    @GetMapping("/string/person")
    public Person getPerson(@RequestParam("id") long id){
        String key = getKey(id);
        return (Person) redisTemplate.opsForValue().get(key);
    }

    private String getKey(long id){
        return PERSON_KEY_PREFIX + id;
    }

    // ----------- List ops -----------------------
    /**
     * Key - String
     * Value - List<Person>
     */

    @PostMapping("/lpush/person")
    public void lpush(@RequestBody List<Person> person){
        redisTemplate.opsForList().leftPushAll(PERSON_LIST_KEY, person);
    }

    @PostMapping("/rpush/person")
    public void rpush(@RequestBody List<Person> person){
        redisTemplate.opsForList().rightPushAll(PERSON_LIST_KEY, person);
    }

    @DeleteMapping("/lpop/person")
    public List<Person> lpop(@RequestParam(value = "count", required = false, defaultValue = "1") int count){
        return redisTemplate.opsForList()
                .leftPop(PERSON_LIST_KEY, count)
                .stream()
                .map(x -> (Person)x)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/rpop/person")
    public List<Person> rpop(@RequestParam(value = "count", required = false, defaultValue = "1") int count){
        return redisTemplate.opsForList()
                .rightPop(PERSON_LIST_KEY, count)
                .stream()
                .map(x -> (Person)x)
                .collect(Collectors.toList());
    }

    @GetMapping("/lrange/person")
    public List<Person> lrange(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
                               @RequestParam(value = "end", required = false, defaultValue = "-1") int end){
        return redisTemplate.opsForList()
                .range(PERSON_LIST_KEY, start, end)
                .stream()
                .map(x -> (Person) x)
                .collect(Collectors.toList());
    }


    // --------------- Set ops -------------------



    // ------------- Hash Ops ---------------------

    private String getHashKey(long id){
        return PERSON_HASH_KEY_PREFIX + id;
    }

    @PostMapping("/hash/person")
    public void savePersonInHash(@RequestBody List<Person> people) {

        people.stream()
                .filter(person -> person.getId() != 0)
                .forEach(person -> {
                    Map map = objectMapper.convertValue(person, Map.class);
                    redisTemplate.opsForHash().putAll(getHashKey(person.getId()), map);
                    redisTemplate.expire(getHashKey(person.getId()), Duration.ofHours(24));
                });

    }

    @GetMapping("/hash/person/all")
    public List<Person> getPeople(@RequestParam("ids") List<Long> peopleIds){

        return peopleIds.stream()
                .map(i -> redisTemplate.opsForHash().entries(getHashKey(i)))
                .map(entryMap -> objectMapper.convertValue(entryMap, Person.class))
                .collect(Collectors.toList());
    }

    /*
            p1 <- Person from FE
            k1 <- redis key
            {
                f1 <- 'age'
                v1 <- p1.age
                .
                .
                .
            }
     */

}
