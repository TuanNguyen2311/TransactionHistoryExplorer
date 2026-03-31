package com.android.transactionhistoryexplorer.core;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

public class QualifierCore {

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface RoomTransactionRepo{}

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface RoomTransactionMapper{}

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LightweightTasks{}

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface BackgroundTasks{}

}
