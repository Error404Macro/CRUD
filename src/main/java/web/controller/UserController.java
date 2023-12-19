package web.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDAO;
import web.models.User;

import javax.validation.Valid;


@Controller
@RequestMapping("/users")
public class UserController {

	private final UserDAO userDAO;

	@Autowired
	public UserController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@GetMapping()
	public String index(Model model) {
		model.addAttribute("users", userDAO.index());
		return "index";
	}

	@GetMapping("/show")
	public String show(@RequestParam("id") int id, Model model) {
		model.addAttribute("user", userDAO.show(id));
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

		userDAO.save(user);
		return "redirect:/users";
	}

	@GetMapping("/edit")
	public String edit(Model model, @RequestParam("id") int id) {
		model.addAttribute("user", userDAO.show(id));
		return "edit";
	}

	@PatchMapping()
	public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
						 @RequestParam("id") int id) {
		if (bindingResult.hasErrors()) {
			return "edit";
		}

		userDAO.update(id, user);
		return "redirect:/users";
	}

	@DeleteMapping()
	public String delete(@RequestParam("id") int id) {
		userDAO.delete(id);
		return "redirect:/users";
	}
}