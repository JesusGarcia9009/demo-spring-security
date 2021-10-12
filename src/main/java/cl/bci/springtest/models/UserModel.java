package cl.bci.springtest.models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                @Parameter(
                    name = "uuid_gen_strategy_class",
                    value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                )
            }
        )
    @Column(name = "id", updatable = false, nullable = false)
	private UUID id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "created", nullable = false)
	private Date created;
	
	@Column(name = "modified", nullable = false)
	private Date modified;
	
	@Column(name = "last_login", nullable = false)
	private Date lastLogin;
	
	@Column(name = "last_token", nullable = false, length = 2000)
	private String lastToken;
	
	@Column(name = "active", nullable = false)
	private boolean active;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userPhone")
	private Set<PhoneModel> phones = new HashSet<>(0);

	
}
