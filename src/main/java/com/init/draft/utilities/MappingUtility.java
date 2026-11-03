package com.init.draft.utilities;

import com.init.draft.dtos.UserProfileRecord;
import com.init.draft.dtos.UserRecord;
import com.init.draft.models.UsersORM;

public class MappingUtility {
    public static UsersORM userRecordToORM(UserRecord user) {
        UsersORM userOrm = new UsersORM();
        userOrm.setUserId(user.userId());
        userOrm.setFirstName(user.firstName());
        userOrm.setLastName(user.lastName());
        userOrm.setEmail(user.email());
        userOrm.setPassword(user.password());
        userOrm.setIsActive(user.isActive());
        userOrm.setIsVerified(user.isVerified());
        return userOrm;
    }

    public static UserRecord userORMToUserRecord(UsersORM userOrm) {
        return new UserRecord(userOrm.getUserId(), userOrm.getFirstName(), userOrm.getLastName(), userOrm.getEmail(),
                userOrm.getPassword(), userOrm.getIsActive(), userOrm.getIsVerified());
    }

    public static UserProfileRecord userORMToUserProfile(UsersORM userOrm) {
        return new UserProfileRecord(userOrm.getUserId(), userOrm.getFirstName(), userOrm.getLastName(),
                userOrm.getEmail(),
                userOrm.getPassword(), userOrm.getIsVerified());
    }

    public static UsersORM userProfileRecordToORM(UserProfileRecord userProfileRecord) {
        UsersORM userOrm = new UsersORM();
        userOrm.setUserId(userProfileRecord.userId());
        userOrm.setFirstName(userProfileRecord.firstName());
        userOrm.setLastName(userProfileRecord.lastName());
        userOrm.setEmail(userProfileRecord.email());
        userOrm.setIsActive(true);
        userOrm.setIsVerified(userProfileRecord.isVerified());
        return userOrm;
    }


}
