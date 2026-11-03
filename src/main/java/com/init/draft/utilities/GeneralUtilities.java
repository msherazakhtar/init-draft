package com.init.draft.utilities;

public class GeneralUtilities {
    public static int generateOTP()
    {
        return 100000 + (int) (Math.random() * 900000);

    }
}
