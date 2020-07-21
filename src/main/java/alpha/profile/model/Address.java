package alpha.profile.model;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import javax.persistence.*;


@Table("address")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Address {
    @PrimaryKey
    private String addressId;

    @Column(name="ADDRESS_LINE1",length=50,nullable=false,unique=true)
    private String addressLine1;

    @Column(name="ADDRESS_LINE2",length=50,nullable=false,unique=true)
    private String addressLine2;

    @Column(name="CITY",length=30,nullable=false)
    private String city;

    @Column(name="ZIPCODE",length=10,nullable=false)
    private Long zipCode;

    @Column(name="STATE",length=30,nullable=false)
    private String state;

    @Column(name="COUNTRY",length=30,nullable=false)
    private String country;

}