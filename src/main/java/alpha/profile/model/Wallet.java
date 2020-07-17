package alpha.profile.model;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.persistence.Column;

@Table("wallet")
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Wallet {

    @PrimaryKey
    private String walletId;

    @Column(name="CARDHOLDER NAME",length=50,nullable=false,unique=true)
    private String cardholderName;

    @Column(name="CARD_NUMBER",length=50,nullable=false,unique=true)
    private Long cardNumber;

    @Column(name="EXPIRY DATE",length=30,nullable=false)
    private Long expiryDate;

    @Column(name="UPI ID",length=50,nullable=false,unique=true)
    private String upiId;

}
