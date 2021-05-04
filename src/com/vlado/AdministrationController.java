package com.vlado;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vlado.dao.CategoryDAO;
import com.vlado.dao.CommentDAO;
import com.vlado.dao.MessageDAO;
import com.vlado.dao.PostDAO;
import com.vlado.dao.SlideDAO;
import com.vlado.dao.TagDAO;
import com.vlado.dao.UserDAO;
import com.vlado.entity.Category;
import com.vlado.entity.Post;
import com.vlado.entity.Slide;
import com.vlado.entity.Tag;
import com.vlado.entity.User;

@Controller
@RequestMapping("/administration")
public class AdministrationController {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private TagDAO tagDAO;
	
	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	private MessageDAO messageDAO;
	
	@Autowired
	private SlideDAO slideDAO;
	
	@RequestMapping("/category-list")
	public String getCategoryList(Model model, Principal principal) {
		
		model.addAttribute("user", userDAO.getUserByUsername(principal.getName()));
		model.addAttribute("categoryList", categoryDAO.getCategoryList());
		return "category-list";
	}
	
	@RequestMapping("/category-form")
	public String getCategoryForm(Model model, Principal principal) {
		
		model.addAttribute("user", userDAO.getUserByUsername(principal.getName()));
		model.addAttribute("category", new Category());
		return "category-form";
	}
	
	@RequestMapping("/category-save")
	public String saveCategory(@Valid @ModelAttribute Category category, BindingResult results, Model model) {
		
		if(results.hasErrors()) {
			model.addAttribute("category", category);
			return "category-form";
		}
		
		categoryDAO.saveCategory(category);
		return "redirect:/administration/category-list";		
	}
	
	@RequestMapping("/category-update-form")
	public String updateCategory(@RequestParam int id, Model model, Principal principal) {		
		Category category = categoryDAO.getCategory(id);
		model.addAttribute("category", category);
		model.addAttribute("user", userDAO.getUserByUsername(principal.getName()));
		
		return "category-form";		
	}
	
	@RequestMapping("/category-delete")
	public String updateCategory(@RequestParam int id) {
		categoryDAO.deleteCategory(id);		
		return "redirect:/administration/category-list";		
	}
	
	//POST ------------------------------------------------
	
	@RequestMapping("/post-list")
	public String getPostList(HttpServletRequest request, Model model, Principal principal) {		
		
		model.addAttribute("categories", categoryDAO.getCategoryList());
		model.addAttribute("user", userDAO.getUserByUsername(principal.getName()));
		
		if(request.isUserInRole("ROLE_admin")) {
			model.addAttribute("posts", postDAO.getPostList());
			return "post-list";
		}
		else
			model.addAttribute("posts", postDAO.getPostsByUsername(principal.getName()));
			return "bloger-post-list";
	}
	
	@RequestMapping("/search-post-list")
	public String getSearchPostList(@RequestParam String title, @RequestParam int id, @RequestParam String author, Model model, Principal principal) {
		
		model.addAttribute("posts", postDAO.searchPost(title, id, author));
		model.addAttribute("categories", categoryDAO.getCategoryList());
		model.addAttribute("title", title);
		model.addAttribute("id", id);
		model.addAttribute("author", author);
		
		model.addAttribute("user", userDAO.getUserByUsername(principal.getName()));
		
		return "post-list";
	}
	
	@RequestMapping("/post-form")
	public String getPostForm(Model model, Principal principal) {
		
		Post post = new Post();
		List<Category> categoryList = categoryDAO.getCategoryList();
		List<User> userList = userDAO.getUserList();
		List<Tag>tagList = tagDAO.getTagList();
		
		model.addAttribute("post", post);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("userList", userList);
		model.addAttribute("tagList", tagList);
		
		model.addAttribute("user", userDAO.getUserByUsername(principal.getName()));
		
		return "post-form";
	}
	
	@RequestMapping("/bloger-post-form")
	public String getBlogerPostForm(Model model, Principal principal) {	
		
		model.addAttribute("user", userDAO.getUserByUsername(principal.getName()));
		
		Post post = new Post();		
		List<Category> categoryList = categoryDAO.getCategoryList();
		List<Tag>tagList = tagDAO.getTagList();
		
		model.addAttribute("post", post);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("tagList", tagList);
		
		return "bloger-post-form";
	}
	
