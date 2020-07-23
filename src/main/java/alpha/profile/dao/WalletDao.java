package alpha.profile.dao;

import alpha.profile.model.Address;
import alpha.profile.model.Wallet;
import org.springframework.data.cassandra.repository.CassandraRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletDao extends CassandraRepository<Wallet,String> {
    //fetch list of wallets associated with an email
    List<Wallet> findByEmail(String email);

}
