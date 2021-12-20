package org.o7planning.sbcrudrestful.dao;
 

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.o7planning.sbcrudrestful.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

    private static final Map<String, User> userMap = new HashMap<String, User>();

    static {
        initUsers();
    }

    private static void initUsers() {
        User user1 = new User("E01", "Smith", "Clerk");
        User user2 = new User("E02", "Allen", "Salesman");
        User user3 = new User("E03", "Jones", "Manager");

        userMap.put(user1.getUserNo(), user1);
        userMap.put(user2.getUserNo(), user2);
        userMap.put(user3.getUserNo(), user3);
    }

    public User getUser(String userNo) {
        return userMap.get(userNo);
    }

    public User addUser(User user) {
        userMap.put(user.getUserNo(), user);
        return user;
    }

    public User updateUser(User user) {
        userMap.put(user.getUserNo(), user);
        return user;
    }

    public void deleteUser(String userNo) {
        userMap.remove(userNo);
    }

    public List<User> getAllUsers() {
        Collection<User> c = userMap.values();
        List<User> list = new ArrayList<User>();
        list.addAll(c);
        return list;
    }

}