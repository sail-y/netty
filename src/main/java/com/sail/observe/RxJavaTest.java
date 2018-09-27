package com.sail.observe;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * @author yangfan
 * @date 2017/08/16
 */
public class RxJavaTest {
    public static void main(String[] args) {


        // 创建事件源
        Observable<String> observable = Observable.create(e -> {
            e.onNext("Hello RxJava");
            e.onNext("I am 帆");
            e.onComplete();
        });

        // 创建订阅者subcriber
        observable.subscribe(System.out::println);


        observable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


    }
}
