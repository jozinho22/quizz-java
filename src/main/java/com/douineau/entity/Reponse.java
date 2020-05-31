package com.douineau.entity;

import java.io.Serializable;

//@Entity
public class Reponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1876188057166632647L;

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	private String texte;

//	@Column(name = "is_true")
	private Boolean isTrue;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "question_id")
	private Question question;

//	@Column(name = "created_at")
//	@Temporal(TemporalType.TIMESTAMP)
//	protected Date createdAt;

	public Reponse() {
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

	public Boolean getIsTrue() {
		return isTrue;
	}

	public void setIsTrue(Boolean isTrue) {
		this.isTrue = isTrue;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "Reponse [id=" + id + ", texte=" + texte + ", isTrue=" + isTrue + ", question=" + question + "]";
	}

//	public Date getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(Date createdAt) {
//		this.createdAt = createdAt;
//	}



}
