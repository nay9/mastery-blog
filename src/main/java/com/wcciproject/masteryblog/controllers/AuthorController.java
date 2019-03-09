package com.wcciproject.masteryblog.controllers;

import javax.annotation.Resource;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wcciproject.masteryblog.models.Author;
import com.wcciproject.masteryblog.repositories.AuthorRepository;
import com.wcciproject.masteryblog.repositories.GenreRepository;
import com.wcciproject.masteryblog.repositories.PostRepository;
import com.wcciproject.masteryblog.repositories.TagRepository;

@Controller
@RequestMapping("/author")
public class AuthorController {
	@Id
	@GeneratedValue
	private Long id;

	@Resource
	AuthorRepository authorRepo;
	@Resource
	GenreRepository genreRepo;
	@Resource
	PostRepository postRepo;
	@Resource
	TagRepository tagRepo;

	@RequestMapping("")
	public String author(Model model) {
		model.addAttribute("authorList", authorRepo.findAll());
		return "/author";
	}
	
	
	@PostMapping("/submit")
	public String authorSubmit(String authorName) {
		authorRepo.save(new Author(authorName));
		return "redirect:/author";
		
	}

	@GetMapping("/{id}")
	public String singleAuthor(@PathVariable Long id, Model model) {
		model.addAttribute("author", authorRepo.findById(id).get());
		model.addAttribute("posts", postRepo.findAll());
		return "redirect:/singleAuthor";
	}

//	@PostMapping("/{id}")
//	public String addAuthor(@PathVariable Long authorId, Author author) {
//		Author review1 = authorRepo.findById(authorId).get();
//		authorRepo.save(new Author(authorId));
//		return "redirect:/authors/" + authorId;
//	}
}
