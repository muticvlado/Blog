package com.vlado;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vlado.dao.CategoryDAO;
import com.vlado.dao.CommentDAO;
import com.vlado.dao.MessageDAO;
import com.vlado.dao.PostDAO;
import com.vlado.dao.SlideDAO;
import com.vlado.dao.TagDAO;
import com.vlado.dao.UserDAO;
import com.vlado.entity.Comment;
import com.vlado.entity.Message;
import com.vlado.entity.Post;
import com.vlado.entity.User;

@Controller
@RequestMapping("/")
public class FrontController {
	
	@Autowired
	PostDAO postDAO;
	
	@Autowired
	CategoryDAO categoryDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	TagDAO tagDAO;
	
	@Autowired
	MessageDAO messageDAO;
	
	@Autowired
	CommentDAO commentDAO;
	
	@Autowired
	SlideDAO slideDAO;
	
	@RequestMapping("/")
	public String getIndexPage(Model model) {		
		model.addAttribute("slider", slideDAO.getSlider());
		model.addAttribute("importantPosts", postDAO.getImportantPost());
		model.addAttribute("latestPosts", postDAO.getLastNumberOfPost(12));
		model.addAttribute("latestCategories", categoryDAO.getCategoryListPriority());
		
		return "front/index-page";
	}
	
	@RequestMapping("/blog")
	public String getBlogPage(@RequestParam(name = "page", defaultValue = "1") int page, Model model) {
		
		List<Post> posts = postDAO.getLastNumberOfPost(12);
		
		model.addAttribute("latestPosts", posts);
		model.addAttribute("latestCategories", categoryDAO.getCategoryListPriority());
		model.addAttribute("tags", tagDAO.getTheMostUseTags());	
		
		model.addAttribute("pagePosts", postDAO.getPostsByPage(page));
		model.addAttribute("page", page);
		
		if(posts.size()%4 == 0)
			model.addAttribute("pageCount", posts.size()/4);	
		else
			model.addAttribute("pageCount", (posts.size()/4)+1);
		
		return "front/blog";
	}
	
	@RequestMapping("/blog-author")
	public String getBlogAuthorPage(@RequestParam String username, Model model) {
		
		User user = userDAO.getAuthorWithPosts(username);		
		model.addAttribute("user", user);

		model.addAttribute("latestPosts", postDAO.getLastNumberOfPost(3));
		model.addAttribute("latestCategories", categoryDAO.getCategoryListPriority());
		model.addAttribute("tags", tagDAO.getTheMostUseTags());
		
		return "front/blog-author";
	}
	
	@RequestMapping("/blog-category")
	public String getBlogCategoryPage(@RequestParam int id, Model model) {
		model.addAttribute("categoryPosts", categoryDAO.getCategoryWithPosts(id));
		
		model.addAttribute("latestPosts", postDAO.getLastNumberOfPost(3));
		model.addAttribute("latestCategories", categoryDAO.getCategoryListPriority());
		model.addAttribute("tags", tagDAO.getTheMostUseTags());
		
		return "front/blog-category";
	}
	
	@RequestMapping("/blog-post/{id}/{url}")
	public String getBlogPostPage(@PathVariable int id, @PathVariable String url, Model model) {
		model.addAttribute("prevPost", postDAO.getPreviousPost(id));
		model.addAttribute("nextPost", postDAO.getNextPost(id));
		model.addAttribute("post", postDAO.getPostWithTags(id));
		model.addAttribute("latestPosts", postDAO.getLastNumberOfPost(3));
		model.addAttribute("latestCategories", categoryDAO.getCategoryListPriority());
		model.addAttribute("tags", tagDAO.getTheMostUseTags());
		model.addAttribute("comments", commentDAO.getCommentsByPostId(id));
		model.addAttribute("comment", new Comment());
		
		postDAO.incrementViews(id);
		
		return "front/blog-post";
	}
	
	@RequestMapping("/blog-search")
	public String getBlogSearchPage(@RequestParam String keywords, Model model) {
		model.addAttribute("posts", postDAO.search(keywords));		
		
		model.addAttribute("latestPosts", postDAO.getLastNumberOfPost(3));
		model.addAttribute("latestCategories", categoryDAO.getCategoryListPriority());
		model.addAttribute("tags", tagDAO.getTheMostUseTags());
		
		return "front/blog-search";
	}	
	
	@RequestMapping("/blog-tag")
	public String getBlogTagPage(@RequestParam int id, Model model) {		
		model.addAttribute("tagPosts", tagDAO.getTagWithPosts(id));	
		
		model.addAttribute("latestPosts", postDAO.getLastNumberOfPost(3));
		model.addAttribute("latestCategories", categoryDAO.getCategoryListPriority());
		model.addAttribute("tags", tagDAO.getTheMostUseTags());
		
		return "front/blog-tag";
	}
	
	@RequestMapping("/contact")
	public String getContactPage(Model model) {
		model.addAttribute("message", new Message());
		model.addAttribute("latestCategories", categoryDAO.getCategoryListPriority());
		model.addAttribute("latestPosts", postDAO.getLastNumberOfPost(3));
		
		return "front/contact";
	}
	
	@RequestMapping("/message-save")
	public String saveMessage(@ModelAttribute Message message) {
		messageDAO.saveMessage(message);
		
		return "redirect:/contact";
	}
	
	@RequestMapping("/comment-save")
	public String saveComment(@Valid @ModelAttribute Comment comment, BindingResult result, Model model) {
		
		int id = comment.getPost().getId();
		String title = postDAO.getPost(id).getTitle();
		String url = FriendlyUrl.createFriendlyUrl(title);
		
		if(result.hasErrors()) {
			model.addAttribute("prevPost", postDAO.getPreviousPost(id));
			model.addAttribute("nextPost", postDAO.getNextPost(id));
			model.addAttribute("post", postDAO.getPostWithTags(id));
			model.addAttribute("latestPosts", postDAO.getLastNumberOfPost(3));
			model.addAttribute("latestCategories", categoryDAO.getCategoryListPriority());
			model.addAttribute("tags", tagDAO.getTheMostUseTags());
			model.addAttribute("comments", commentDAO.getCommentsByPostId(id));
			model.addAttribute("comment", comment);
			return "front/blog-post";
			
		} else {
			commentDAO.saveComment(comment);
			
			return "redirect:/blog-post/" + id + "/" + url;
		}
	}
	
}
