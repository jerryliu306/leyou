package com.leyou.common.advice.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.leyou.common.utils.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class JsonUtilsTest {

    static String json;

    @Test
    public void toStringTest() {
        json = JsonUtils.toString(Arrays.asList(new User("Rose", 18),
                new User("Jack", 21)));
        System.out.println("json = " + json);
    }

    @Test
    public void toBeanTest() {
        json = "{\"name\":\"Rose\",\"age\":18}";
        User user = JsonUtils.toBean(json, User.class);
        System.out.println("user = " + user);
    }

    @Test
    public void toListTest() {
        json = "[{\"name\":\"Rose\",\"age\":18},{\"name\":\"Jack\",\"age\":21}]";
        List<User> users = JsonUtils.toList(json, User.class);
        System.out.println("users = " + users);
    }

    @Test
    public void toMapTest() {
        json = "{\"name\":\"Rose\",\"age\":18}";
        Map<String, Object> map = JsonUtils.toMap(json, String.class, Object.class);
        System.out.println("map = " + map);
    }

    @Test
    public void nativeReadTest() {
        json = "{\"heima52\": [{\"name\":\"Rose\",\"age\":18},{\"name\":\"Jack\",\"age\":21}],\"heima54\": [{\"name\":\"Rose\",\"age\":18},{\"name\":\"Jack\",\"age\":21}]}";
        Map<String, List<User>> stringListMap = JsonUtils.nativeRead(json,
                new TypeReference<Map<String, List<User>>>() {
                });
        System.out.println("stringListMap = " + stringListMap);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class User {
        String name;
        int age;
    }
}