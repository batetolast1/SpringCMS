package io.github.batetolast1.springcms.controller;

import io.github.batetolast1.springcms.dto.ArticleDto;
import io.github.batetolast1.springcms.dto.AuthorDto;
import io.github.batetolast1.springcms.dto.CategoryDto;
import io.github.batetolast1.springcms.service.ArticleService;
import io.github.batetolast1.springcms.service.AuthorService;
import io.github.batetolast1.springcms.service.CategoryService;
import io.github.batetolast1.springcms.validation.groups.ArticleData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    public static final String LIST = "article/list";
    public static final String LIST_REDIRECT = "redirect:/article/list";
    public static final String DELETE = "article/delete";
    public static final String ADD = "article/add";
    public static final String EDIT = "article/edit";

    private final ArticleService articleService;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @ModelAttribute("authorDtos")
    public List<AuthorDto> authorDtos() {
        return authorService.getAll();
    }

    @ModelAttribute("categoryDtos")
    public List<CategoryDto> categoryDtos() {
        return categoryService.getAll();
    }

    @GetMapping("/list")
    public String displayList(Model model) {
        List<ArticleDto> articleDtos = articleService.getAll();
        model.addAttribute("articleDtos", articleDtos);
        return LIST;
    }

    @GetMapping("/confirm-delete")
    public String confirmDelete(Model model, @RequestParam(name = "id") Long id) {
        model.addAttribute("id", id);
        return DELETE;
    }

    @GetMapping("/delete")
    public String processDelete(@RequestParam(name = "id") Long id) {
        articleService.delete(id);
        return LIST_REDIRECT;
    }

    @GetMapping("/add")
    public String displayAddForm(Model model) {
        model.addAttribute("articleDto", new ArticleDto());
        return ADD;
    }

    @PostMapping("/add")
    public String processAddForm(@Validated(ArticleData.class) ArticleDto articleDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ADD;
        }

        articleService.save(articleDto);
        return LIST_REDIRECT;
    }

    @GetMapping("/edit")
    public String displayEditForm(Model model, @RequestParam(name = "id") Long id) {
        ArticleDto articleDto = articleService.getById(id);

        if (articleDto != null) {
            model.addAttribute("articleDto", articleDto);
            return EDIT;
        }

        return LIST;
    }

    @PostMapping("/edit")
    public String processEditForm(@Validated(ArticleData.class) ArticleDto articleDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return EDIT;
        }

        articleService.edit(articleDto);
        return LIST_REDIRECT;
    }
}
