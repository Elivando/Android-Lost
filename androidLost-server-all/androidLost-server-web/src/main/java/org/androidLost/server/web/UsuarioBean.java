package org.androidLost.server.web;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import org.androidLost.server.utils.entities.UsuarioEntity;
import org.androidLost.server.web.utils.BaseBean;

@ManagedBean
@SessionScoped
@Named
public class UsuarioBean extends BaseBean {
	private static final long serialVersionUID = -3519962563913691073L;

	private long id;
	private String nome;
	private String cpf;
	private String email;
	private Date dataCadastro;
	private String login;
	private String senha;

	public UsuarioBean() {
		super();
	}

	public UsuarioBean(UsuarioEntity usuarioEntity) {
		this.id = usuarioEntity.getId();
		this.nome = usuarioEntity.getNome();
		this.cpf = usuarioEntity.getCpf();
		this.email = usuarioEntity.getEmail();
		this.dataCadastro = usuarioEntity.getDataCadastro();
		this.login = usuarioEntity.getLogin();
		this.senha = usuarioEntity.getSenha();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public UsuarioEntity getUsuarioEntity() {
		UsuarioEntity usuarioEntity = new UsuarioEntity();

		usuarioEntity.setId(this.id);
		usuarioEntity.setNome(this.nome);
		usuarioEntity.setSenha(this.senha);
		usuarioEntity.setCpf(this.cpf);
		usuarioEntity.setEmail(this.email);
		usuarioEntity.setLogin(this.login);

		return usuarioEntity;
	}

}
