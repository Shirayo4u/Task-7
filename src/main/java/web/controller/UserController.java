package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add";
    }

    @PostMapping
    public String saveUser(@ModelAttribute("user") User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "add";

        }if (user.getId() == 0){
            userService.addUser(user);
        } else {
            userService.updateUser(user);
        }
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable(value = "id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "show";
    }


    @GetMapping()
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "list";
    }

    @GetMapping("/update/{id}")
    public String updateUsers(@PathVariable(value = "id") int id, Model model) {
        User user = userService.getUserById(id);
        if (user == null) {
            return "redirect:/users";
        }
        model.addAttribute("user", user);
        return "edit";

    }


    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable(value = "id") int id) {
        userService.deleteUser(id);
        return "redirect:/users";

    }

}