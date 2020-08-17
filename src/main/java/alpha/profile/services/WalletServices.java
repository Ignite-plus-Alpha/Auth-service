package alpha.profile.services;

import alpha.profile.exceptions.AddressNotFoundException;
import alpha.profile.exceptions.WalletNotFoundException;
import alpha.profile.model.Address;
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


    //create a wallet entry
    public Wallet createWallet(Wallet wallet) {
        return walletDao.save(wallet);
    }

    //find list of wallets in db
    public List<Wallet> getAllWallet() {
        return walletDao.findAll();
    }

    //find all wallets associated with an User address
    public List<Wallet> getWalletsByUserID(String userId) throws  WalletNotFoundException {
        List<Wallet> wallets= walletDao.findByUserid(userId);

        if(wallets==null)
            throw new WalletNotFoundException("address not found");
        return wallets;
    }

    //update a particular wallet associated with an User address
    public Wallet updateWalletByUserIdWalletId(String userId,String walletId,Wallet newWallet) throws WalletNotFoundException {
        List<Wallet>  userWallets = walletDao.findByUserid(userId);

        for (Wallet tempWallet:userWallets
        ) {
            if(tempWallet.getWalletId().equals(walletId) )
           {    tempWallet.setExpiryDate(newWallet.getExpiryDate());
                return walletDao.save(tempWallet);
           }
        }
        throw new WalletNotFoundException("wallet not found");

    }

    //get particular address by user and AddressId
    public Wallet getWalletByUserIdWalletId(String userId,String walletId) {
        List<Wallet>  wallets = walletDao.findByUserid(userId);

        for (Wallet tempWallet:wallets
        ) {
            if(tempWallet.getWalletId().equals(walletId) )
            { return tempWallet;
            }
        }
        return null;
    }

    //delete particular address by user and AddressId
    public void deleteWalletByUserIdWalletId(String userId,String walletId) {
        List<Wallet>  wallets = walletDao.findByUserid(userId);
        for (Wallet tempWallet:wallets
        ) {
            if(tempWallet.getWalletId().equals(walletId) )
            {  walletDao.delete(tempWallet);
            }
        }
    }
}
