package com.eis.cloud;


import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.Test;
import rx.Observable;

import java.util.HashMap;
import java.util.Map;

class User {
    String userId;

    @Override
    public String toString() {

        return "User{" +
                "userId='" + userId + '\'' +
                '}';
    }
}

class Company {
    String companyName;

    @Override
    public String toString() {

        return "Company{" +
                "companyName='" + companyName + '\'' +
                '}';
    }
}


class StubRESTAPI {
    static Observable<Map<String, String>> getCorrelation() {

        return Observable.just(new HashMap<String, String>());
    }

    static Observable<User> getUser() {

        System.out.println("GetUserCalled");
        User u = new User();
        u.userId = "testUser";
        return Observable.just(u);
    }


    static Observable<Company> getUserErrors() {

        return Observable.error(new RuntimeException("bad company call"));
    }

    static Observable<Company> getCompany() {

        System.out.println("GetCompany Called");
        Company c = new Company();
        c.companyName = "testCompanyName";
        return Observable.just(c);
    }

    static Observable<Company> getCompanyErrors() {

        return Observable.error(new RuntimeException("bad company call"));
    }
}


/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Simply shows how to subscribe and receive the eventgs
     */
    @Test
    public void testReactiveUseCase1() {

        Observable<String> o = Observable.just("a", "b", "c");
        o.subscribe(a -> System.out.println(a));

        System.out.println("");

        o.subscribe(a -> System.out.println(a));
        System.out.println("==========");
    }


    /**
     * Shows how you can add a mapping fucntion to the mix
     * which will add -postfix to the end of the sequence
     */
    @Test
    public void testReactiveUseCase2() {

        int i = 0;

        Observable<String> o = Observable.just("a", "b", "c");

        o.map(s -> s + "-postfix").subscribe(a -> System.out.println(a));
        System.out.println("==========");
    }


    @Test
    public void testReactiveUseCase3() {
        // this will chain the two calls together such that get company is called
        // and then getUser is called ( potentially asynchronously.  The result returned as a pair
        System.out.println("= Make the calls now in parallel but return when both are done =");
        Observable.combineLatest(StubRESTAPI.getCompany(), StubRESTAPI.getUser(),
                (company, user) -> new ImmutablePair<>(company, user))
                .subscribe(a -> System.out.println(a));


        System.out.println("= Make the calls now in sequence =");
        StubRESTAPI.getCompany().flatMap(successResponse ->
                StubRESTAPI.getUser()).subscribe();


        System.out.println("= Shows what happens if either call in sequence throws an exception --" );

        System.out.println("= Fail First --" );
        StubRESTAPI.getCompanyErrors().subscribe(a -> System.out.println("Success"),
                error -> System.out.println("Errored:" + error));


        System.out.println("= Fail Second --" );
        StubRESTAPI.getCompany().flatMap(successResponse ->
                StubRESTAPI.getUserErrors())
                .subscribe(a -> System.out.println("Success"),
                        error -> System.out.println("Errored:" + error));

        System.out.println("==========");


    }


}