	@RequestMapping("/bloger-post-save")
	public String saveBlogerPost(@ModelAttribute Post post, Principal principal) {		
		
		ArrayList<Integer> ids = new ArrayList<Integer>();
		
		for(Tag t : post.getTags()) {
			ids.add(Integer.parseInt(t.getName()));
		}
		
		List<Tag> tags = tagDAO.getTagsByIds(ids);
		post.setTags(tags);
		
		Category category = categoryDAO.getCategory(post.getCategory().getId());
		post.setCategory(category);
		
		User user = userDAO.getUserByUsername(principal.getName());
		post.setUser(user);
		
		postDAO.savePost(post);
		
		return "redirect:/administration/post-list";
	}
	
	@RequestMapping("/post-save")
	public String savePost(@ModelAttribute Post post) {		
		
		ArrayList<Integer> ids = new ArrayList<Integer>();
		
		for(Tag t : post.getTags()) {
			ids.add(Integer.parseInt(t.getName()));
		}
		
		List<Tag> tags = tagDAO.getTagsByIds(ids);
		post.setTags(tags);
		
		Category category = categoryDAO.getCategory(post.getCategory().getId());
		post.setCategory(category);
		
		User user = userDAO.getUserByUsername(post.getUser().getUsername());
		post.setUser(user);
		
		postDAO.savePost(post);
		
		return "redirect:/administration/post-list";
	}
	
	@RequestMapping("/post-update-form")
	public String getUpdatePostForm(@RequestParam int id, Model model, Principal principal) {
				
		Post post = postDAO.getPostWithTags(id);
		List<Category> categoryList = categoryDAO.getCategoryList();
		List<User> userList = userDAO.getUserList();
		List<Tag> tagList = tagDAO.getTagList();
		
		model.addAttribute("post", post);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("userList", userList);
		model.addAttribute("tagList", tagList);
		
		model.addAttribute("user", userDAO.getUserByUsername(principal.getName()));
		
		return "post-form";
	}
	
	@RequestMapping("/post-delete")
	public String deletePost(@RequestParam int id) {
		postDAO.deletePost(id);
		return "redirect:/administration/post-list";
	}
	
	@RequestMapping("/make-slide")
	public String makeSlideFromPost(@RequestParam int id, Model model, Principal principal) {
		
		Post post = postDAO.getPost(id);		
		Slide slide = new Slide();
		
		slide.setButton_url("/BlogSpringMvcApp/blog-post?id=" + id);
		slide.setEnable(true);
		slide.setImage(post.getImage());
		slide.setOrder_number(1);
		slide.setTitle(post.getTitle());
		
		model.addAttribute("slide", slide);
		model.addAttribute("user", userDAO.getUserByUsername(principal.getName()));
		
		return "slide-form";
	}
	
	//TAG ---------------------------------------------------------------
	
	@RequestMapping("/tag-list")
	public String getTagList(Model model, Principal principal) {
		List<Tag> tagList = tagDAO.getTagList();
		model.addAttribute("tagList", tagList);	
		model.addAttribute("user", userDAO.getUserByUsername(principal.getName()));
		return "tag-list";
	}
	
	@RequestMapping("/tag-form")
	public String getTagForm(Model model, Principal principal) {
		Tag tag = new Tag();
		model.addAttribute("tag", tag);
		model.addAttribute("user", userDAO.getUserByUsername(principal.getName()));
		return "tag-form";
	}
	
	@RequestMapping("/tag-save")
	public String saveTag(@ModelAttribute Tag tag) {
		tagDAO.saveTag(tag);
		return "redirect:/administration/tag-list";
	}
	
	@RequestMapping("/tag-delete")
	public String deleteTag(@RequestParam int id) {
		tagDAO.deleteTag(id);
		return "redirect:/administration/tag-list";
	}
	
	@RequestMapping("/tag-update-form")
	public String getUpdateTagForm(@RequestParam int id, Model model, Principal principal) {
		Tag tag = tagDAO.getTag(id);
		model.addAttribute("tag", tag);
		model.addAttribute("user", userDAO.getUserByUsername(principal.getName()));
		return "tag-form";
	}
	
	//COMMENT ------------------------------------------------------------
	
	@RequestMapping("/comment-list")
	public String getCommentList(Model model, Principal principal) {
		model.addAttribute("comments", commentDAO.getComments());
		model.addAttribute("user", userDAO.getUserByUsername(principal.getName()));
		return "comment-list";
	}
	
	@RequestMapping("/comment-delete")
	public String deleteComment(@RequestParam int id) {
		commentDAO.deleteComment(id);		
		return "redirect:/administration/comment-list";		
	}
	
