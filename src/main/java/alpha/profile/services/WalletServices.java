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

    //find all wallets associated with an email address
    public List<Wallet> getWalletsByEmail(String email) throws  WalletNotFoundException {
        List<Wallet> wallets= walletDao.findByEmail(email);

        if(wallets==null)
            throw new WalletNotFoundException("address not found");
        return wallets;
    }

    //update a particular wallet associated with an email address
    public Wallet updateWalletByEmailIdWalletId(String email,String walletId,Wallet newWallet) throws WalletNotFoundException {
        List<Wallet>  userWallets = walletDao.findByEmail(email);

        for (Wallet tempWallet:userWallets
        ) {
            if(tempWallet.getWalletid().equals(walletId) )
           {
                  tempWallet.setExpiryDate(newWallet.getExpiryDate());
                return walletDao.save(tempWallet);
           }
        }
        throw new WalletNotFoundException("wallet not found");

    }

    //get particular address by email and AddressId
    public Wallet getWalletByEmailIdWalletId(String email,String walletId) {
        List<Wallet>  wallets = walletDao.findByEmail(email);

        for (Wallet tempWallet:wallets
        ) {
            if(tempWallet.getWalletid().equals(walletId) )
            { return tempWallet;
            }
        }
        return null;
    }

    //delete particular address by email and AddressId
    public void deleteWalletByEmailIdWalletId(String email,String walletId) {
        List<Wallet>  wallets = walletDao.findByEmail(email);
        for (Wallet tempWallet:wallets
        ) {
            if(tempWallet.getWalletid().equals(walletId) )
            {  walletDao.delete(tempWallet);
            }
        }

    }




//    public Optional<Wallet> getWalletById(String walletId) throws WalletNotFoundException {
//        Optional<Wallet> wallet = walletDao.findById(walletId);
//
//        if (!wallet.isPresent())
//            throw new WalletNotFoundException("Card not found");
//        return wallet;
//    }

//    public void deleteWalletById(String walletId) {
//        Optional<Wallet> wallet = walletDao.findById(walletId);
//        if (!wallet.isPresent()) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Card not found in repo,enter correct details");
//        }
//        walletDao.deleteById(walletId);
//    }

//    public Wallet updateWalletById(String walletId, String Cardholder_name) throws WalletNotFoundException {
//        Optional<Wallet> walletData = walletDao.findById(walletId);
//
//        if (walletDao.findById(walletId).isPresent()) {
//            Wallet wallet = walletData.get();
//            wallet.setCardholderName(Cardholder_name);
//            return walletDao.save(wallet);
//        }
//        throw new WalletNotFoundException("Wallet not found");
//    }
}
