package org.androidLost.server.utils.entities;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.androidLost.server.utils.BaseEntity;
import org.androidLost.server.utils.JsonDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Entity
@XmlRootElement
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(columnNames = {
		"login", "email", "cpf" }))
@AttributeOverride(name = "id", column = @Column(name = "CodUsuario"))
public class UsuarioEntity extends BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@NotNull
	private String nome;

	@NotNull
	private String cpf;

	@NotNull
	private String email;

	@NotNull
	private Date dataCadastro;

	@NotNull
	private String login;

	@NotNull
	private String senha;

	@NotNull
	private String status;

	private Date dataAltSenha;

//	@OneToMany(mappedBy = "usuarioEntity", targetEntity = Usuario_AparelhoEntity.class,fetch=FetchType.EAGER)
//	private List<Usuario_AparelhoEntity> usuario_AparelhoEntity;

	@Override
	public void setId(Long Id) {
		super.setId(Id);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDataAltSenha() {
		return dataAltSenha;
	}

	public void setDataAltSenha(Date dataAltSenha) {
		this.dataAltSenha = dataAltSenha;
	}

//	public List<Usuario_AparelhoEntity> getUsuario_AparelhoEntity() {
//		return usuario_AparelhoEntity;
//	}
//
//	public void setUsuario_AparelhoEntity(
//			List<Usuario_AparelhoEntity> usuario_AparelhoEntity) {
//		this.usuario_AparelhoEntity = usuario_AparelhoEntity;
//	}

}
