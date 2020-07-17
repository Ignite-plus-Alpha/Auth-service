package alpha.profile.dao;

import alpha.profile.model.Wallet;
import org.springframework.data.cassandra.repository.CassandraRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface WalletDao extends CassandraRepository<Wallet,String> {

}
