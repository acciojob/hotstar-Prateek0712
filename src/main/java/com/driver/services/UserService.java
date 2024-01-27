package com.driver.services;


import com.driver.model.Subscription;
import com.driver.model.SubscriptionType;
import com.driver.model.User;
import com.driver.model.WebSeries;
import com.driver.repository.UserRepository;
import com.driver.repository.WebSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WebSeriesRepository webSeriesRepository;


    public Integer addUser(User user){

        //Jut simply add the user to the Db and return the userId returned by the repository
        User user1=userRepository.save(user);
        return user1.getId();
    }
    public Integer getAvailableCountOfWebSeriesViewable(Integer userId){

        //Return the count of all webSeries that a user can watch based on his ageLimit and subscriptionType
        //Hint: Take out all the Webseries from the WebRepository
        Optional<User>OptionalUser=userRepository.findById(userId);
        if(!OptionalUser.isPresent())
        {
           return 0;
        }
        User user1  =OptionalUser.get();
        List<WebSeries>seriesList=webSeriesRepository.findBySubscriptionType(user1.getSubscription().getSubscriptionType());
        int  cnt=0;
        for(WebSeries ws:seriesList)
        {
            if(ws.getAgeLimit()<=user1.getAge()) cnt++;
        }
        return cnt;
    }
/* <-------------------------------------------DONE---------------------------------------------->*/



}
