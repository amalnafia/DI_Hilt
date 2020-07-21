package com.example.di_hilt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Inject;
import javax.inject.Qualifier;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.components.ActivityComponent;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Inject
    AnalyticsServiceImpl someClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("TAG", "onCreate: " + someClass.analyticsMethods());
//        Log.d("TAG", "onCreate: " + someInterfaceImpl2.getAThing());
    }
}


class ExampleServiceImpl {

    private final AnalyticsInterface analyticsService;

    @Inject
    ExampleServiceImpl(@AuthInterceptorOkHttpClient AnalyticsInterface analyticsService) {
        this.analyticsService = analyticsService;
    }
}

interface AnalyticsInterface {
    String analyticsMethods();
}

class AnalyticsServiceImpl implements AnalyticsInterface {

    @Inject
    AnalyticsServiceImpl() {
    }

    @Override
    public String analyticsMethods() {
        return "some Implementation";
    }
}


@Module
@InstallIn(ActivityComponent.class)
class AnalyticsModule {

    @Provides
    public static AnalyticsInterface provideAnalyticsService(
            @AuthInterceptorOkHttpClient AnalyticsInterface analyticsService
    ) {
        return new AnalyticsServiceImpl();
    }
}

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@interface AuthInterceptorOkHttpClient {
}

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@interface OtherInterceptorOkHttpClient {
}

//class SomeClass {
//    private final SomeInterface someInterface;
//
//    @Inject
//    SomeClass(@someInterfaceImpl1 SomeInterface someInterface) {
//        this.someInterface = someInterface;
//    }
//
//}
//
//
//interface SomeInterface {
//    String getAThing();
//}
//
//class SomeInterfaceImpl1 implements SomeInterface {
//
//    @Inject
//    public SomeInterfaceImpl1() {
//    }
//
//    @Override
//    public String getAThing() {
//        return "Interface result 1";
//    }
//
//}
////
////class SomeInterfaceImpl2 implements SomeInterface {
////
////
////
////    @Inject
////    public SomeInterfaceImpl2( ) {
////    }
////
////    @Override
////    public String getAThing() {
////        return "Interface result 2";
////    }
////
////}
//
//@Module
//@InstallIn(ActivityComponent.class)
//class MyModule {
//
//    @Provides
//    public static SomeInterface provideSomeInterface
//            (@someInterfaceImpl1 SomeInterface someInterface) {
//        return new SomeInterfaceImpl1();
//    }
//
////    @Provides
////    public static SomeInterface provideSomeInterface2(
////            @someInterfaceImpl2
////                    SomeInterface someInterface
////    ) {
////        return new SomeInterfaceImpl2();
////    }
//
//}
//
//@Qualifier
//@Retention(RetentionPolicy.RUNTIME)
//@interface someInterfaceImpl1 {
//}
//
////@Qualifier
////@Retention(RetentionPolicy.RUNTIME)
////@interface someInterfaceImpl2 {
////}
//
