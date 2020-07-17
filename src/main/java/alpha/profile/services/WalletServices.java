package alpha.profile.services;

import alpha.profile.exceptions.WalletNotFoundException;
import alpha.profile.model.Wallet;
import alpha.profile.dao.WalletDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Service
public class WalletServices {
    @Autowired
    private WalletDao walletDao;


    public Wallet createWallet(Wallet wallet) {
        return walletDao.save(wallet);
    }


    public List<Wallet> getAllWallet() {
        return walletDao.findAll();
    }

    public Optional<Wallet> getWalletById(String walletId) throws WalletNotFoundException {
        Optional<Wallet> wallet = walletDao.findById(walletId);

        if (!wallet.isPresent())
            throw new WalletNotFoundException("Card not found");
        return wallet;
    }

    public void deleteWalletById(String walletId) {
        Optional<Wallet> wallet = walletDao.findById(walletId);
        if (!wallet.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Card not found in repo,enter correct details");
        }
        walletDao.deleteById(walletId);
    }

    public Wallet updateWalletById(String walletId, String Cardholder_name) throws WalletNotFoundException {
        Optional<Wallet> walletData = walletDao.findById(walletId);

        if (walletDao.findById(walletId).isPresent()) {
            Wallet wallet = walletData.get();
            wallet.setCardholderName(Cardholder_name);
            return walletDao.save(wallet);
        }
        throw new WalletNotFoundException("Wallet not found");
    }
}
