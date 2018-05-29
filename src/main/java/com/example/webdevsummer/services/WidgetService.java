package com.example.webdevsummer.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer.model.Topic;
import com.example.webdevsummer.model.Widget;
import com.example.webdevsummer.repositories.TopicRepository;
import com.example.webdevsummer.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins="*")
public class WidgetService {
	
	@Autowired
	private WidgetRepository widgetRepository;
	
	@Autowired
	private TopicRepository topicRepository;
	
	@PostMapping("api/topic/{topicId}/widget/save")
	public void saveAllWidgets(@PathVariable("topicId") int topicId,@RequestBody List<Widget> widgets) {
		Optional<Topic> data=topicRepository.findById(topicId);
		if(data.isPresent()) {
			Topic topic =data.get();
			List<Widget> widgetlist=topic.getWidgets();
			for(Widget widget:widgetlist) {
				widgetRepository.deleteById(widget.getId());
			}
		}
		for(Widget widget:widgets) {
			Optional<Topic> datatopic=topicRepository.findById(topicId);
			if(data.isPresent()) {
				Topic topic =datatopic.get();
				widget.setTopic(topic);
				widgetRepository.save(widget);
			}
		}
	}
	
	@GetMapping("/api/widget")
	public List<Widget> findAllWidgets(){
		return (List<Widget>) widgetRepository.findAll();
	}
	
	@GetMapping("api/topic/{topicId}/widget")
	public List<Widget> getWidgetByTopicId(@PathVariable("topicId") int topicId){
		Optional<Topic> data = topicRepository.findById(topicId);
		if(data.isPresent()) {
			Topic topic = data.get();
			List<Widget> widgets= topic.getWidgets();
			Collections.sort(widgets);
			return widgets;
		}
		return null;	
	}
	
}
