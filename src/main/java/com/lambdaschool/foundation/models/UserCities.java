package com.lambdaschool.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Table for Cities favorited by User
 */
@Entity
@Table(name = "user_cities")
@IdClass(UserCitiesId.class)
public class UserCities extends Auditable implements Serializable
{
    /**
     * 1/2 of complex key, UserId
     */
    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties(value = "cities", allowSetters = true)
    private User user;

    /**
     * 1/2 of complex key, CityId
     */
    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "city_id")
    @JsonIgnoreProperties(value = "users", allowSetters = true)
    private City city;

    /**
     * Default constructor
     */
    public UserCities()
    {
    }

    /**
     * Main constructor
     * @param user User that is favoriting
     * @param city City that is being favorited
     */
    public UserCities(
        @NotNull User user,
        @NotNull City city)
    {
        this.user = user;
        this.city = city;
    }

    /**
     * Getters and setters for UserCities fields
     *
     **********************************************************************************/
    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public City getCity()
    {
        return city;
    }

    public void setCity(City city)
    {
        this.city = city;
    }

    /**
     * Because this is a join table and implements Serializable,
     * equals and hashcode methods are required
     * @param o Object being compared
     * @return return boolean if object matches
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof UserCities))
        {
            return false;
        }
        UserCities that = (UserCities) o;
        return ((user == null) ? 0 : user.getUserId()) == ((that.user == null) ? 0 : that.user.getUserId()) &&
            ((city == null) ? 0 : city.getCityId()) == ((that.city == null) ? 0 : that.city.getCityId());
    }

    /**
     * Override of default hasCode(), we always want it to default to equals()
     * so we return a default value
     * @return default value
     */
    @Override
    public int hashCode()
    {
        return 37;
    }
}
