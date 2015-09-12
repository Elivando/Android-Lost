package org.androidLost.server.utils.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.androidLost.server.utils.BaseEntity;

@Entity
@XmlRootElement
@Table(name = "aparelho")
@AttributeOverride(name = "id", column = @Column(name = "CodAparelho"))
public class AparelhoEntity extends BaseEntity<Long> {
	private static final long serialVersionUID = 9125694759318053435L;

	private String descricao;
	
	@NotNull
	private String imei;

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

}
