package io.github.batetolast1.springcms.controller;

import io.github.batetolast1.springcms.dto.AuthorDto;
import io.github.batetolast1.springcms.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {

    public static final String LIST = "author/list";
    public static final String LIST_REDIRECT = "redirect:/author/list";
    public static final String DELETE = "author/delete";
    public static final String ADD = "author/add";
    public static final String EDIT = "author/edit";

    private final AuthorService authorService;

    @GetMapping("/list")
    public String displayList(Model model) {
        List<AuthorDto> authorDtos = authorService.getAll();
        model.addAttribute("authorDtos", authorDtos);
        return LIST;
    }

    @GetMapping("/confirm-delete")
    public String confirmDelete(Model model, @RequestParam(name = "id") Long id) {
        model.addAttribute("id", id);
        return DELETE;
    }

    @GetMapping("/delete")
    public String processDelete(@RequestParam(name = "id") Long id) {
        authorService.delete(id);
        return LIST_REDIRECT;
    }

    @GetMapping("/add")
    public String displayAddForm(Model model) {
        model.addAttribute("authorDto", new AuthorDto());
        return ADD;
    }

    @PostMapping("/add")
    public String processAddForm(@Valid AuthorDto authorDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ADD;
        }

        authorService.save(authorDto);
        return LIST_REDIRECT;
    }

    @GetMapping("/edit")
    public String displayEditForm(Model model, @RequestParam(name = "id") Long id) {
        AuthorDto authorDto = authorService.getById(id);
        model.addAttribute("authorDto", authorDto);
        return EDIT;
    }

    @PostMapping("/edit")
    public String processEditForm(@Valid AuthorDto authorDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return EDIT;
        }

        authorService.edit(authorDto);
        return LIST_REDIRECT;
    }
}
