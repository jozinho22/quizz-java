package com.douineau.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

//@Entity
public class Question implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5641309062449375141L;
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	
	private String texte;
	
	private String topic;
	
//	@OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
	private List<Reponse> reponses;

	@JsonIgnore
	private Boolean isDone = false;

//	@Column(name = "created_at")
//	@Temporal(TemporalType.TIMESTAMP)
//	@JsonIgnore
//	private Date createdAt;
	
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTexte() {
		return texte;
	}
	
	public void setTexte(String texte) {
		this.texte = texte;
	}
	
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public List<Reponse> getReponses() {
		return reponses;
	}

	public void setReponses(List<Reponse> reponses) {
		this.reponses = reponses;
	}
	
	public Boolean getIsDone() {
		return isDone;
	}

	public void setIsDone(Boolean isDone) {
		this.isDone = isDone;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", texte=" + texte + ", topic=" + topic + ", reponses=" + reponses + ", isDone="
				+ isDone + "]";
	}




//	public Date getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(Date createdAt) {
//		this.createdAt = createdAt;
//	}


}
