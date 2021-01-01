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

@Entity
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String titre) {
		this.title = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String lien) {
		this.link = lien;
	}

	public byte[] getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(byte[] vignette) {
		this.thumbnail = vignette;
	}

	public List<Tags> getTags() {
		return tags;
	}

	public void setTags(List<Tags> tags) {
		this.tags = tags;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
}
