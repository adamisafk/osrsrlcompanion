/*this class contains methods for the dialog boxes for adding accounts, as well as where OAuth appears
and the storage for osrs names*/


package com.sirgar.kadill.osrs_rl_companion;

public class accounts {
    public String osrsName = "";
    public boolean isNamePresent;

    //method to fetch username from first entry at dialog box at splash screen
    public void getOsrsName(String loginName) {
        osrsName = loginName;
    }

    //method to detect if username is present - for some reason it keeps giving false when its supposed to be true
    public boolean checkName() {
        if (osrsName.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

}
