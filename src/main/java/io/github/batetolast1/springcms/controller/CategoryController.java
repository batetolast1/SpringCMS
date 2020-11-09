package io.github.batetolast1.springcms.controller;

import io.github.batetolast1.springcms.dto.CategoryDto;
import io.github.batetolast1.springcms.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/list")
    public String list(Model model) {
        List<CategoryDto> categoryDtos = categoryService.getAll();
        model.addAttribute("categoryDtos", categoryDtos);
        return "category/list";
    }

    @GetMapping("/confirm-delete")
    public String delete(Model model, @RequestParam(name = "id") Long id) {
        model.addAttribute("id", id);
        return "category/delete";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(name = "id") Long id) {
        categoryService.delete(id);
        return "redirect:/category/list";
    }

    @GetMapping("/add")
    public String form(Model model) {
        model.addAttribute("categoryDto", new CategoryDto());
        return "category/add";
    }

    @PostMapping("/add")
    public String processForm(CategoryDto categoryDto) {
        categoryService.save(categoryDto);
        return "redirect:/category/list";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam(name = "id") Long id) {
        CategoryDto categoryDto = categoryService.getById(id);
        model.addAttribute("categoryDto", categoryDto);
        return "category/edit";
    }

    @PostMapping("/edit")
    public String processEdit(CategoryDto categoryDto) {
        categoryService.edit(categoryDto);
        return "redirect:/category/list";
    }
}
