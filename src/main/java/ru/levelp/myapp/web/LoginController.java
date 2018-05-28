package ru.levelp.myapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage(HttpSession session) {
        if (session.getAttribute("userName") != null) {
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginForm(@RequestParam String user,
                            @RequestParam String password,
                            HttpSession session,
                            ModelMap model) {
        if (session.getAttribute("userName") != null) {
            return "redirect:/";
        }

        if (user.equals(password)) {
            session.setAttribute("userName", user);
            return "redirect:/";
        }

        model.addAttribute("error", "Invaliad login or password");
        return "login";
    }

    @GetMapping("/logout")
    public String logout (HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

}
