package jm.task.core.jdbc.controller;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public String helloPage() {
//		userService.dropTable();
//		userService.createTable();
		return "/mainpage";
	}

	@GetMapping("/all")
	public String allUsers(Model model) {
		model.addAttribute("allUsers", userService.getAllUsers());
		userService.getAllUsers().forEach(User::toString);
		return "users";
	}

	@GetMapping("/{id}")
	public String getEdit(Model model, @PathVariable Long id) {
		User user = userService.findUserById(id);
		System.out.println(user.toString());
		System.out.println("model" + model);
		model.addAttribute("user", user);
		return "/edit";
	}

	@GetMapping("/new")
	public String openPageNew(@ModelAttribute("user") User user){
		return "/create";
	}

	@PostMapping
	public String createNewUser(@ModelAttribute("user") User user){
		System.out.println("создался" + user.toString());
		userService.create(user);
		userService.getAllUsers().forEach(User::toString);
		return "redirect:user/all";
	}

	@PostMapping("/{id}")
	public String postEdit(@ModelAttribute() User user) {
		userService.update(user);
		return "redirect:all";
	}

	@DeleteMapping("/{id}")
	public String deleteUserById(@PathVariable Long id) {
		userService.delete(id);
		return "redirect:all";
	}
}