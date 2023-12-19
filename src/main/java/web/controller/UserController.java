package web.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDAO;
import web.models.User;


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

	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		model.addAttribute("user", userDAO.show(id));
		return "show";
	}
	@GetMapping("/new")
	public String newUser(@ModelAttribute("user") User user) {
		return "new";
	}

	@PostMapping()
	public String create(@ModelAttribute("user") @Validated User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "new";
		}

		userDAO.save(user);
		return "redirect:/users";
	}
	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("user", userDAO.show(id));
		return "edit";
	}

	@PatchMapping("/{id}")
	public String update(@ModelAttribute("user") @Validated User user, BindingResult bindingResult,
						 @PathVariable("id") int id) {
		if (bindingResult.hasErrors()) {
			return "edit";
		}

		userDAO.update(id, user);
		return "redirect:/users";
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		userDAO.delete(id);
		return "redirect:/users";
	}
	
}