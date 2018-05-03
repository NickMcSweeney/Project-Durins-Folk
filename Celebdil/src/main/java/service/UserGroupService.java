package main.java.service;

import main.java.data.Address;
import main.java.data.User;
import main.java.data.UserGroup;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * A service class for handling UserGroup logic
 */
@Service
public class UserGroupService {

    public UserGroup getUserGroupByGroupId(UUID groupId) {
        UserGroup usergroup = new UserGroup();
        usergroup.setGroupId(groupId);
        usergroup.setAddress(new Address());
        usergroup.setCreationDate(new Date());
        usergroup.setName("Thrain's sons");
        return usergroup;
    }

    public List<UserGroup> getUserGroupsByString(String string) {
        UserGroup userGroup= getUserGroupByGroupId(UUID.randomUUID());
        List<UserGroup> userGroups = new ArrayList<>();
        userGroups.add(userGroup);
        userGroups.add(userGroup);
        userGroups.add(userGroup);
        return userGroups;
    }

    public boolean addUserToUserGroup(UserGroup userGroup, User user) {
        userGroup.addMember(user);
        return true;
    }

    public User setGroupLeader(UserGroup userGroup, User user) {
        User oldLeader = userGroup.getLeader();
        userGroup.setLeader(user);
        return oldLeader;
    }

    public void addUserToSuccessors(UserGroup userGroup, User user) {
        if(userGroup.getMembers() == null || !userGroup.getMembers().contains(user)) {
            userGroup.addMember(user);
        }
        userGroup.addSuccessor(user);
    }

    public boolean removeUserFromGroup(UserGroup userGroup, User user) {
        userGroup.removeMember(user);
        userGroup.removeSuccessor(user);
        if(user.equals(userGroup.getLeader())) {
            User newLeader = userGroup.succeedLeader();
            if(newLeader == null) {
                // TODO: Delete group
                return false;
            }
        }
        return true;
    }

    /**
     * Create a new UserGroup. We will not yet know their AccountNumber.
     * @param userGroup
     * @return
     */
    public UserGroup createNewUserGroup(UserGroup userGroup) {
        userGroup.setGroupId(UUID.randomUUID());
        return userGroup;
    }
}