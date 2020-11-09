package io.github.batetolast1.springcms.controller;

import io.github.batetolast1.springcms.dto.AuthorDto;
import io.github.batetolast1.springcms.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/list")
    public String list(Model model) {
        Set<AuthorDto> authorDtos = authorService.getAll();
        model.addAttribute("authorDtos", authorDtos);
        return "author/list";
    }

    @GetMapping("/confirm-delete")
    public String delete(Model model, @RequestParam(name = "id") Long id) {
        model.addAttribute("id", id);
        return "author/delete";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(name = "id") Long id) {
        authorService.delete(id);
        return "redirect:/author/list";
    }

    @GetMapping("/add")
    public String form(Model model) {
        model.addAttribute("authorDto", new AuthorDto());
        return "author/add";
    }

    @PostMapping("/add")
    public String processForm(AuthorDto authorDto) {
        authorService.save(authorDto);
        return "redirect:/author/list";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam(name = "id") Long id) {
        AuthorDto authorDto = authorService.getById(id);
        model.addAttribute("authorDto", authorDto);
        return "author/edit";
    }

    @PostMapping("/edit")
    public String processEdit(AuthorDto authorDto) {
        authorService.edit(authorDto);
        return "redirect:/author/list";
    }
}
