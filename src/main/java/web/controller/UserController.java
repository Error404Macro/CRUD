package web.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.Service.UserService;
import web.Service.UserServiceImpl;
import web.models.User;

import javax.validation.Valid;


@Controller
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping()
	public String index(Model model) {
		model.addAttribute("users", userService.getAllUsers());
		return "index";
	}

	@GetMapping("/show")
	public String show(@RequestParam("id") int id, Model model) {
		model.addAttribute("user", userService.getUserById(id));
		return "show";
	}

	@GetMapping("/new")
	public String newUser(Model model) {
		model.addAttribute("user", new User());
		return "new";
	}

	@PostMapping()
	public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "new";
		}

		userService.saveUser(user);
		return "redirect:/users";
	}

	@GetMapping("/edit")
	public String edit(Model model, @RequestParam("id") int id) {
		model.addAttribute("user", userService.getUserById(id));
		return "edit";
	}

	@PatchMapping()
	public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
						 @RequestParam("id") int id) {
		if (bindingResult.hasErrors()) {
			return "edit";
		}

		userService.updateUser(id, user);
		return "redirect:/users";
	}

	@DeleteMapping()
	public String delete(@RequestParam("id") int id) {
		userService.deleteUser(id);
		return "redirect:/users";
	}
}