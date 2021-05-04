package com.vlado.dao;

import java.util.List;

import com.vlado.entity.Slide;

public interface SlideDAO {
	
	List<Slide> getSlideList();
	
	List<Slide> getSlider(); //Only enable slides for slider
	
	void saveSlide(Slide slide);
	
	void deleteSlide(int id);
	
	Slide getSlideById(int id);
}
