package alpha.profile.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.cassandra.core.mapping.*;
import org.springframework.data.cassandra.core.mapping.Table;
import javax.persistence.*;
import javax.persistence.Column;


@Table("profile")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Profile {

    @PrimaryKey
    @Column(name="email",length=30,nullable=false)
    @NonNull
    private String email;

    @Column(name="user_id")
    private String userId;

    @Column(name="first_name",length=50,nullable=false,unique=true)
    private String firstName;

    @Column(name="last_name",length=50,nullable=false,unique=true)
    private String lastName;

    @Column(name="mobile",length=10,nullable=false)
    private String mobile;

    @Column(name="password",length=30,nullable=false)
    private String password;
}