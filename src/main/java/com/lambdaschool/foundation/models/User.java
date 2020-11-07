package com.lambdaschool.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * The entity allowing interaction with the users table
 */
@Entity
@Table(name = "users")
public class User
    extends Auditable
{
    /**
     * The primary key (long) of the users table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    /**
     * The username (String). Cannot be null and must be unique
     */
    @NotNull
    private String username;

    /**
    *The list that holds the users favorite cities
    */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "user", allowSetters = true)
    private List<UserCities> favoriteCities = new ArrayList<>();

    /**
     * Default constructor used primarily by the JPA.
     */
    public User()
    {
    }

    /**
     * Given the params, create a new user object
     * <p>
     * userid is autogenerated
     *
     * @param username The username (String) of the user
     */
    public User(String username)
    {
        setUsername(username);
    }

    /**
     * Getter for userid
     *
     * @return the userid (long) of the user
     */
    public long getUserId()
    {
        return userId;
    }

    /**
     * Setter for userid. Used primary for seeding data
     *
     * @param userid the new userid (long) of the user
     */
    public void setUserId(long userid)
    {
        this.userId = userid;
    }

    /**
     * Getter for username
     *
     * @return the username (String) lowercase
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * setter for username
     *
     * @param username the new username (String) converted to lowercase
     */
    public void setUsername(String username)
    {
        this.username = username.toLowerCase();
    }

    /**
     *
     * getter and setters for user's fav cities
     */

    public List<UserCities> getFavoriteCities()
    {
        return favoriteCities;
    }

    public void setFavoriteCities(List<UserCities> favoriteCities)
    {
        this.favoriteCities = favoriteCities;
    }

    /**
     * ToString override method
     */
    @Override
    public String toString()
    {
        return "User{" +
            "userid=" + userId +
            ", username='" + username + '\'' +
            ", favCities=" + favoriteCities +
            '}';
    }

    /**
     * Keeping this commented out code for future feature User Authentication
     *
     * Internally, user security requires a list of authorities, roles, that the user has. This method is a simple way to provide those.
     * Note that SimpleGrantedAuthority requests the format ROLE_role name all in capital letters!
     *
     * @return The list of authorities, roles, this user object has
     */
//    @JsonIgnore
//    public List<SimpleGrantedAuthority> getAuthority()
//    {
//        List<SimpleGrantedAuthority> rtnList = new ArrayList<>();
//
//        for (UserRoles r : this.roles)
//        {
//            String myRole = "ROLE_" + r.getRole()
//                .getName()
//                .toUpperCase();
//            rtnList.add(new SimpleGrantedAuthority(myRole));
//        }
//
//        return rtnList;
//    }
}
