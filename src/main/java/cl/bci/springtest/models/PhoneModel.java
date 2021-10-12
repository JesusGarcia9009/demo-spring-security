package cl.bci.springtest.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "phone")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneModel implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "number", nullable = false)
	private String number;
	
	@Column(name = "city_code", nullable = false)
	private String cityCode;
	
	@Column(name = "country_code", nullable = false)
	private String countryCode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_users")
	private UserModel userPhone;

}
