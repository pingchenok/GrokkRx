package com.autolotto.myrxjava;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import rx.*;
import rx.Observable.*;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MyRxJava";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rxExample7();
    }

    private void rxExample7() {
        Observable<String> myObservable = Observable.from(new String[]{"1234567", "123456", "12345", "1234"});
        myObservable
                .filter( s -> s.length() > 4)
                .map(s -> s + " - chen")
                .take(2)
                .subscribe(s -> Log.i(TAG, s));
    }


    private void rxExample6() {
        Observable<String> myObservable = Observable.just("Used chain of maps and hashcode");
        myObservable
                .map(s -> s + " - chen")
                .map(s -> s.hashCode())
                .map(i -> Integer.toHexString(i))
                .subscribe(s -> Log.i(TAG, s + " is hashcode"));
    }

    private void rxExample5() {
        Observable<String> myObservable = Observable.just("Used Lambda instead of Func for Map");
        myObservable
                .map(s -> s + " -chen")
                .subscribe(s -> Log.i(TAG, s));
    }

    private void rxExample4() {
        Observable<String> myObservable = Observable.just("Used Pure Func for Map");
        myObservable.map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s + " -chen";
            }
        }).subscribe(s -> Log.i(TAG, s));
    }

    private void rxExample3() {
        Observable<String> myObservable = Observable.just("Used Lambda");
        myObservable.subscribe(s -> Log.i(TAG, s));
    }

    private void rxExample2() {
        Observable<String> myObservable = Observable.just("just example");
        myObservable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i(TAG, s);
            }
        });
    }

    private void rxExample1() {
        OnSubscribe myOnSubscribe = new OnSubscribe<String>() {
            @Override
            public void call (Subscriber<? super String> sub) {
                sub.onNext("verbose example");
            }
        };

        Observable<String> myObservable = Observable.create(myOnSubscribe );

        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, s);
            }
        };

        Subscription subscription = myObservable.subscribe(mySubscriber);
    }
}