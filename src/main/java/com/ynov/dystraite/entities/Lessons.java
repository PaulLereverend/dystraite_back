package com.ynov.dystraite.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Lessons implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	@Lob
	private String description;
	
	@Column(name = "content")
	@Lob
	private String content;
	
	@Column(name = "thumbnail", columnDefinition="BLOB")
	private byte[] thumbnail;
	
	@Column(name = "video")
	private String video;
	
	@Column(name = "created_at")
	private Date createdAt;	
	
	@ManyToMany
	@JoinTable(
			  name = "tags", 
			  joinColumns = @JoinColumn(name = "lesson_id"),
			  inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tags> tags;

	public Lessons(String title, String description, String content, byte[] thumbnail, String video, Date createdAt,
			List<Tags> tags) {
		super();
		this.title = title;
		this.description = description;
		this.content = content;
		this.thumbnail = thumbnail;
		this.video = video;
		this.createdAt = createdAt;
		this.tags = tags;
	}
	public Lessons() {
		super();
	}
	
	
}
