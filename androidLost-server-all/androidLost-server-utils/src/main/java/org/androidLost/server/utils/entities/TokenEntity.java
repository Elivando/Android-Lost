package org.androidLost.server.utils.entities;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.androidLost.server.utils.BaseEntity;
import org.androidLost.server.utils.JsonDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Entity
@XmlRootElement
@Table(name = "token", uniqueConstraints = @UniqueConstraint(columnNames = { "token" }))
@AttributeOverride(name = "id", column = @Column(name = "CodToken"))
public class TokenEntity extends BaseEntity<Long> {
	private static final long serialVersionUID = -7596122831728650013L;

	@NotNull
	private String token;

	@NotNull
	private Date dataAtivacao;

	@NotNull
	private String status;
	
	@NotNull
	private String plataforma;
	
	@ManyToOne
	@JoinColumn(name = "CodUsuario", referencedColumnName = "CodUsuario")
	private UsuarioEntity usuarioEntity;

	public UsuarioEntity getUsuarioEntity() {
		return usuarioEntity;
	}

	public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDataAtivacao() {
		return dataAtivacao;
	}

	public void setDataAtivacao(Date dataAtivacao) {
		this.dataAtivacao = dataAtivacao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

}
