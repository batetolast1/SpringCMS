package io.github.batetolast1.springcms.controller;

import io.github.batetolast1.springcms.dto.ArticleDto;
import io.github.batetolast1.springcms.dto.CategoryDto;
import io.github.batetolast1.springcms.service.ArticleService;
import io.github.batetolast1.springcms.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomePageController {

    public static final String INDEX = "index";
    public static final String INDEX_REDIRECT = "redirect:/";
    public static final String LIST_BY_CATEGORY = "articlesByCategory";

    private final ArticleService articleService;
    private final CategoryService categoryService;

    @GetMapping
    public String home(Model model) {
        List<ArticleDto> articleDtos = articleService.getLastArticles();
        model.addAttribute("articleDtos", articleDtos);
        return INDEX;
    }

    @GetMapping("/articles-by-category")
    public String displayListByCategory(@RequestParam Long id, Model model) {
        CategoryDto categoryDto = categoryService.getById(id);

        if (categoryDto == null) {
            return INDEX_REDIRECT;
        }

        model.addAttribute("categoryDto", categoryDto);

        List<ArticleDto> articleDtosByCategory = articleService.getAllByCategoryId(id);
        model.addAttribute("articleDtosByCategory", articleDtosByCategory);
        return LIST_BY_CATEGORY;
    }
}
