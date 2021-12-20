package org.o7planning.sbcrudrestful.controller;

import java.util.List;

import org.o7planning.sbcrudrestful.dao.UserDAO;
import org.o7planning.sbcrudrestful.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class MainRESTController {

    @Autowired
    private UserDAO userDAO;

    @RequestMapping("/")
    @ResponseBody
    public String welcome() {
        return "Welcome to RestTuserlate Example.";
    }

    // URL:
    // http://localhost:8080/SomeContextPath/users
    // http://localhost:8080/SomeContextPath/users.xml
    // http://localhost:8080/SomeContextPath/users.json
    @RequestMapping(value = "/users", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<User> getUsers() {
        List<User> list = userDAO.getAllUsers();
        return list;
    }

    // URL:
    // http://localhost:8080/SomeContextPath/user/{userNo}
    // http://localhost:8080/SomeContextPath/user/{userNo}.xml
    // http://localhost:8080/SomeContextPath/user/{userNo}.json
    @RequestMapping(value = "/user/{userNo}", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public User getUser(@PathVariable("userNo") String userNo) {
        return userDAO.getUser(userNo);
    }

    // URL:
    // http://localhost:8080/SomeContextPath/user
    // http://localhost:8080/SomeContextPath/user.xml
    // http://localhost:8080/SomeContextPath/user.json

    @RequestMapping(value = "/user", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public User addUser(@RequestBody User user) {

        System.out.println("(Service Side) Creating user: " + user.getUserNo());

        return userDAO.addUser(user);
    }

    // URL:
    // http://localhost:8080/SomeContextPath/user
    // http://localhost:8080/SomeContextPath/user.xml
    // http://localhost:8080/SomeContextPath/user.json
    @RequestMapping(value = "/user", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public User updateUser(@RequestBody User user) {

        System.out.println("(Service Side) Editing user: " + user.getUserNo());
        return userDAO.updateUser(user);
    }

    // URL:
    // http://localhost:8080/SomeContextPath/user/{userNo}
    @RequestMapping(value = "/user/{userNo}", //
            method = RequestMethod.DELETE, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public void deleteUser(@PathVariable("userNo") String userNo) {

        System.out.println("(Service Side) Deleting user: " + userNo);

        userDAO.deleteUser(userNo);
    }

}