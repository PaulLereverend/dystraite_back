package com.ynov.dystraite.entities;

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
public class Books {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	@Lob
	private String description;
	
	@Column(name = "link")
	private String link;
	
	@Column(name = "thumbnail", columnDefinition="BLOB")
	private byte[] thumbnail;
	
	
	@Column(name = "created_at")
	private Date createdAt;	
	
	@ManyToMany
	@JoinTable(
			  name = "tags", 
			  joinColumns = @JoinColumn(name = "book_id"),
			  inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tags> tags;

	public Books() {
	}
	public Books(String title, String description, String link, byte[] thumbnail, Date createdAt, List<Tags> tags) {
		super();
		this.title = title;
		this.description = description;
		this.link = link;
		this.thumbnail = thumbnail;
		this.createdAt = createdAt;
		this.tags = tags;
	}
	
}