	@RequestMapping("/comment-toggle")
	public String toggleComment(@RequestParam int id) {
		commentDAO.toggleComment(id);
		return "redirect:/administration/comment-list";
	}
	
	//MESSAGE ------------------------------------------------------------
	
	@RequestMapping("/message-list")
	public String getMessageList(Model model, Principal principal) {
		model.addAttribute("messages", messageDAO.getMessages());
		model.addAttribute("user", userDAO.getUserByUsername(principal.getName()));
		return "message-list";
	}
	
	@RequestMapping("/message-delete")
	public String deleteMessage(@RequestParam int id) {
		messageDAO.deleteMessage(id);
		return "redirect:/administration/message-list";
	}
	
	@RequestMapping("/message-check")
	public String checkMessage(@RequestParam int id) {
		messageDAO.toggleMessage(id);
		return "redirect:/administration/message-list";
	}
	
	//SLIDE --------------------------------------------------------------
	
	@RequestMapping("/slide-form")
	public String getSlideForm(Model model, Principal principal) {
		model.addAttribute("slide", new Slide());
		model.addAttribute("user", userDAO.getUserByUsername(principal.getName()));
		return "slide-form";
	}
	
	@RequestMapping("/slide-update-form")
	public String getSlideUpdateForm(@RequestParam int id, Model model, Principal principal) {		
		model.addAttribute("slide", slideDAO.getSlideById(id));
		model.addAttribute("user", userDAO.getUserByUsername(principal.getName()));
		return "slide-form";
	}
	
	@RequestMapping("/slide-save")
	public String saveSlide(@ModelAttribute Slide slide) {
		slideDAO.saveSlide(slide);
		return "redirect:/administration/slide-list";
	}
	
	@RequestMapping("/slide-list")
	public String getSlideList(Model model, Principal principal ) {
		model.addAttribute("slides", slideDAO.getSlideList());
		model.addAttribute("user", userDAO.getUserByUsername(principal.getName()));
		return "slide-list";
	}
	
	//USER --------------------------------------------------------------
	
	@RequestMapping("/user-list")
	public String getUserList(Model model, Principal principal) {
		model.addAttribute("users", userDAO.getUserList());
		model.addAttribute("user", userDAO.getUserByUsername(principal.getName()));
		return "user-list";
	}
	
	@RequestMapping("/user-block")
	public String toggleUser(@RequestParam String username) {
		userDAO.toggleUser(username);
		return "redirect:/administration/user-list";
	}

	@RequestMapping("/user-update-form")
	public String getUserUpdateForm(@RequestParam String username, Model model) {
		model.addAttribute("roles", userDAO.getRoles());
		model.addAttribute("user", userDAO.getUserByUsername(username));		
		return "user-form";
	}
	
	@RequestMapping("/user-save")
	public String saveUser(@ModelAttribute User user) {
		userDAO.saveUser(user);
		return "redirect:/administration/user-list";
	}
	
	@RequestMapping("/user-delete-form")
	public String saveUser(@RequestParam String username) {
		userDAO.deleteUser(username);
		return "redirect:/administration/user-list";
	}
	
	@RequestMapping("/user-form")
	public String getUserForm(Model model, Principal principal) {
		model.addAttribute("roles", userDAO.getRoles());
		model.addAttribute("user", new User());
		model.addAttribute("userP", userDAO.getUserByUsername(principal.getName()));
		return "user-form";
	}
	
	@RequestMapping("/user-form-edit")
	public String getUserFormEdit(Model model, Principal principal) {
		User user = userDAO.getUserByUsername(principal.getName());
		model.addAttribute("user", user);		
		return "user-form-edit";
	}

	@RequestMapping("/user-form-edit-save")
	public String userEditSave(Principal principal, @ModelAttribute User user) {
		String username = principal.getName();
		userDAO.changeUserDetails(username, user);
		return "change-success";
	}
	
	@RequestMapping("/user-change-password")
	public String getChangePasswordForm(Principal principal, Model model) {
		model.addAttribute("user", userDAO.getUserByUsername(principal.getName()));
		model.addAttribute("username", principal.getName());
		return "user-change-password";
	}
	
	@RequestMapping("/user-change-password-save")
	public String changePassword(@RequestParam String oldPass, @RequestParam String newPass, 
			@RequestParam String repeatPass, @RequestParam String username) {
		
		if(userDAO.changePassword(oldPass, newPass, repeatPass, username))
			return "change-success";
		
		return "access-deneid";
	}
}
