package io.github.batetolast1.springcms.controller;

import io.github.batetolast1.springcms.dto.ArticleDto;
import io.github.batetolast1.springcms.dto.AuthorDto;
import io.github.batetolast1.springcms.dto.CategoryDto;
import io.github.batetolast1.springcms.service.ArticleService;
import io.github.batetolast1.springcms.service.AuthorService;
import io.github.batetolast1.springcms.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @ModelAttribute("authorDtos")
    public Set<AuthorDto> authorDtos() {
        return authorService.getAll();
    }

    @ModelAttribute("categoryDtos")
    public Set<CategoryDto> categoryDtos() {
        return categoryService.getAll();
    }

    @GetMapping("/list")
    public String list(Model model) {
        Set<ArticleDto> articleDtos = articleService.getAll();
        model.addAttribute("articleDtos", articleDtos);
        return "article/list";
    }

    @GetMapping("/confirm-delete")
    public String delete(Model model, @RequestParam(name = "id") Long id) {
        model.addAttribute("id", id);
        return "article/delete";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(name = "id") Long id) {
        articleService.delete(id);
        return "redirect:/article/list";
    }

    @GetMapping("/add")
    public String form(Model model) {
        model.addAttribute("articleDto", new ArticleDto());
        return "article/add";
    }

    @PostMapping("/add")
    public String processForm(ArticleDto articleDto) {
        articleService.save(articleDto);
        return "redirect:/article/list";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam(name = "id") Long id) {
        ArticleDto articleDto = articleService.getById(id);
        model.addAttribute("articleDto", articleDto);
        return "article/edit";
    }

    @PostMapping("/edit")
    public String processEdit(ArticleDto articleDto) {
        articleService.edit(articleDto);
        return "redirect:/article/list";
    }
}
