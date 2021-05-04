package com.vlado.dao;

import java.util.List;

import com.vlado.entity.Tag;

public interface TagDAO {
	
	List<Tag> getTagList();
	
	Tag getTag(int id);
	
	Tag getTagWithPosts(int id);
	
	List<Tag> getTagsByIds(List<Integer> ids);	
		
	void saveTag(Tag tag);	
	
	void deleteTag(int id);
	
	List<Tag> getTheMostUseTags();

}
