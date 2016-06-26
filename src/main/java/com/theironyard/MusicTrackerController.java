package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

@Controller
public class MusicTrackerController {
    @Autowired
    UserRepository users;

    @Autowired
    AlbumRepository albums;

    @PostConstruct
    public void init() throws Exception {
        if (users.count() == 0) {
            User user = new User("Jon", PasswordStorage.createHash("pass"));
            users.save(user);
        }
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(HttpSession session, Model model,  String search) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "login";
        }
        return "home";
    }


    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(String username, String password, HttpSession session) throws Exception {
        User user = users.findByName(username);
        if (user == null) {
            user = new User(username, PasswordStorage.createHash(password));
            users.save(user);
        } else if (!PasswordStorage.verifyPassword(password, user.password)) {
            throw new Exception("Wrong password!");
        }
        session.setAttribute("username", username);
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(path = "/add-album", method = RequestMethod.POST)
    public String add(HttpSession session, String artist, String albumName, String genre, int rating, int releaseDate) {
        String username = (String) session.getAttribute("username");
        User user = users.findByName(username);
        Album a = new Album(artist, albumName, genre, rating, releaseDate);
        albums.save(a);
        return "redirect:/";
    }

    @RequestMapping(path = "/delete-album", method = RequestMethod.POST)
    public String delete(int id) {
        albums.delete(id);
        return "redirect:/";
    }
}





